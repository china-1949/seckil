package com.dayup.seckil.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.dayup.seckil.base.result.Result;
import com.dayup.seckil.base.result.ResultCode;
import com.dayup.seckil.model.Course;
import com.dayup.seckil.model.Orders;
import com.dayup.seckil.model.User;
import com.dayup.seckil.redis.CourseRedis;
import com.dayup.seckil.service.ICourseService;
import com.dayup.seckil.service.IOrderService;
import com.dayup.seckil.service.ISeckillService;

@Service
@Transactional
public class SeckillServiceImpl implements ISeckillService {
	@Autowired
	public ICourseService iCourseService;
	
	@Autowired
	public IOrderService iOrderService;
	
	
	@Autowired
	public CourseRedis courseRedis;
	
	@Autowired
	public KafkaTemplate<String, String> kafkaTemplate;
	
	private static  Map<String,Boolean> isSeckill = new HashMap<String,Boolean>();
	
	public Orders seckill(User user,Course course){
		//减库存(数据库操作)
		int success=iCourseService.reduceStockByCourseNo(course.getCourseNo());
		//下订单(订单数据库操作)
		if(success >0){
			Orders orders =new Orders();
			//使用BeanUtil进行赋值
			BeanUtils.copyProperties(course, orders);
			orders.setUsername(user.getUsername());
			orders.setCreatBy(user.getUsername());
			orders.setCreateDate(new Date());
			orders.setPayPrice(course.getCourcePrice());;
			orders.setPayStatus("0");
			return iOrderService.saveAndFlush(orders);
		}
		return null;
		
	}
	
	@Override
	public Result<Orders> seckillFlow(User user, String courseNo) {
		System.out.print("user:"+user.getUsername());
		
		boolean isPass=isSeckill.get(courseNo);
		if(isPass){ //课程的map为true，返回错误信息
			return Result.failure(ResultCode.SECKILL_NO_QUOTE);
		}
		//判断库存redis ，预减库存
		double stockQuantity = courseRedis.decr(courseNo, -1);
		if(stockQuantity<=0){
			//将课程的map设置为true
			isSeckill.put(courseNo, true);
			return Result.failure(ResultCode.SECKILL_NO_QUOTE);
		}
		//判断库存(数据层的判断移入到KafkaConsumer)
		kafkaTemplate.send("test",courseNo+","+user.getUsername());
		//Orders newOrder = seckill(user,course); //移入到KafkaConsumer
		return Result.failure(ResultCode.SECKILL_LINE_UP);
	}
	//初始化操作
	@Override
	public void cacheAllCourse() {
		//查询所有的课程
		List<Course> courseList = iCourseService.findAllCourse();
		if(null==courseList){
			//程序结束了
			return ;
		}
		//缓存
		for (Course course : courseList) {
			courseRedis.putString(course.getCourseNo(), course.getStockQuantity(), 60, true); //每个课程信息60s过期  ,course.getStockQuantity()为Integer类型的，存在redis里面取得时候会报错
			courseRedis.put(course.getCourseNo(), course, -1);// 保存每个course对象
			isSeckill.put(course.getCourseNo(), false); //没有卖之前所有课程为false
		}
	}

}
