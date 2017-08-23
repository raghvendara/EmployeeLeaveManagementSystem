package com.alacriti.elm.bo;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.alacriti.elm.dao.DaoException;
import com.alacriti.elm.dao.UserDao;
import com.alacriti.elm.resteasy.modelClasses.EmployeeLeaveInfo;
import com.alacriti.elm.resteasy.modelClasses.LeaveApplicationDetails;

public class UserBo extends BaseBO{
	public static final Logger log= Logger.getLogger(UserBo.class);

	public UserBo(Connection connection) {
		super(connection);
	}

	public UserBo() {
		super();
	}
	UserDao userDao;

	public boolean leaveApplicationBo(
			LeaveApplicationDetails leaveApplicationDetails) throws BoException {
		log.debug("in LeaveApplicationBo");
		try {
			userDao = new UserDao(getConnection());
			return userDao.leaveApplicationDao(leaveApplicationDetails);
		} catch (DaoException e) {
			log.error("Exception occured ",e);
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception occured ",e);
			throw new BoException();
		}
	}

	public EmployeeLeaveInfo leaveValidationBo(String empId) throws BoException{
		log.debug("in LeaveValidationBo");
		try {
			userDao = new UserDao(getConnection());
			return userDao.leaveValidationDao(empId);
		} catch (DaoException e) {
			log.error("Exception occured ",e);
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception occured ",e);
			throw new BoException();
		}
	}
	

}
