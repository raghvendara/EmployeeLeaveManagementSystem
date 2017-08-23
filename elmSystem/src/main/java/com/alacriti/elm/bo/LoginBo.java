package com.alacriti.elm.bo;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import com.alacriti.elm.dao.AdminDao;
import com.alacriti.elm.dao.DaoException;
import com.alacriti.elm.dao.LoginDao;
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

public class LoginBo extends BaseBO {
	public static final Logger log= Logger.getLogger(LoginBo.class);


	public LoginBo(Connection connection) {
		super(connection);
	}
	AdminDao adminDao;
	LoginDao sampleDao;


	public ResponseToLoginPost loginBo(UserLoginInfo userLoginInfo) throws BoException
	{
		log.debug("in loginBo");
		ResponseToLoginPost responseToPost=null;
		
		try {
			sampleDao = new LoginDao(getConnection());
			responseToPost = sampleDao.loginDao(userLoginInfo);
		} catch (DaoException e) {
			log.error("Exception occured ",e);
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception occured ",e);
			throw new BoException();
		}
		
		
		return responseToPost;
	}

	public List<RequestedEmployeeInfo> requestedEmployeeInfoBO(
			String projectName, String designation) throws BoException {
		log.debug("in requestedEmployeeInfoBO");

		List<RequestedEmployeeInfo> emp_list=null;
				
		try {
			adminDao = new AdminDao(getConnection());
			emp_list = adminDao.getRequestedEmployeeInfoDao(projectName,designation);
		} catch (DaoException e) {
			log.error("Exception occured ",e);
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception occured ",e);
			throw new BoException();
		}
		return emp_list;
	}

	public boolean acceptLeaveBO(
			RequestedEmployeeInfo requestedEmployeeInfo,String designation) throws BoException {
		log.debug("in acceptLeaveBO");

		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.acceptLeaveDao(requestedEmployeeInfo,designation);
		} catch (DaoException e) {
			log.error("Exception occured ",e);
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception occured ",e);
			throw new BoException();
		}
		
		
	}

	public boolean rejectLeaveBO(RequestedEmployeeInfo requestedEmployeeInfo, String designation) throws BoException {
		log.debug("in rejectLeaveBO");

		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.rejectLeaveDao(requestedEmployeeInfo,designation);
		} catch (DaoException e) {
			log.error("Exception occured ",e);
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception occured ",e);
			throw new BoException();
		}
	}

	public EmployeeLeaveInfo getSerchEpmInfoBO(String emp_id) throws BoException{
		log.debug("in getSerchEpmInfoBO");

		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.getSerchEpmInfoDao(emp_id);
		} catch (DaoException e) {
			log.error("Exception occured ",e);
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception occured ",e);
			throw new BoException();
		}
		
		
	}

	public List<EmployeeLeaveList> getEpmLeaveListBO(String emp_id) throws BoException {
		log.debug("in getEpmLeaveListBO");

		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.getSerchEpmLeaveListDao(emp_id);
		} catch (DaoException e) {
			log.error("Exception occured ",e);
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception occured ",e);
			throw new BoException();
		}
	}

	public boolean addEmpInfoBO(AddEmpInfo addEmpInfo) throws BoException {
		log.debug("in addEmpInfoBO");

		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.addEmpInfoDao(addEmpInfo);
		} catch (DaoException e) {
			log.error("Exception occured ",e);
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception occured ",e);
			throw new BoException();
		}	
	}

	public ResponseToForgotPassword forgotPasswordBo(
			ForgotPasswordInfo forgotPasswordInfo) throws BoException  {
		log.debug("in forgotPasswordBo");

		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.forgotPasswordDao(forgotPasswordInfo);
		} catch (DaoException e) {
			log.error("Exception occured ",e);
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception occured ",e);
			throw new BoException();
		}	
	}

	public boolean resetPasswordBo(NewPasswordInfo newPasswordInfo) throws BoException{
		log.debug("in resetPasswordBo");

		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.resetPasswordDao(newPasswordInfo);
		} catch (DaoException e) {
			log.error("Exception occured ",e);
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception occured ",e);
			throw new BoException();
		}	
	}

	public EmployeeProfile getEmpProfileBO(String emp_id)throws BoException {
		log.debug("in getEmpProfileBO");

		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.getEmpProfileDao(emp_id);
		} catch (DaoException e) {
			log.error("Exception occured ",e);
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception occured ",e);
			throw new BoException();
		}	
	}

	public boolean editProfileBO(EmployeeProfile employeeProfile) throws BoException{
		log.debug("in editProfileBO");

		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.editProfileDao(employeeProfile);
		} catch (DaoException e) {
			log.error("Exception occured ",e);
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception occured ",e);
			throw new BoException();
		}	
		
	}

	public boolean empIDForValidationBO(String empID) throws BoException {
		log.debug("in empIDForValidationBO");

		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.validationForEmpIDDao(empID);
		} catch (DaoException e) {
			log.error("Exception occured ",e);
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception occured ",e);
			throw new BoException();
		}	
	}

	public boolean userNameForValidationBO(String userName) throws BoException{
		log.debug("in userNameForValidationBO");

		try {
			adminDao = new AdminDao(getConnection());
			return adminDao.validationForUserNameDao(userName);
		} catch (DaoException e) {
			log.error("Exception occured ",e);
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception occured ",e);
			throw new BoException();
		}	
	}
	
	
}
