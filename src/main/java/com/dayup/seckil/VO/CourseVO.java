package com.dayup.seckil.VO;

import java.io.Serializable;

import com.dayup.seckil.model.Course;

public class CourseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Course course;
	//状态
	private int courseStatus =0;
	//课程选课剩余多少时间
	private int remainTime = 0;
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public int getCourseStatus() {
		return courseStatus;
	}
	public void setCourseStatus(int courseStatus) {
		this.courseStatus = courseStatus;
	}
	public int getRemainTime() {
		return remainTime;
	}
	public void setRemainTime(int remainTime) {
		this.remainTime = remainTime;
	}
	
	
	
	
}
