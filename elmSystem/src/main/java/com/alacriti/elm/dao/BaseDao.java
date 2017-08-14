package com.alacriti.elm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class BaseDao {
	public Long auditEventTransactiondId;
	public int auditEventId;
	private Connection connection;

	public BaseDao() {

	}

	public BaseDao(Connection _connection) {
		this.connection = _connection;

	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}

	public void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}

	public void close(PreparedStatement stmt, ResultSet rs) {
		close(stmt);
		close(rs);

	}

	protected PreparedStatement getPreparedStatement(Connection connection, String sqlCmd) throws SQLException {


		try {

			return connection.prepareStatement(sqlCmd);
		} catch (SQLException e) {
			e.getMessage();
			throw e;
		}
	}

	protected PreparedStatement getPreparedStatementReturnPK(Connection connection, String sqlCmd) throws SQLException {

		try {

			return connection.prepareStatement(sqlCmd, Statement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			e.getMessage();
			throw e;
		}
	}

	protected ResultSet executeQuery(PreparedStatement ps) throws SQLException {

		try {

			return ps.executeQuery();
		} catch (SQLException e) {
			e.getMessage();
			throw e;
		}
	}

	protected int executeUpdate(PreparedStatement ps) throws SQLException {

		try {

			return ps.executeUpdate();
		} catch (SQLException e) {
			e.getMessage();
			throw e;
		} finally {
			close(ps);
		}
	}


}
