package com.dayup.seckil;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dayup.seckil.VO.UserVo;
import com.dayup.seckil.model.User;
import com.dayup.seckil.redis.UserRedis;
import com.dayup.seckil.service.UserService;
import com.dayup.seckil.util.MD5Utils;




@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	@Autowired 
	public UserService userService;
	
	@Autowired 
	public UserRedis userRedis;
	
	@Test
	public void test(){
		User user =new User("chens","23123121312");
	//	User newUser =save(user);
		//如果有主键，有该主键则修改，没有则保存新的一条数据
		Assert.assertNotNull(userService.register(user));
	//	fail("这个是粗我的");
	}
	@Test
	public void testGetUser(){
		Assert.assertNotNull(userService.getUser("chensong"));
	}
	
	@Test
	public void testPassword(){
		UserVo user = userService.getUser("chensong");
		String password= MD5Utils.inputToDb("123456", user.getDbflag());
		Assert.assertEquals(password, user.getPassword());
	}
	
	@Test
	public void testPutRedis(){
		User user =new User("cs2","123456"); 
		userRedis.put(user.getUsername(),user , -1);
	}
	
}










