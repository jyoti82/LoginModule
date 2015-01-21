package com.jyoti.loginmodule.models;

import java.io.Serializable;

public class AuthenticatedUserInfo implements Serializable{


	private UserCredential userCredential;
	
	public AuthenticatedUserInfo(UserCredential userCredential ){
		super();
		this.userCredential = userCredential;
	}
	
	public UserCredential getUserCredential() {
		return userCredential;
	}
	
	public void setUserCredential(UserCredential userCredential) {
		this.userCredential = userCredential;
	}

	
}
