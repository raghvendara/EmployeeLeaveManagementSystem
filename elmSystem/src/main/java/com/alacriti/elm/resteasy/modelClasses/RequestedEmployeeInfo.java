package com.alacriti.elm.resteasy.modelClasses;

import java.sql.Date;

public class RequestedEmployeeInfo {
	
		String emp_id;
		String fullName;
		int leave_id;
		String leave_type;
		Date applied_date;
		Date date_of_leave;
		int noOfDays;
		String reason;
		String email;
		public RequestedEmployeeInfo(){}
		
		public RequestedEmployeeInfo(String emp_id, String fullName, int leave_id,
				String leave_type, Date applied_date, Date date_of_leave,
				int noOfDays, String reason, String email) {
			super();
			this.emp_id = emp_id;
			this.fullName = fullName;
			this.leave_id = leave_id;
			this.leave_type = leave_type;
			this.applied_date = applied_date;
			this.date_of_leave = date_of_leave;
			this.noOfDays = noOfDays;
			this.reason = reason;
			this.email = email;
		}
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
		public int getLeave_id() {
			return leave_id;
		}
		public void setLeave_id(int leave_id) {
			this.leave_id = leave_id;
		}
		public String getLeave_type() {
			return leave_type;
		}
		public void setLeave_type(String leave_type) {
			this.leave_type = leave_type;
		}
		public Date getApplied_date() {
			return applied_date;
		}
		public void setApplied_date(Date applied_date) {
			this.applied_date = applied_date;
		}
		public Date getDate_of_leave() {
			return date_of_leave;
		}
		public void setDate_of_leave(Date date_of_leave) {
			this.date_of_leave = date_of_leave;
		}
		public int getNoOfDays() {
			return noOfDays;
		}
		public void setNoOfDays(int noOfDays) {
			this.noOfDays = noOfDays;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}

}
