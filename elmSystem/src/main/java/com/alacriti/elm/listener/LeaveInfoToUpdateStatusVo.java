package com.alacriti.elm.listener;

public class LeaveInfoToUpdateStatusVo {

	String empID;
	int leaveID;
	String leaveType;
	int noOfDays;
	public LeaveInfoToUpdateStatusVo(){}
	public LeaveInfoToUpdateStatusVo(String emp_id, int leave_id,String leaveType,int noOfDays) {
		super();
		this.empID = emp_id;
		this.leaveID = leave_id;
		this.leaveType=leaveType;
		this.noOfDays=noOfDays;
	}
	
	public LeaveInfoToUpdateStatusVo(String empID, int leaveID) {
		super();
		this.empID = empID;
		this.leaveID = leaveID;
	}
	public String getEmp_id() {
		return empID;
	}
	public void setEmp_id(String emp_id) {
		this.empID = emp_id;
	}
	public int getLeave_id() {
		return leaveID;
	}
	public void setLeave_id(int leave_id) {
		this.leaveID = leave_id;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public int getNoOfDays() {
		return noOfDays;
	}
	public void setNoOfDays(int noOfDays) {
		this.noOfDays = noOfDays;
	}
	
}
