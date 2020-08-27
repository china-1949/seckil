package com.dayup.seckil.redis;

import org.springframework.stereotype.Repository;

import com.dayup.seckil.model.User;
//只有抽象的方法需要被子类实现，不是抽象可以不写
@Repository //需要加入到spring中
public class UserRedis extends BaseRedis<User> {
	private static final String REDIS_KEY ="com.dayup.seckil.redis.UserRedis";
	
	@Override
	protected String getRedisKey() {
		
		return REDIS_KEY;
	}
	
}
