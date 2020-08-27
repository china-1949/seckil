package com.dayup.seckil.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayup.seckil.VO.UserVo;
import com.dayup.seckil.model.User;
import com.dayup.seckil.redis.UserRedis;
import com.dayup.seckil.repository.UserRepository;
import com.dayup.seckil.service.UserService;
@Service
@Transactional
public class UserServiceImpl  implements UserService{
	
	@Autowired
	public UserRepository  userRepository;
	
	//需要引入redis
	@Autowired
	public UserRedis userRedis;
	@Override
	public User register(User user) {
		// TODO Auto-generated method stub
		//保存好立即返回该对象
		User saveAndFlush = userRepository.saveAndFlush(user);
		//User save = userRepository.save(user);
		return saveAndFlush;
	}
	
	@Override
	public UserVo getUser(String username) {
		UserVo userVo = new UserVo();
		User user = userRedis.get(username);
		if(user == null){ //如果缓存没有从数据库中读取
			user=userRepository.findByUsername(username);
			if(user!= null){ //-1一直保持(先一直保存，登录成功校验后saveUserToRedisByToken则为3600)
				userRedis.put(user.getUsername(), user, -1);
			}else{
				//直接返回空
				return null;
			}
		}
		
//		else{
//			 user=userRedis.get(username);
//		}
		//将user转化为userVo
		BeanUtils.copyProperties(user, userVo);
		return userVo;
		
		
		// TODO Auto-generated method stub
	//	return userRepository.getOne(username); // 使用getOne也是可以的（和懒加载相关的，尽量不要用）
	//	return userRepository.findByUsername(username);
	}

	@Override
	public void saveUserToRedisByToken(UserVo dbUser, String token) {
		User user = new User();
		//将UserVo转化为User对象
		BeanUtils.copyProperties(dbUser, user);
		userRedis.put(token, user, 3600);
	}

	@Override
	public Object getUserFromRedisByToken(String token) {
		// TODO Auto-generated method stub
		return userRedis.get(token);
	}

}
