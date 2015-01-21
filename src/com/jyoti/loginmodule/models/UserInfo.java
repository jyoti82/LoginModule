package com.jyoti.loginmodule.models;

import java.io.Serializable;

public class UserInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String address;
	private String mobile;
	private String Email;
	
	public UserInfo(String user, String pass, String email, String mob, String add){
		super();
		this.userName = user;
		this.password = pass;
		this.Email = email;
		this.mobile = mob;
		this.address = add;
	}
	String getUserName(){
		return userName;
	}
	String getPassword(){
		return password;
	}
	String getAddress(){
		return address;
	}
	String getMobile(){
		return mobile;
	}
	
	String getEmail(){
		return Email;
	}
	
	void setUserName(String usernm){
		this.userName = usernm;
	}
	void setPassword(String pass){
		this.password = pass;
	}
	void setMobile(String mob){
		this.mobile = mob;
	}
	void setAddress(String add){
		this.address = add;
	}
	void setEmail(String email){
		this.Email = email;
	}
	
	
}
