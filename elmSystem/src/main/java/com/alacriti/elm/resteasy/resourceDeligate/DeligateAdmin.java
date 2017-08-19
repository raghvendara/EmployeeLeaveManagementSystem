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

public class DeligateAdmin extends BaseDeligate{
	public static final Logger log= Logger.getLogger(DeligateAdmin.class);


	public List<RequestedEmployeeInfo> deligateRequestedEmployeeInfo(String projectName, String designation)
	{
		log.debug("in deligateRequestedEmployeeInfo");
		List<RequestedEmployeeInfo> emp_list=null;
	
		boolean rollBack = false;
		Connection connection = null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			LoginBo sampleBO = new LoginBo(connection);
			emp_list = sampleBO.requestedEmployeeInfoBO(projectName,designation);
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

		boolean rollBack = false;
		Connection connection = null;
		LoginBo sampleBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			sampleBO = new LoginBo(connection);
			
			return sampleBO.acceptLeaveBO(requestedEmployeeInfo,designation);

		} catch (Exception e) {
			log.error("Exception occured ",e);
			rollBack = true;
			return false;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		//return false;
		//return false;
		
	}

	public boolean deligateRejectLeave(RequestedEmployeeInfo requestedEmployeeInfo, String designation) {
		log.debug("in deligateRejectLeave");

		boolean rollBack = false;
		Connection connection = null;
		LoginBo sampleBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			sampleBO = new LoginBo(connection);
			
			return sampleBO.rejectLeaveBO(requestedEmployeeInfo,designation);

		} catch (Exception e) {
			log.error("Exception occured ",e);
			rollBack = true;
			return false;
		} finally {
			endDBTransaction(connection, rollBack);
		}
	}

	public EmployeeLeaveInfo deligateRequestedEmployeeInfoforSearch(
			String emp_id) {
		log.debug("in deligateRequestedEmployeeInfoforSearch");

		EmployeeLeaveInfo emp_info=null;
		
		boolean rollBack = false;
		Connection connection = null;
		LoginBo sampleBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			sampleBO = new LoginBo(connection);
			
			emp_info=sampleBO.getSerchEpmInfoBO(emp_id);

		} catch (Exception e) {
			log.error("Exception occured ",e);
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return emp_info;
	}

	public List<EmployeeLeaveList> deligateRequestedEmployeeLeaveList(
			String emp_id) {
		log.debug("in deligateRequestedEmployeeLeaveList");

		boolean rollBack = false;
		Connection connection = null;
		LoginBo sampleBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			sampleBO = new LoginBo(connection);
			
			return sampleBO.getEpmLeaveListBO(emp_id);

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
		LoginBo sampleBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			sampleBO = new LoginBo(connection);
			
			return sampleBO.addEmpInfoBO(addEmpInfo);

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
		LoginBo sampleBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			sampleBO = new LoginBo(connection);
			
			return sampleBO.getEmpProfileBO(emp_id);

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
		LoginBo sampleBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			sampleBO = new LoginBo(connection);
			
			return sampleBO.editProfileBO(employeeProfile);

		} catch (Exception e) {
			log.error("Exception occured ",e);
			rollBack = true;
			return false;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		
	}
}
