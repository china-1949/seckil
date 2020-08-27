package com.dayup.seckil.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dayup.seckil.model.Course;
import com.dayup.seckil.redis.CourseRedis;
import com.dayup.seckil.repository.CourseRespository;
import com.dayup.seckil.service.ICourseService;
@Service
@Transactional
public class CourseServiceImpl implements ICourseService {
	@Autowired
	private CourseRespository  courseRespository;
	
	//引入CourseRedis
	@Autowired
	private CourseRedis courseRedis;
	
	public static final String ALL_COURSE_REDIS="allCourseRedis";
	@Override
	public List<Course> findAllCourse() {
		List<Course> courseList = new ArrayList<Course>();
		// TODO Auto-generated method stub
		//从redis读取数据
		String courseListString=(String)courseRedis.getString(ALL_COURSE_REDIS);
		//List list = JSON.parseObject(tokenStr, List.class);
		courseList = JSON.parseArray(courseListString, Course.class);
		//没有从mysql中读取
		if(StringUtils.isEmpty(courseListString)){
			//读取数据库，直接赋给courseList
			courseList=courseRespository.findAll();
			//缓存到redis中,先要转化为json的字符串
			String courseString = JSON.toJSONString(courseList).toString();
			courseRedis.putString(ALL_COURSE_REDIS, courseString, -1); //expire表示永不失效
		}
		
		return  courseList;
	}
	
	@Override
	public Course findCourseByCourseNo(String courseNo) {
		// TODO Auto-generated method stub
		Optional<Course> course =courseRespository.findById(courseNo);//java 8 里面的 常用于空值null的判断
		return course.orElse(null);//相当于if判断是否为空的,不存在则返回null'
	}
	
	//减库存
	@Override
	public int reduceStockByCourseNo(String courseNo) {
		
		return courseRespository.reduceStockByCourseNo(courseNo);
	}

}
