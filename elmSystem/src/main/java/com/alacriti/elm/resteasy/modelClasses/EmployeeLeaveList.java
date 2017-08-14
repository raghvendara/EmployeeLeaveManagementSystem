package com.alacriti.elm.resteasy.modelClasses;

import java.sql.Date;


public class EmployeeLeaveList {

	int leaveID;
	String leave_type;
	Date date_of_leave;
	int no_of_days;
	String reason;
	String team_lead_status;
	String proj_man_status;
	String hr_status;
	Date applied_date;

	
	public EmployeeLeaveList(int leaveID, String leave_type,
			Date date_of_leave, int no_of_days, String reason,
			String team_lead_status, String proj_man_status, String hr_status, Date applied_date) {
		super();
		this.leaveID = leaveID;
		this.leave_type = leave_type;
		this.date_of_leave = date_of_leave;
		this.no_of_days = no_of_days;
		this.reason = reason;
		this.team_lead_status = team_lead_status;
		this.proj_man_status = proj_man_status;
		this.hr_status = hr_status;
		this.applied_date =applied_date;
	}
	public EmployeeLeaveList(){}
	public int getLeaveID() {
		return leaveID;
	}
	public void setLeaveID(int leaveID) {
		this.leaveID = leaveID;
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
	public String getTeam_lead_status() {
		return team_lead_status;
	}
	public void setTeam_lead_status(String team_lead_status) {
		this.team_lead_status = team_lead_status;
	}
	public String getProj_man_status() {
		return proj_man_status;
	}
	public void setProj_man_status(String proj_man_status) {
		this.proj_man_status = proj_man_status;
	}
	public String getHr_status() {
		return hr_status;
	}
	public void setHr_status(String hr_status) {
		this.hr_status = hr_status;
	}
	public Date getApplied_date() {
		return applied_date;
	}
	public void setApplied_date(Date applied_date) {
		this.applied_date = applied_date;
	}
	
}
