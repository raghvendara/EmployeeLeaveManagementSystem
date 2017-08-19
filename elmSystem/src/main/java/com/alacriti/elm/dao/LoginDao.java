package com.alacriti.elm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.alacriti.elm.resteasy.modelClasses.ResponseToLoginPost;
import com.alacriti.elm.resteasy.modelClasses.UserLoginInfo;

public class LoginDao extends BaseDao  {

		public LoginDao() {
		}

		public LoginDao(Connection conn) {
			super(conn);
		}
		public static final Logger log= Logger.getLogger(LoginDao.class);

		public ResponseToLoginPost loginDao(UserLoginInfo userLoginInfo) throws DaoException {
			
			log.debug("in loginDao");
			PreparedStatement stmt = null;
			ResultSet rs = null;
			Connection connection = null;
			
			String userName=userLoginInfo.getUserName();
			String password=userLoginInfo.getPassword();
			String emp_id=null;
			String fullName=null;
			String designation=null;
			String projectName=null;
			boolean flag=false;
			String sqlCommand="select l.emp_id,l.user_name,l.password,i.emp_fName,i.emp_lName,i.emp_designation,i.project_name from raghava_EmpLeavePortal_LoginCredentials as l join raghava_EmpLeavePortal_EmpInfo as i on l.emp_id=i.emp_id where l.user_name=? and l.password=?;";

			try {
				connection = getConnection();
				stmt=connection.prepareStatement(sqlCommand);
				stmt.setString(1, userName);
				stmt.setString(2, password);

				rs= stmt.executeQuery();
				
				if(rs.next())
				{
					emp_id=rs.getString(1);
					if(rs.getString(5)!=null)
						fullName=rs.getString(4)+"    "+rs.getString(5);
					else
						fullName=rs.getString(4);
					designation=rs.getString(6);
					projectName=rs.getString(7);
					flag=true;
				}	
					
			} catch (SQLException e) {
				log.error("Exception occured ",e);
				throw new DaoException("SQLException in selectMessage():", e);

			} finally {
				close(stmt, rs);
			}
			return new ResponseToLoginPost(flag,emp_id,fullName,designation,projectName);
		}
		
	}	