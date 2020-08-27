package com.dayup.seckil.controller.api;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dayup.seckil.base.controller.BaseApiController;
import com.dayup.seckil.base.result.Result;
import com.dayup.seckil.model.Orders;
import com.dayup.seckil.model.User;
import com.dayup.seckil.service.ISeckillService;
@RestController
public class SeckillAPIController extends BaseApiController implements InitializingBean { //InitializingBean 初始化操作
	@Autowired
	public ISeckillService iSeckillService;
	
	//初始化缓存所有的课程
	@Override
	public void afterPropertiesSet() throws Exception {
		iSeckillService.cacheAllCourse();
		
	}
	@RequestMapping(value="seckill/{courseNo}",method=RequestMethod.GET)
	public Result<Orders> seckill(User user,@PathVariable String courseNo){ 
		if(null == user){
			return Result.failure();
		}
		
		return  iSeckillService.seckillFlow(user,courseNo);
		
	}
	
}
