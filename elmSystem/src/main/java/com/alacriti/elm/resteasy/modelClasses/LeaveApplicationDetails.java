package com.alacriti.elm.resteasy.modelClasses;

import java.sql.Date;
import java.util.Calendar;

public class LeaveApplicationDetails {
	String emp_id;
	String leave_type;
	Date date_of_leave;
	int no_of_days;
	String reason;
	Date applied_date;
	java.sql.Date ourJavaDateObject = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	
	public LeaveApplicationDetails(){}
	public LeaveApplicationDetails(String emp_id, String leave_type,
			Date date_of_leave, int no_of_days, String reason) {
		super();
		this.emp_id = emp_id;
		this.leave_type = leave_type;
		this.date_of_leave = date_of_leave;
		this.no_of_days = no_of_days;
		this.reason = reason;
		this.applied_date=this.ourJavaDateObject;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getLeave_type() {
		return leave_type;
	}
	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}
	public Date getDate_of_leave() {
		return date_of_leave;
	}
	public void setDate_of_leave(Date date_of_leave) {
		this.date_of_leave = date_of_leave;
	}
	public int getNo_of_days() {
		return no_of_days;
	}
	public void setNo_of_days(int no_of_days) {
		this.no_of_days = no_of_days;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getApplied_date() {
		return applied_date;
	}
	public void setApplied_date(Date applied_date) {
		this.applied_date = applied_date;
	}
	
}
