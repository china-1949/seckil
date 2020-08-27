package com.dayup.seckil.service;

import com.dayup.seckil.VO.UserVo;
import com.dayup.seckil.model.User;

public interface UserService {
	public User register(User user);
	//解决懒加载问题（与数据库解绑）
	public UserVo getUser(String username);
	
	public void saveUserToRedisByToken(UserVo dbUser, String token);
	
	public Object getUserFromRedisByToken(String token);
}
