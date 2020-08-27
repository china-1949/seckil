package com.dayup.seckil.service;

import com.dayup.seckil.model.Orders;

public interface IOrderService {

	Orders getOrderByUsernameAndCourseNo(String username, String courseNo);

	Orders saveAndFlush(Orders orders);
	
}
