package com.alacriti.elm.dao;

import java.sql.SQLException;



public class DaoException extends Exception {

	private static final long serialVersionUID = -215067414094112851L;
	public DaoException(String message) {
		super(message);
	}
	public DaoException(String string, SQLException e)
	{
		e.printStackTrace();
	}
}
