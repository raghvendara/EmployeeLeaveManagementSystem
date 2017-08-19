package com.alacriti.elm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.alacriti.elm.resteasy.modelClasses.LeaveApplicationDetails;

public class UserDao extends BaseDao {
	public UserDao() {
	}

	public UserDao(Connection conn) {
		super(conn);
	}
	public static final Logger log= Logger.getLogger(UserDao.class);


	public boolean LeaveApplicationDao(
			LeaveApplicationDetails leaveApplicationDetails) throws DaoException{
		log.debug("in LeaveApplicationDao");
		
		String sqlCommand=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		java.sql.Date ourJavaDateObject = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		sqlCommand="insert into raghava_empleaveportal_leave_request_info(emp_id,leave_id,leave_type,date_of_leave,no_of_days,reason,team_lead_status,proj_man_status,hr_status,applied_date) values(?,(select max(t.leave_id)+1 from raghava_empleaveportal_leave_request_info as t where emp_id=?),?,?,?,?,'na','na','na',?);";
					
			try {
				
				Connection connection=getConnection();
				stmt=connection.prepareStatement(sqlCommand);
				
				stmt.setString(1, leaveApplicationDetails.getEmp_id());
				stmt.setString(2, leaveApplicationDetails.getEmp_id());
				stmt.setString(3, leaveApplicationDetails.getLeave_type());
				stmt.setDate(4, leaveApplicationDetails.getDate_of_leave());
				stmt.setInt(5, leaveApplicationDetails.getNo_of_days());
				stmt.setString(6, leaveApplicationDetails.getReason());
				stmt.setDate(7, ourJavaDateObject);

				int flagg=stmt.executeUpdate();
				if(flagg>=1){
					System.out.println("success going to return true..");
				return true;
				}	
				else return false;	
					
			} catch (SQLException e) {
				log.error("Exception occured ",e);
				throw new DaoException("SQLException in selectMessage():", e);
			} finally {
				close(stmt, rs);
			}

	}
	
	
	
}
