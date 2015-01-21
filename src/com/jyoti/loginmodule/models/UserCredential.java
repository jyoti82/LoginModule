package com.jyoti.loginmodule.models;

import java.io.Serializable;

public class UserCredential implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String userName;
	private String emailId;
	private String password;
	private String userId;
	
	public UserCredential(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public UserCredential(String userName, String password, String emailId, String userId) {
		super();
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.setUserId(userId);
	}

	public String getUserName() {
		return userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getemailId() {
		return emailId;
	}

	public void setemailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
	
	

