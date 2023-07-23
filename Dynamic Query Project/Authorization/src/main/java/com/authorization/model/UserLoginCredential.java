package com.authorization.model;

import lombok.Data;
import lombok.ToString;

/**
 * This is a model class which is passed as an argument to generate token in
 * login method of the JwtAuthenticationController class.This class contains
 * fields like UserId and Password.
 *
 */
@Data
@ToString
public class UserLoginCredential {

	private String userid;

	private String password;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserLoginCredential(String userid, String password) {
		super();
		this.userid = userid;
		this.password = password;
	}

	public UserLoginCredential() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
