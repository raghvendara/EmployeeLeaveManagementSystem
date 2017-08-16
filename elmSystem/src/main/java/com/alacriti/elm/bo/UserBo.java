package com.alacriti.elm.bo;

import java.sql.Connection;

import com.alacriti.elm.dao.DaoException;
import com.alacriti.elm.dao.UserDao;
import com.alacriti.elm.resteasy.modelClasses.LeaveApplicationDetails;

public class UserBo extends BaseBO{
	
	public UserBo(Connection connection) {
		super(connection);
	}

	public UserBo() {
		super();
	}
	UserDao userDao;

	public boolean LeaveApplicationBo(
			LeaveApplicationDetails leaveApplicationDetails) throws BoException {
		try {
			userDao = new UserDao(getConnection());
			return userDao.LeaveApplicationDao(leaveApplicationDetails);
		} catch (DaoException e) {
			e.printStackTrace();
			throw new BoException("DAOException Occured");
		} catch (Exception e) {
			e.printStackTrace();
			throw new BoException();
		}
	}
	

}
