package com.alacriti.elm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alacriti.elm.resteasy.modelClasses.AddEmpInfo;
import com.alacriti.elm.resteasy.modelClasses.EmployeeLeaveInfo;
import com.alacriti.elm.resteasy.modelClasses.EmployeeLeaveList;
import com.alacriti.elm.resteasy.modelClasses.EmployeeProfile;
import com.alacriti.elm.resteasy.modelClasses.ForgotPasswordInfo;
import com.alacriti.elm.resteasy.modelClasses.MakeRequiredQuery;
import com.alacriti.elm.resteasy.modelClasses.NewPasswordInfo;
import com.alacriti.elm.resteasy.modelClasses.RequestedEmployeeInfo;
import com.alacriti.elm.resteasy.modelClasses.ResponseToForgotPassword;



public class AdminDao extends BaseDao{
	
	public AdminDao(Connection conn) {
		super(conn);
	}
	public AdminDao(){
		
	}
	MailingService sendMail=new MailingService();
	
	public List<RequestedEmployeeInfo> getRequestedEmployeeInfoDao(
			String projectName, String designation) throws DaoException {
		
		List<RequestedEmployeeInfo> emp_list=new ArrayList<RequestedEmployeeInfo>();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCommand = null;
		
		if(projectName.equals("null"))
			sqlCommand="select r.emp_id,r.leave_id,r.leave_type,r.applied_date,r.date_of_leave,r.no_of_days,r.reason,i.emp_fName,i.emp_lName,i.email from raghava_empleaveportal_leave_request_info as r join raghava_EmpLeavePortal_EmpInfo as i on r.emp_id=i.emp_id where 'approved' in (r.team_lead_status) and 'approved' in (r.proj_man_status) and 'na' in (r.hr_status)";
		if(designation.equals("PM"))
			sqlCommand="select r.emp_id,r.leave_id,r.leave_type,r.applied_date,r.date_of_leave,r.no_of_days,r.reason,i.emp_fName,i.emp_lName,i.email from raghava_empleaveportal_leave_request_info as r join raghava_EmpLeavePortal_EmpInfo as i on r.emp_id=i.emp_id where 'approved' in (r.team_lead_status) and i.project_name=?";
		if(designation.equals("TL"))
			sqlCommand="select r.emp_id,r.leave_id,r.leave_type,r.applied_date,r.date_of_leave,r.no_of_days,r.reason,i.emp_fName,i.emp_lName,i.email from raghava_empleaveportal_leave_request_info as r join raghava_EmpLeavePortal_EmpInfo as i on r.emp_id=i.emp_id where 'na' in (r.team_lead_status) and i.project_name=?";

		
		try {
			
			Connection connection=getConnection();
			stmt=connection.prepareStatement(sqlCommand);
			if(!(projectName.equals("null")))
				stmt.setString(1, projectName);
			
			rs= stmt.executeQuery();
			while(rs.next())
			{
				String emp_id=rs.getString(1);
				String fullName;
				if(rs.getString(9)!=null)
					fullName=rs.getString(8)+"       "+rs.getString(9);
				else
					fullName=rs.getString(8);
				int leave_id=rs.getInt(2);
				String leave_type=rs.getString(3);
				Date applied_date=rs.getDate(4);
				Date date_of_leave=rs.getDate(5);
				int noOfDays=rs.getInt(6);
				String reason=rs.getString(7);
				String email=rs.getString(10);
				emp_list.add(new RequestedEmployeeInfo(emp_id,fullName,leave_id,leave_type,applied_date,date_of_leave,noOfDays,reason,email));
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("SQLException in selectMessage():", e);
		} finally {
			close(stmt, rs);
		}
		return emp_list;
	}
	
	////// this method is only for HR
	
	public boolean acceptLeaveDao(
			RequestedEmployeeInfo requestedEmployeeInfo,String designation) throws DaoException{
		
		String emp_id=requestedEmployeeInfo.getEmp_id();
		String leaveType=requestedEmployeeInfo.getLeave_type();
		int noOfDays=requestedEmployeeInfo.getNoOfDays();
		String sqlCommand=null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		MakeRequiredQuery makeRequiredQuery=new MakeRequiredQuery();
		

		if(designation.equals("TL"))
		{
			sqlCommand=makeRequiredQuery.queryForTL();
			boolean notification=updateLeaveStatus(requestedEmployeeInfo,sqlCommand);
			if(notification==true){
				System.out.println("Mail----->"+requestedEmployeeInfo.getEmail()+"<-----------");
			sendMail.send("dummytesting799@gmail.com","raghava@799",requestedEmployeeInfo.getEmail(),"Leave Notification","your leave has been successfully accepted by your:  "+ designation);
			return true; 
			}
		}
		if(designation.equals("PM"))
		{
			sqlCommand=makeRequiredQuery.queryForPM();
			boolean notification=updateLeaveStatus(requestedEmployeeInfo,sqlCommand);
			if(notification==true){
			sendMail.send("dummytesting799@gmail.com","raghava@799",requestedEmployeeInfo.getEmail(),"Leave Notification","your leave has been successfully accepted by your:  "+ designation);
			return true; 
			}	
		}
		if(designation.equals("HR"))
		{
				sqlCommand=makeRequiredQuery.queryForHR(leaveType);
				
			
			
	//		if(leaveType.equals("sick"))
	//			sqlCommand="select availed_leaves,total_bal_leaves,bal_in_sick from  raghava_empleaveportal_leaves_info where emp_id=?";
	//		if(leaveType.equals("casual"))
	//			sqlCommand="select availed_leaves,total_bal_leaves,bal_in_casual from  raghava_empleaveportal_leaves_info where emp_id=?";
	//		if(leaveType.equals("privilege"))
	//			sqlCommand="select availed_leaves,total_bal_leaves,bal_in_privilege from  raghava_empleaveportal_leaves_info where emp_id=?";
			
			try {
				
				Connection connection=getConnection();
				stmt=connection.prepareStatement(sqlCommand);
				
				stmt.setString(1, emp_id);
				stmt.setInt(2, noOfDays);
				stmt.setInt(3, noOfDays);
				stmt.setInt(4, noOfDays);
				stmt.setString(5, emp_id);
				stmt.setString(6, emp_id);
	
				int flagg=stmt.executeUpdate();
				System.out.println("Going to send mail here.......!!!  in accept  in HR --->: " + flagg + "<----  ********");

				if(flagg>=1){
					sendMail.send("dummytesting799@gmail.com","raghava@799",requestedEmployeeInfo.getEmail(),"Leave Notification","your leave has been successfully accepted by your:  "+ designation);
				return true;
				}	
				else return false;
				//return stmt.execute();
				
					
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DaoException("SQLException in selectMessage():", e);
			} finally {
				close(stmt, rs);
			}
		}
		else return false;
	}
	
	////this is for TL and PM to update
	
	private boolean updateLeaveStatus(
			RequestedEmployeeInfo requestedEmployeeInfo,String sqlCommand) throws DaoException  {


		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String emp_id=requestedEmployeeInfo.getEmp_id();		
		try {
			
			Connection connection=getConnection();
			stmt=connection.prepareStatement(sqlCommand);
			stmt.setString(1, emp_id);
			int noOfrowsAffected=stmt.executeUpdate();
			System.out.println("Going to send mail here.......!!!  in accept TL of PM-----> : " + noOfrowsAffected + " <---- ********");

			if(noOfrowsAffected==1){
			return true;
			}
			else return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("SQLException in selectMessage():", e);
		} finally {
			close(stmt, rs);
		}

	}
	/// to reject
	public boolean rejectLeaveDao(RequestedEmployeeInfo requestedEmployeeInfo, String designation) throws DaoException{
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCommand = null;
		
		MakeRequiredQuery makeRequiredQuery=new MakeRequiredQuery();

		if(designation.equals("TL"))
		{
			sqlCommand=makeRequiredQuery.queryForRejectTL();

		}
		if(designation.equals("PM"))
		{
			sqlCommand=makeRequiredQuery.queryForRejectPM();
	
		}
		if(designation.equals("HR"))
		{
			sqlCommand=makeRequiredQuery.queryForRejectHR();
		}
		try {
			
			Connection connection=getConnection();
			stmt=connection.prepareStatement(sqlCommand);
			stmt.setString(1, requestedEmployeeInfo.getEmp_id());

			int flagg=stmt.executeUpdate();
			System.out.println("Going to send mail here in reject.......!!!  : " + flagg + "  ********");

			if(flagg==1){

			sendMail.send("dummytesting799@gmail.com","raghava@799",requestedEmployeeInfo.getEmail(),"Leave Notification","your leave has been rejected by your:  "+ designation);

			return true;
			}	
			else return false;
			//return stmt.execute();	
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("SQLException in selectMessage():", e);
		} finally {
			close(stmt, rs);
		}
		
	}
	public EmployeeLeaveInfo getSerchEpmInfoDao(String emp_id) throws DaoException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCommand = null;
		try {
			sqlCommand="select l.emp_id,l.availed_leaves,l.bal_in_sick,l.bal_in_casual,l.bal_in_privilege,l.total_bal_leaves,k.emp_fName,k.emp_lName  from raghava_empleaveportal_leaves_info as l join raghava_EmpLeavePortal_EmpInfo as k on l.emp_id=k.emp_id where k.emp_id=? or k.emp_fName like ? or k.emp_lName like ?";
			Connection connection=getConnection();
			stmt=connection.prepareStatement(sqlCommand);
			stmt.setString(1, emp_id);
			stmt.setString(2, emp_id+'%');
			stmt.setString(3, emp_id+'%');

			rs= stmt.executeQuery();
			if(rs.next())
				return new EmployeeLeaveInfo(rs.getString(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getString(7)+"    "+rs.getString(8));
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("SQLException in selectMessage():", e);
		} finally {
			close(stmt, rs);
		}
		
	}
	public List<EmployeeLeaveList> getSerchEpmLeaveListDao(String emp_id) throws DaoException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCommand = null;
		List<EmployeeLeaveList> employeeLeaveList=new ArrayList<EmployeeLeaveList>();
		try {
			sqlCommand="select * from raghava_empleaveportal_leave_request_info where emp_id=? order by leave_id desc;";
			Connection connection=getConnection();
			stmt=connection.prepareStatement(sqlCommand);
			stmt.setString(1, emp_id);

			rs= stmt.executeQuery();
			while(rs.next())
			{
				employeeLeaveList.add(new EmployeeLeaveList(rs.getInt(2),rs.getString(3),rs.getDate(4),rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getDate(10)));
			}
			return employeeLeaveList;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("SQLException in selectMessage():", e);
		} finally {
			close(stmt, rs);
		}
		
	}
	public boolean addEmpInfoDao(AddEmpInfo addEmpInfo) throws DaoException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCommand = null;
		//MakeRequiredQuery makeRequiredQuery=new MakeRequiredQuery();
		try {
			sqlCommand="insert into raghava_EmpLeavePortal_EmpInfo values(?,?,?,?,?,?,?);";
			Connection connection=getConnection();

			stmt=connection.prepareStatement(sqlCommand);
			stmt.setString(1, addEmpInfo.getEmpID());
			stmt.setString(2, addEmpInfo.getFirstName());
			stmt.setString(3, addEmpInfo.getLastName());
			stmt.setString(4, addEmpInfo.getDesignation());
			stmt.setString(5, addEmpInfo.getMobileNumber());
			stmt.setString(6, addEmpInfo.getEmail());
			stmt.setString(7, addEmpInfo.getProject());
			int flagg=stmt.executeUpdate();
			if(flagg==1){
			return insertUnamePwd(addEmpInfo.getUserName(),addEmpInfo.getPassword()
					,addEmpInfo.getEmpID(),connection);
			}	
			else return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("SQLException in selectMessage():", e);
		} finally {
			close(stmt, rs);
		}		
	}
	private boolean insertUnamePwd(String userName, String password,
			String empId, Connection connection) throws DaoException{
		PreparedStatement stmt = null;

		String sqlCommand = "insert into raghava_EmpLeavePortal_LoginCredentials values(?,?,?);";
		try {
			stmt=connection.prepareStatement(sqlCommand);
			stmt.setString(1,userName);
			stmt.setString(2,password);
			stmt.setString(3,empId);

			int rowsAffected= stmt.executeUpdate();
			if(rowsAffected==1)
				return updateInLeavesInfo(empId,connection);
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("SQLException in selectMessage():", e);
		}finally {
			close(stmt);
		}
	}
	private boolean updateInLeavesInfo(String empId, Connection connection) throws DaoException {
		PreparedStatement stmt = null;

		String sqlCommand = "insert into raghava_empleaveportal_leaves_info values(?,0,11,5,5,21);";
		try {
			stmt=connection.prepareStatement(sqlCommand);
			stmt.setString(1,empId);
			int rowsAffected= stmt.executeUpdate();
			if(rowsAffected==1)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("SQLException in selectMessage():", e);
		}finally {
			close(stmt);
		}
	}
	public ResponseToForgotPassword forgotPasswordDao(
			ForgotPasswordInfo forgotPasswordInfo) throws DaoException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCommand = null;
		String emp_id=forgotPasswordInfo.getEmp_id();
		String email=forgotPasswordInfo.getEmail();
		String mobile=forgotPasswordInfo.getMobile();
		String userName=forgotPasswordInfo.getUserName();
		try {
			sqlCommand="select i.emp_contactNo,i.email,l.user_name from raghava_EmpLeavePortal_EmpInfo as i join raghava_EmpLeavePortal_LoginCredentials as l on i.emp_id=l.emp_id where i.emp_id=?";
			Connection connection=getConnection();
			stmt=connection.prepareStatement(sqlCommand);
			stmt.setString(1, emp_id);
			rs= stmt.executeQuery();
			if(rs.next())
			{
				if(mobile.equals(rs.getString(1)) && email.equals(rs.getString(2)) && userName.equals(rs.getString(3)) ){
					return new ResponseToForgotPassword(true,emp_id);
				}
				else
					return null;
			}
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("SQLException in selectMessage():", e);
		} finally {
			close(stmt, rs);
		}
		
	}
	public boolean resetPasswordDao(NewPasswordInfo newPasswordInfo) throws DaoException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCommand = null;
		//MakeRequiredQuery makeRequiredQuery=new MakeRequiredQuery();
		try {
			sqlCommand="update raghava_EmpLeavePortal_LoginCredentials set password=? where emp_id=?;";
			Connection connection=getConnection();
			System.out.println("***********************************************");
			System.out.println("see here ----emp id------->>>>"+newPasswordInfo.getEmpID());
			System.out.println("see here ----emp id------->>>>"+newPasswordInfo.getcPassWd());

			stmt=connection.prepareStatement(sqlCommand);
			stmt.setString(1, newPasswordInfo.getcPassWd());
			stmt.setString(2, newPasswordInfo.getEmpID());
			
			int flagg=stmt.executeUpdate();
			if(flagg==1){
			return true;
			}	
			else return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("SQLException in selectMessage():", e);
		} finally {
			close(stmt, rs);
		}		
	}
	public EmployeeProfile getEmpProfileDao(String emp_id) throws DaoException {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCommand = null;
		try {
			sqlCommand="select * from raghava_EmpLeavePortal_EmpInfo where emp_id=?";
			Connection connection=getConnection();
			stmt=connection.prepareStatement(sqlCommand);
			stmt.setString(1, emp_id);
			
			rs= stmt.executeQuery();
			if(rs.next())
				return new EmployeeProfile(rs.getString(1),rs.getString(2)+"   "+rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
			else
				return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("SQLException in selectMessage():", e);
		} finally {
			close(stmt, rs);
		}
		
	}
	public boolean editProfileDao(EmployeeProfile employeeProfile) throws DaoException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sqlCommand = null;
		
		try {
			sqlCommand="update  raghava_EmpLeavePortal_EmpInfo set emp_designation=?,emp_contactNo=?,email=?,project_name=? where emp_id=?;";
			Connection connection=getConnection();
			
			stmt=connection.prepareStatement(sqlCommand);
			stmt.setString(1, employeeProfile.getDesignation());
			stmt.setString(2, employeeProfile.getMobile());
			stmt.setString(3, employeeProfile.getEmail());
			stmt.setString(4, employeeProfile.getProject());
			stmt.setString(5, employeeProfile.getEmp_id());

			int flagg=stmt.executeUpdate();
			if(flagg==1){
			return true;
			}	
			else return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException("SQLException in selectMessage():", e);
		} finally {
			close(stmt, rs);
		}		
	}
}
