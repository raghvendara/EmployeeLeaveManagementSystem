package com.alacriti.elm.bo;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.alacriti.elm.dao.DaoException;
import com.alacriti.elm.dao.UserDao;
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

	public boolean LeaveApplicationBo(
			LeaveApplicationDetails leaveApplicationDetails) throws BoException {
		log.debug("in LeaveApplicationBo");
		try {
			userDao = new UserDao(getConnection());
			return userDao.LeaveApplicationDao(leaveApplicationDetails);
		} catch (DaoException e) {
			log.error("Exception occured ",e);
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			log.error("Exception occured ",e);
			throw new BoException();
		}
	}
	

}
