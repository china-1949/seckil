package com.dayup.seckil.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="user")
public class User implements Serializable {
	
	
	/**
	 * pom文件引入jetty的配置，对象需要序列化
	 */
	private static final long serialVersionUID = 1L;

	//有参构造器，与无参构造器
	public User() {
	
	}
	public User(String username, String password) {
	
		this.username = username;
		this.password = password;
	}
	@Id //指定主键
	@Column(name="username",nullable=false)    //不等于为空的
	@NotBlank(message="用户名不能为空！！")
	private String  username;
	
	@Column(name="password",nullable=false)
	@NotBlank(message="密码不能为空！")
	private String password;
	
	private String repassword;
	
	public String getDbflag() {
		return dbflag;
	}
	public void setDbflag(String dbflag) {
		this.dbflag = dbflag;
	}
	@Column(name="dbflag")
	private String dbflag;
	
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	//这个是学生的id
	@Column(name="id",nullable = false)
	private long id;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", id=" + id + "]";
	}
	
	
}
