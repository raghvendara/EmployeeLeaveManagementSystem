package com.alacriti.elm.resteasy.resourceDeligate;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.alacriti.elm.bo.UserBo;
import com.alacriti.elm.resteasy.modelClasses.LeaveApplicationDetails;

public class UserDeligate extends BaseDeligate{

	public static final Logger log= Logger.getLogger(UserDeligate.class);

	public boolean deligateLeaveApplication(
			LeaveApplicationDetails leaveApplicationDetails) {
		log.debug("in deligateLeaveApplication");
		
		boolean flag=false;
		boolean rollBack = false;
		Connection connection = null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			UserBo userBO = new UserBo(connection);
			flag= userBO.LeaveApplicationBo(leaveApplicationDetails);
		} catch (Exception e) {
			log.error("Exception occured ",e);
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return flag;
	}
	

}
