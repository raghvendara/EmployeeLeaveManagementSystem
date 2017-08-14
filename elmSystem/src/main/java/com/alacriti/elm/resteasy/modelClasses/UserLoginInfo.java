package com.alacriti.elm.resteasy.modelClasses;

public class UserLoginInfo {
	String userName, password;
	
	public UserLoginInfo(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public UserLoginInfo() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}