package com.alacriti.elm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alacriti.elm.datasource.MySqlDataSource;
import com.alacriti.elm.listener.LeaveInfoToUpdateStatusVo;
import com.alacriti.elm.utilities.MakeRequiredQuery;

public class AutoUpdateHRStatusInDBDao {

	public boolean updateHRStatus(Date todaysDateObject) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCommand=null;
		Connection connection=null;
		List<LeaveInfoToUpdateStatusVo> empLeaveList = new ArrayList<LeaveInfoToUpdateStatusVo>();
		boolean flagToCheckResultSet=false;

		try {
			
			connection=MySqlDataSource.getInstance().getConnection();
			sqlCommand="select emp_id,leave_id,adddate(applied_date,interval 5 day),leave_type,no_of_days from raghava_empleaveportal_leave_request_info where 'approved' in (team_lead_status) and 'approved' in (proj_man_status) and 'na' in (hr_status);";
			stmt=connection.prepareStatement(sqlCommand);
			rs=stmt.executeQuery();
			while(rs.next())
			{
				System.out.println("#####  emp_id : "+rs.getString(1)+"leave id :"+rs.getInt(2)+
						"applied date : "+rs.getDate(3)+"#######");
				int compareResult = todaysDateObject.compareTo(rs.getDate(3));
				if(compareResult==0)
					System.out.println("********** dates matched!!! ************");
				if(compareResult<0)
					System.out.println("********* current date is less than the applied date plus three....!********");
				else{
					System.out.println("********* current date is grater than the applied date plus three **********");
					flagToCheckResultSet=true;
					empLeaveList.add(new LeaveInfoToUpdateStatusVo(rs.getString(1),rs.getInt(2),rs.getString(4),rs.getInt(5)));
				}
			}
			if(empLeaveList.size()!=0 && flagToCheckResultSet){
				updateHRLeaveStatus(connection, empLeaveList);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return flagToCheckResultSet;

	}

	private void updateHRLeaveStatus(Connection connection, List<LeaveInfoToUpdateStatusVo> empLeaveList) {
		System.out.println("@@@@@@@@@@ ****** in the updateHRLeaveStatus ******* @@@@@@@@@@");
		
		String sqlCommand=null;
		PreparedStatement stmt=null;
		Iterator<LeaveInfoToUpdateStatusVo> itr = empLeaveList.iterator();
		MakeRequiredQuery makeRequiredQuery=new MakeRequiredQuery();

		while(itr.hasNext())
		{
			LeaveInfoToUpdateStatusVo leaveRequest=itr.next();
			sqlCommand=makeRequiredQuery.queryForHR(leaveRequest.getLeaveType());
			try {
				stmt=connection.prepareStatement(sqlCommand);
				stmt.setString(1, leaveRequest.getEmp_id());
				stmt.setInt(2, leaveRequest.getNoOfDays());
				stmt.setInt(3, leaveRequest.getNoOfDays());
				stmt.setInt(4, leaveRequest.getNoOfDays());
				stmt.setString(5, leaveRequest.getEmp_id());
				stmt.setString(6, leaveRequest.getEmp_id());
				stmt.setInt(7, leaveRequest.getLeave_id());
				int rowsAffected=stmt.executeUpdate();
				if(rowsAffected>=1){
				System.out.println("@@@@@@@@@@ ****** updated the HR leave status in database ******* @@@@@@@@@@");
				connection.commit();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
