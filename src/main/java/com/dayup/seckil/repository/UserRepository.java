package com.dayup.seckil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dayup.seckil.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {  //JpaRepository 封装了分页操作，增删改查操作
	//并列的条件查询(等效于select * from user where  user name ='username' and password ='password' )
	public User findByUsernameAndPassword(String name,String password);
	
	public User findByUsername(String name);
	
}
