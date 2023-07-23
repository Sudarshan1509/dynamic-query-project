package com.authorization.model;

import lombok.Data;
import lombok.ToString;

/**
 * This is model class which is used as a response when a successful validation
 * of token happens which contains fields like UserId, UserName and if the user
 * is valid or not.
 */

@Data
@ToString
public class JwtResponse {

	private String userid;

	private String username;

	private boolean isValid;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	

	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JwtResponse(String userid, String username, boolean isValid) {
		super();
		this.userid = userid;
		this.username = username;
		this.isValid = isValid;
	}
	
	
}
