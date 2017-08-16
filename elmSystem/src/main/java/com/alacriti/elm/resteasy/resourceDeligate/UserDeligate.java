package com.alacriti.elm.resteasy.resourceDeligate;

import java.sql.Connection;

import com.alacriti.elm.bo.UserBo;
import com.alacriti.elm.resteasy.modelClasses.LeaveApplicationDetails;

public class UserDeligate extends BaseDeligate{

	public boolean deligateLeaveApplication(
			LeaveApplicationDetails leaveApplicationDetails) {
		
		boolean flag=false;
		boolean rollBack = false;
		Connection connection = null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			UserBo userBO = new UserBo(connection);
			flag= userBO.LeaveApplicationBo(leaveApplicationDetails);
		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return flag;
	}
	

}
