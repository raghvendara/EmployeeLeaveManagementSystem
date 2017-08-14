package com.alacriti.elm.resteasy.modelClasses;

public class ResponseToForgotPassword {

	boolean flag;
	String emp_id;
	
	public ResponseToForgotPassword(boolean flag, String emp_id) {
		super();
		this.flag = flag;
		this.emp_id = emp_id;
	}
	public ResponseToForgotPassword(){}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	
}
