package com.alacriti.elm.resteasy.modelClasses;

public class EmployeeProfile {
	String emp_id;
	String fullName;
	String designation;
	String mobile;
	String email;
	String project;
	
	
	public EmployeeProfile(String emp_id, String fullName, String designation,
			String mobile, String email, String project) {
		super();
		this.emp_id = emp_id;
		this.fullName = fullName;
		this.designation = designation;
		this.mobile = mobile;
		this.email = email;
		this.project = project;
	}
	
	public EmployeeProfile(){}
	 
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	

}
