package com.dayup.seckil.VO;

import java.io.Serializable;

//pojo类
public class UserVo implements Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String  username;
	
	
	private String password;
	
	private String repassword;
	
	
	private String dbflag;


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRepassword() {
		return repassword;
	}


	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}


	public String getDbflag() {
		return dbflag;
	}


	public void setDbflag(String dbflag) {
		this.dbflag = dbflag;
	}
	
	
	
}
