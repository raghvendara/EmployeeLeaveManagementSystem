package com.alacriti.elm.resteasy.modelClasses;

public class AddEmpInfo {

	String firstName;
	String lastName;
	String empID;
	String designation;
	String project;
	String email;
	String userName;
	String password;
	String mobileNumber;
	
	public AddEmpInfo(String firstName, String lastName, String empID,
			String designation, String project, String email, String userName,
			String password, String mobileNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.empID = empID;
		this.designation = designation;
		this.project = project;
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.mobileNumber = mobileNumber;
	}
	
	public AddEmpInfo(){}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	
}
