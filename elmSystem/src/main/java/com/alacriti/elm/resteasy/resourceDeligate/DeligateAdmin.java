package com.alacriti.elm.resteasy.resourceDeligate;

import java.sql.Connection;
import java.util.List;

import com.alacriti.elm.bo.SampleBo;
import com.alacriti.elm.resteasy.modelClasses.AddEmpInfo;
import com.alacriti.elm.resteasy.modelClasses.EmployeeLeaveInfo;
import com.alacriti.elm.resteasy.modelClasses.EmployeeLeaveList;
import com.alacriti.elm.resteasy.modelClasses.EmployeeProfile;
import com.alacriti.elm.resteasy.modelClasses.RequestedEmployeeInfo;

public class DeligateAdmin extends BaseDeligate{

	public List<RequestedEmployeeInfo> deligateRequestedEmployeeInfo(String projectName, String designation)
	{
		
		List<RequestedEmployeeInfo> emp_list=null;
	
		boolean rollBack = false;
		Connection connection = null;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			SampleBo sampleBO = new SampleBo(connection);
			emp_list = sampleBO.requestedEmployeeInfoBO(projectName,designation);
		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return emp_list;
		
	}

	public boolean deligateAcceptLeave(RequestedEmployeeInfo requestedEmployeeInfo, String designation) {
//		boolean falg=false;
//		ResponseToAccept responseToAccept=null;
		boolean rollBack = false;
		Connection connection = null;
		SampleBo sampleBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			sampleBO = new SampleBo(connection);
			
			return sampleBO.acceptLeaveBO(requestedEmployeeInfo,designation);

		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
			return false;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		//return false;
		//return false;
		
	}

	public boolean deligateRejectLeave(String emp_id, String designation) {
		
		boolean rollBack = false;
		Connection connection = null;
		SampleBo sampleBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			sampleBO = new SampleBo(connection);
			
			return sampleBO.rejectLeaveBO(emp_id,designation);

		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
			return false;
		} finally {
			endDBTransaction(connection, rollBack);
		}
	}

	public EmployeeLeaveInfo deligateRequestedEmployeeInfoforSearch(
			String emp_id) {
		
		EmployeeLeaveInfo emp_info=null;
		
		boolean rollBack = false;
		Connection connection = null;
		SampleBo sampleBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			sampleBO = new SampleBo(connection);
			
			emp_info=sampleBO.getSerchEpmInfoBO(emp_id);

		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return emp_info;
	}

	public List<EmployeeLeaveList> deligateRequestedEmployeeLeaveList(
			String emp_id) {
		
		boolean rollBack = false;
		Connection connection = null;
		SampleBo sampleBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			sampleBO = new SampleBo(connection);
			
			return sampleBO.getEpmLeaveListBO(emp_id);

		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		return null;
		
	}

	public boolean deligateAddEmpInfo(AddEmpInfo addEmpInfo) {
		boolean rollBack = false;
		Connection connection = null;
		SampleBo sampleBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			sampleBO = new SampleBo(connection);
			
			return sampleBO.addEmpInfoBO(addEmpInfo);

		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
			return false;
		} finally {
			endDBTransaction(connection, rollBack);
		}
	}

	public EmployeeProfile deligateGetEmpProfile(String emp_id) {
		boolean rollBack = false;
		Connection connection = null;
		SampleBo sampleBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			sampleBO = new SampleBo(connection);
			
			return sampleBO.getEmpProfileBO(emp_id);

		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
			return null;

		} finally {
			endDBTransaction(connection, rollBack);
		}
		
	}

	public boolean deligateEditProfile(EmployeeProfile employeeProfile) {
		boolean rollBack = false;
		Connection connection = null;
		SampleBo sampleBO;
		try {
			connection = startDBTransaction();
			setConnection(connection);
			sampleBO = new SampleBo(connection);
			
			return sampleBO.editProfileBO(employeeProfile);

		} catch (Exception e) {
			e.printStackTrace();
			rollBack = true;
			return false;
		} finally {
			endDBTransaction(connection, rollBack);
		}
		
	}
}
