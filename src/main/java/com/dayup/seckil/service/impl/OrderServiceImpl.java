package com.dayup.seckil.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayup.seckil.model.Orders;
import com.dayup.seckil.repository.OrderRespository;
import com.dayup.seckil.service.IOrderService;
@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
	@Autowired
	public OrderRespository  orderRespository;
	
	@Override
	public Orders getOrderByUsernameAndCourseNo(String username, String courseNo) {
		// TODO Auto-generated method stub
		return orderRespository.findByUsernameAndCourseNo(username,courseNo);
	}

	@Override
	public Orders saveAndFlush(Orders orders) {
		// TODO Auto-generated method stub
		return orderRespository.saveAndFlush(orders);
	}
	
}
