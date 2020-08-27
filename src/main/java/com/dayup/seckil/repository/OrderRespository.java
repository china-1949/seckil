package com.dayup.seckil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dayup.seckil.model.Orders;
@Repository
public interface OrderRespository extends JpaRepository<Orders, String> {

	public Orders findByUsernameAndCourseNo(String username, String courseNo);
	
}
