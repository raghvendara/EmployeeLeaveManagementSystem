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

public class AutoUpdateTLStatusInDBDao {

	public boolean updateTLStatus(Date todaysDateObject) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCommand=null;
		Connection connection=null;
		List<LeaveInfoToUpdateStatusVo> empLeaveList = new ArrayList<LeaveInfoToUpdateStatusVo>();
		boolean flagToCheckResultSet=false;

		try {
			
			connection=MySqlDataSource.getInstance().getConnection();
			sqlCommand="select emp_id,leave_id,adddate(applied_date,interval 3 day) from raghava_empleaveportal_leave_request_info where team_lead_status='na';";
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
					empLeaveList.add(new LeaveInfoToUpdateStatusVo(rs.getString(1),rs.getInt(2)));
				}
			}
			if(empLeaveList.size()!=0 && flagToCheckResultSet){
				updateTLLeaveStatus(connection, empLeaveList);
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

	private void updateTLLeaveStatus(Connection connection, List<LeaveInfoToUpdateStatusVo> empLeaveList) {
		System.out.println("@@@@@@@@@@ ****** in the updateTLLeaveStatus ******* @@@@@@@@@@");

		int leaveID;
		String empID;
		String sqlCommand=null;
		PreparedStatement stmt=null;
		Iterator<LeaveInfoToUpdateStatusVo> itr = empLeaveList.iterator();
		while(itr.hasNext())
		{
			LeaveInfoToUpdateStatusVo leaveRequest=itr.next();
			leaveID=leaveRequest.getLeave_id();
			empID=leaveRequest.getEmp_id();
			sqlCommand="update raghava_empleaveportal_leave_request_info set team_lead_status='approved' where leave_id=? and emp_id=?;";
			try {
				stmt=connection.prepareStatement(sqlCommand);
				stmt.setInt(1,leaveID);
				stmt.setString(2,empID);
				int rowsAffected=stmt.executeUpdate();
				if(rowsAffected>=1){
				System.out.println("@@@@@@@@@@ ****** updated the TL leave status in database ******* @@@@@@@@@@");
				connection.commit();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}
