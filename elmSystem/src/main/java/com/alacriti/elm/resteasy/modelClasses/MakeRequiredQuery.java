package com.alacriti.elm.resteasy.modelClasses;

public class MakeRequiredQuery {

	public String queryForHR(String leaveType) {

		if(leaveType.equals("sick")){
			
		return "UPDATE raghava_empleaveportal_leaves_info AS t1,raghava_empleaveportal_leave_request_info as t3 INNER JOIN "
				+ "(SELECT availed_leaves,bal_in_sick,total_bal_leaves FROM raghava_empleaveportal_leaves_info WHERE emp_id =?) AS t2 "
				+ "SET t1.availed_leaves = t2.availed_leaves+?,t1.bal_in_sick = t2.bal_in_sick-?,t1.total_bal_leaves = t2.total_bal_leaves-?,t3.hr_status='approved' "
				+ "WHERE t1.emp_id = ? and t3.emp_id=?;";
			
		}
		if(leaveType.equals("casual")){
			
		return "UPDATE raghava_empleaveportal_leaves_info AS t1,raghava_empleaveportal_leave_request_info as t3 INNER JOIN "
				+ "(SELECT availed_leaves,bal_in_casual,total_bal_leaves FROM raghava_empleaveportal_leaves_info WHERE emp_id =?) AS t2 "
				+ "SET t1.availed_leaves = t2.availed_leaves+?,t1.bal_in_casual = t2.bal_in_casual-?,t1.total_bal_leaves = t2.total_bal_leaves-?,t3.hr_status='approved' "
				+ "WHERE t1.emp_id = ? and t3.emp_id=?;";
			
		}
		if(leaveType.equals("privilege")){
			
		return "UPDATE raghava_empleaveportal_leaves_info AS t1,raghava_empleaveportal_leave_request_info as t3 INNER JOIN "
				+ "(SELECT availed_leaves,bal_in_casual,total_bal_leaves FROM raghava_empleaveportal_leaves_info WHERE emp_id =?) AS t2 "
				+ "SET t1.availed_leaves = t2.availed_leaves+?,t1.bal_in_casual = t2.bal_in_casual-?,t1.total_bal_leaves = t2.total_bal_leaves-?,t3.hr_status='approved' "
				+ "WHERE t1.emp_id = ? and t3.emp_id=?;";
		}
		else
		return null;
	}

	public String queryForTL() {
		return "update raghava_empleaveportal_leave_request_info set team_lead_status='approved' where emp_id=?;";
	}

	public String queryForPM() {
		return "update raghava_empleaveportal_leave_request_info set proj_man_status='approved' where emp_id=?;";
	}

	public String queryForRejectTL() {
		return "update raghava_empleaveportal_leave_request_info set team_lead_status='reject' where emp_id=?;";
	}

	public String queryForRejectPM() {
		return "update raghava_empleaveportal_leave_request_info set proj_man_status='reject' where emp_id=?;";
	}

	public String queryForRejectHR() {
		return "update raghava_empleaveportal_leave_request_info set hr_status='reject' where emp_id=?;";
	}

	
}
