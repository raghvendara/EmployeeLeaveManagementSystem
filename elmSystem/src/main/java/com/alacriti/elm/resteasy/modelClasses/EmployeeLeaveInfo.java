package com.alacriti.elm.resteasy.modelClasses;

public class EmployeeLeaveInfo {

	String emp_id=null;
	int availed_leaves;
	int bal_in_sick;
	int bal_in_casual;
	int bal_in_privilege;
	int total_bal_leaves;
	String fullName;
	
	

	public EmployeeLeaveInfo(){}
	
	public EmployeeLeaveInfo(String emp_id, int availed_leaves,
			int bal_in_sick, int bal_in_casual, int bal_in_privilege,
			int total_bal_leaves, String fullName) {
		super();
		this.emp_id = emp_id;
		this.availed_leaves = availed_leaves;
		this.bal_in_sick = bal_in_sick;
		this.bal_in_casual = bal_in_casual;
		this.bal_in_privilege = bal_in_privilege;
		this.total_bal_leaves = total_bal_leaves;
		this.fullName=fullName;
	}
	public String getFullName() {
		return fullName;
	}

	public EmployeeLeaveInfo(int availed_leaves, int bal_in_sick,
			int bal_in_casual, int bal_in_privilege, int total_bal_leaves) {
		super();
		this.availed_leaves = availed_leaves;
		this.bal_in_sick = bal_in_sick;
		this.bal_in_casual = bal_in_casual;
		this.bal_in_privilege = bal_in_privilege;
		this.total_bal_leaves = total_bal_leaves;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public int getAvailed_leaves() {
		return availed_leaves;
	}
	public void setAvailed_leaves(int availed_leaves) {
		this.availed_leaves = availed_leaves;
	}
	public int getBal_in_sick() {
		return bal_in_sick;
	}
	public void setBal_in_sick(int bal_in_sick) {
		this.bal_in_sick = bal_in_sick;
	}
	public int getBal_in_casual() {
		return bal_in_casual;
	}
	public void setBal_in_casual(int bal_in_casual) {
		this.bal_in_casual = bal_in_casual;
	}
	public int getBal_in_privilege() {
		return bal_in_privilege;
	}
	public void setBal_in_privilege(int bal_in_privilege) {
		this.bal_in_privilege = bal_in_privilege;
	}
	public int getTotal_bal_leaves() {
		return total_bal_leaves;
	}
	public void setTotal_bal_leaves(int total_bal_leaves) {
		this.total_bal_leaves = total_bal_leaves;
	}
	
	
}
