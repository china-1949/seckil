package com.dayup.seckil.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.dayup.seckil.model.Course;
import com.dayup.seckil.model.Orders;
import com.dayup.seckil.model.User;
import com.dayup.seckil.service.ICourseService;
import com.dayup.seckil.service.IOrderService;
import com.dayup.seckil.service.ISeckillService;

@Component
public class KafkaConsumer {
	@Autowired
	public ICourseService iCourseService;
	
	@Autowired
	public IOrderService iOrderService;
	
	@Autowired
	public ISeckillService  iSeckillService;
	
	@KafkaListener(id="seconds-kill",topics ="test",groupId ="seconds-kill")
	public void listener(ConsumerRecord<?, ?> record) {
		String[] messages = record.value().toString().split(",");
		String courseNo =messages[0];
		String username =messages[1];
		//判断库存（数据库层判断）
		Course course = iCourseService.findCourseByCourseNo(courseNo);
		Integer stock = course.getStockQuantity();
		if(stock <=0){
			return ;
		}
		//判断是否已经购买（数据库层判断）
		Orders order=iOrderService.getOrderByUsernameAndCourseNo(username,courseNo);
		if(null!= order){
			return ;
		}
		//减库存下订单
		//Orders newOrder = seckill(user,course);
		//减库存下订单
		User user =new User();
		user.setUsername(username);
		iSeckillService.seckill(user, course);
		return ;
	}
}
