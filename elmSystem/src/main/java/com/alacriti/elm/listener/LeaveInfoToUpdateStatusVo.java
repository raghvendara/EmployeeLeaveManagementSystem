package com.alacriti.elm.listener;

public class LeaveInfoToUpdateStatusVo {

	String emp_id;
	int leave_id;
	public LeaveInfoToUpdateStatusVo(){}
	public LeaveInfoToUpdateStatusVo(String emp_id, int leave_id) {
		super();
		this.emp_id = emp_id;
		this.leave_id = leave_id;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public int getLeave_id() {
		return leave_id;
	}
	public void setLeave_id(int leave_id) {
		this.leave_id = leave_id;
	}
	
}
