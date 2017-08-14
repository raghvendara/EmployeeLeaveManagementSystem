package com.alacriti.elm.resteasy.modelClasses;

public class ForgotPasswordInfo {
	String userName;
	String emp_id;
	String email;
	String mobile;
	
	public ForgotPasswordInfo(String userName, String emp_id, String email,
			String mobile) {
		super();
		this.userName = userName;
		this.emp_id = emp_id;
		this.email = email;
		this.mobile = mobile;
	}
	public ForgotPasswordInfo(){}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
