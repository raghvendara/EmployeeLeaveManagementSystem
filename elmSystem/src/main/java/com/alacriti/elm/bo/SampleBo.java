package com.alacriti.elm.bo;

import java.sql.Connection;
import java.util.List;

import com.alacriti.elm.dao.AdminDao;
import com.alacriti.elm.dao.DaoException;
import com.alacriti.elm.dao.SampleDao;
import com.alacriti.elm.resteasy.modelClasses.AddEmpInfo;
import com.alacriti.elm.resteasy.modelClasses.EmployeeLeaveInfo;
import com.alacriti.elm.resteasy.modelClasses.EmployeeLeaveList;
import com.alacriti.elm.resteasy.modelClasses.EmployeeProfile;
import com.alacriti.elm.resteasy.modelClasses.ForgotPasswordInfo;
import com.alacriti.elm.resteasy.modelClasses.NewPasswordInfo;
import com.alacriti.elm.resteasy.modelClasses.RequestedEmployeeInfo;
import com.alacriti.elm.resteasy.modelClasses.ResponseToForgotPassword;
import com.alacriti.elm.resteasy.modelClasses.ResponseToLoginPost;
import com.alacriti.elm.resteasy.modelClasses.UserLoginInfo;

public class SampleBo extends BaseBO {

	public SampleBo(Connection connection) {
		super(connection);
	}
	AdminDao adminDao;
	SampleDao sampleDao;


	public ResponseToLoginPost loginBo(UserLoginInfo userLoginInfo) throws BoException
	{
		ResponseToLoginPost responseToPost=null;
		
		try {
			sampleDao = new SampleDao(getConnection());
			responseToPost = sampleDao.loginDao(userLoginInfo);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoException();
		}
		
		
		return responseToPost;
	}

	public List<RequestedEmployeeInfo> requestedEmployeeInfoBO(
			String projectName, String designation) throws BoException {
		
		List<RequestedEmployeeInfo> emp_list=null;
				
		try {
			adminDao = new AdminDao(getConnection());
			emp_list = adminDao.getRequestedEmployeeInfoDao(projectName,designation);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoException();
		}
		return emp_list;
	}

	public boolean acceptLeaveBO(
			RequestedEmployeeInfo requestedEmployeeInfo,String designation) throws BoException {
		
		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.acceptLeaveDao(requestedEmployeeInfo,designation);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoException();
		}
		
		
	}

	public boolean rejectLeaveBO(String emp_id, String designation) throws BoException {
		
		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.rejectLeaveDao(emp_id,designation);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoException();
		}
	}

	public EmployeeLeaveInfo getSerchEpmInfoBO(String emp_id) throws BoException{
				
		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.getSerchEpmInfoDao(emp_id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoException();
		}
		
		
	}

	public List<EmployeeLeaveList> getEpmLeaveListBO(String emp_id) throws BoException {
		
		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.getSerchEpmLeaveListDao(emp_id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoException();
		}
	}

	public boolean addEmpInfoBO(AddEmpInfo addEmpInfo) throws BoException {
		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.addEmpInfoDao(addEmpInfo);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoException();
		}	
	}

	public ResponseToForgotPassword forgotPasswordBo(
			ForgotPasswordInfo forgotPasswordInfo) throws BoException  {
		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.forgotPasswordDao(forgotPasswordInfo);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoException();
		}	
	}

	public boolean resetPasswordBo(NewPasswordInfo newPasswordInfo) throws BoException{
		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.resetPasswordDao(newPasswordInfo);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoException();
		}	
	}

	public EmployeeProfile getEmpProfileBO(String emp_id)throws BoException {
		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.getEmpProfileDao(emp_id);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoException();
		}	
	}

	public boolean editProfileBO(EmployeeProfile employeeProfile) throws BoException{
		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.editProfileDao(employeeProfile);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoException();
		}	
		
	}
	
	
}
