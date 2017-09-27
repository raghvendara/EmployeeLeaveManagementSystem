package com.alacriti.elm.resteasy.resourceDeligate;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.elm.bo.LoginBo;
import com.alacriti.elm.resteasy.modelClasses.AddEmpInfo;
import com.alacriti.elm.resteasy.modelClasses.EmployeeLeaveInfo;
import com.alacriti.elm.resteasy.modelClasses.EmployeeLeaveList;
import com.alacriti.elm.resteasy.modelClasses.EmployeeProfile;
import com.alacriti.elm.resteasy.modelClasses.RequestedEmployeeInfo;
import com.alacriti.elm.utilities.MailingService;

public class DeligateAdmin extends BaseDeligate{
	public static final Logger log= Logger.getLogger(DeligateAdmin.class);
	MailingService sendMail=new MailingService();
	
	public List<RequestedEmployeeInfo> deligateRequestedEmployeeInfo(String projectName, String designation)
	{
		log.debug("in deligateRequestedEmployeeInfo");
		List<RequestedEmployeeInfo> emp_list=null;
	
		boolean rollBack = false;
		Connection connection = null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			LoginBo loginBO = new LoginBo(connection);
			emp_list = loginBO.requestedEmployeeInfoBO(projectName,designation);
		} catch (Exception e) {
			log.error("Exception occured ",e);
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return emp_list;
		
	}

	public boolean deligateAcceptLeave(RequestedEmployeeInfo requestedEmployeeInfo, String designation) {
		log.debug("in deligateAcceptLeave");
		
		boolean flagToAcceptLeave=false;
		boolean rollBack = false;
		Connection connection = null;
		LoginBo loginBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			loginBO = new LoginBo(connection);
			flagToAcceptLeave = loginBO.acceptLeaveBO(requestedEmployeeInfo,designation);
			if(flagToAcceptLeave)
				sendMail.send("dummytesting799@gmail.com","raghava@799",requestedEmployeeInfo.getEmail(),"Leave Notification","your leave has been successfully accepted by your:  "+ designation);
			return flagToAcceptLeave;

		} catch (Exception e) {
			log.error("Exception occured ",e);
			rollBack = true;
			return false;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		
	}

	public boolean deligateRejectLeave(RequestedEmployeeInfo requestedEmployeeInfo, String designation) {
		log.debug("in deligateRejectLeave");
		
		boolean flagToAcceptLeave=false;
		boolean rollBack = false;
		Connection connection = null;
		LoginBo loginBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			loginBO = new LoginBo(connection);
			
			flagToAcceptLeave= loginBO.rejectLeaveBO(requestedEmployeeInfo,designation);
			
			if(flagToAcceptLeave)
				sendMail.send("dummytesting799@gmail.com","raghava@799",requestedEmployeeInfo.getEmail(),"Leave Notification","your leave has been rejected by your:  "+ designation);

			return flagToAcceptLeave;

		} catch (Exception e) {
			log.error("Exception occured ",e);
			rollBack = true;
			return false;
		} finally {
			endDBTransaction(connection, rollBack);
		}
	}

	public List<EmployeeLeaveInfo> deligateRequestedEmployeeInfoforSearch(
			String emp_id) {
		log.debug("in deligateRequestedEmployeeInfoforSearch");		
		boolean rollBack = false;
		Connection connection = null;
		LoginBo loginBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			loginBO = new LoginBo(connection);
			return loginBO.getSerchEpmInfoBO(emp_id);
		} catch (Exception e) {
			log.error("Exception occured ",e);
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return null;
		
	}

	public List<EmployeeLeaveList> deligateRequestedEmployeeLeaveList(
			String emp_id) {
		log.debug("in deligateRequestedEmployeeLeaveList");

		boolean rollBack = false;
		Connection connection = null;
		LoginBo loginBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			loginBO = new LoginBo(connection);
			
			return loginBO.getEpmLeaveListBO(emp_id);

		} catch (Exception e) {
			log.error("Exception occured ",e);
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return null;
		
	}

	public boolean deligateAddEmpInfo(AddEmpInfo addEmpInfo) {
		log.debug("in deligateAddEmpInfo");

		boolean rollBack = false;
		Connection connection = null;
		LoginBo loginBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			loginBO = new LoginBo(connection);
			
			return loginBO.addEmpInfoBO(addEmpInfo);

		} catch (Exception e) {
			log.error("Exception occured ",e);
			rollBack = true;
			return false;
		} finally {
			endDBTransaction(connection, rollBack);
		}
	}

	public EmployeeProfile deligateGetEmpProfile(String emp_id) {
		log.debug("in deligateGetEmpProfile");

		boolean rollBack = false;
		Connection connection = null;
		LoginBo loginBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			loginBO = new LoginBo(connection);
			
			return loginBO.getEmpProfileBO(emp_id);

		} catch (Exception e) {
			log.error("Exception occured ",e);
			rollBack = true;
			return null;

		} finally {
			endDBTransaction(connection, rollBack);
		}
		
	}

	public boolean deligateEditProfile(EmployeeProfile employeeProfile) {
		log.debug("in deligateEditProfile");

		boolean rollBack = false;
		Connection connection = null;
		LoginBo loginBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			loginBO = new LoginBo(connection);
			
			return loginBO.editProfileBO(employeeProfile);

		} catch (Exception e) {
			log.error("Exception occured ",e);
			rollBack = true;
			return false;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		
	}

	public boolean deligateEmpIDForValidation(String empID) {
		log.debug("in deligateEmpIDForValidation");

		boolean rollBack = false;
		Connection connection = null;
		LoginBo loginBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			loginBO = new LoginBo(connection);
			
			return loginBO.empIDForValidationBO(empID);

		} catch (Exception e) {
			log.error("Exception occured ",e);
			rollBack = true;
			return false;
		} finally {
			endDBTransaction(connection, rollBack);
		}
	}

	public boolean deligateUserNameForValidation(String userName) {
		log.debug("in deligateUserNameForValidation");

		boolean rollBack = false;
		Connection connection = null;
		LoginBo loginBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			loginBO = new LoginBo(connection);
			
			return loginBO.userNameForValidationBO(userName);

		} catch (Exception e) {
			log.error("Exception occured ",e);
			rollBack = true;
			return false;
		} finally {
			endDBTransaction(connection, rollBack);
		}
	}
}
