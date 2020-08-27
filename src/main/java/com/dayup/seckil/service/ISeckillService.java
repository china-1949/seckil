package com.dayup.seckil.service;

import com.dayup.seckil.base.result.Result;
import com.dayup.seckil.model.Course;
import com.dayup.seckil.model.Orders;
import com.dayup.seckil.model.User;

public interface ISeckillService {

	Result<Orders> seckillFlow(User user, String courseNo);

	void cacheAllCourse();

	Orders seckill(User user, Course course);
	
}
