package com.alacriti.elm.resteasy.resourceDeligate;

import java.sql.Connection;
import java.sql.SQLException;

import com.alacriti.elm.datasource.MySqlDataSource;


public class BaseDeligate {
	private Connection connection;

	public void setConnection(Connection _connection) {
		this.connection = _connection;
	}

	public Connection getConnection() {
		return connection;
	}

	protected void endDBTransaction(Connection connection) {
		try {
			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
			}
		} catch (Exception e) {
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
			}
		}

	}

	protected void endDBTransaction(Connection connection, boolean isRollback) {

		if (isRollback) {
			try {
				connection.rollback();
			} catch (SQLException e) {
			}

			finally {
				try {
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
				}
			}
		} else {
			endDBTransaction(connection);
		}

	}

	protected Connection startDBTransaction() {
		Connection conn = null;
		try {
			if (conn == null || conn.isClosed())
				conn = MySqlDataSource.getInstance().getConnection();

			conn.setAutoCommit(false);
		} catch (SQLException e) {
		}
		return conn;

	}

	
}
