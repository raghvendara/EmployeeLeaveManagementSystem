package com.alacriti.elm.resteasy.resourceDeligate;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.alacriti.elm.datasource.MySqlDataSource;


public class BaseDeligate {
	public static final Logger log= Logger.getLogger(BaseDeligate.class);

	private Connection connection;

	public void setConnection(Connection _connection) {
		this.connection = _connection;
	}

	public Connection getConnection() {
		return connection;
	}

	protected void endDBTransaction(Connection connection) {
		log.debug("in endDBTransaction");
		try {
			connection.commit();

		} catch (SQLException e) {
			log.error("Exception occured ",e);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				log.error("Exception occured ",e);
			}
		} catch (Exception e) {
			log.error("Exception occured ",e);
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				log.error("Exception occured ",e);
			}
		}

	}

	protected void endDBTransaction(Connection connection, boolean isRollback) {
		log.debug("in endDBTransaction for roll back");

		if (isRollback) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				log.error("Exception occured ",e);
			}

			finally {
				try {
					if (connection != null)
						connection.close();
				} catch (SQLException e) {
					log.error("Exception occured ",e);
				}
			}
		} else {
			endDBTransaction(connection);
		}

	}

	protected Connection startDBTransaction() {
		log.debug("in startDBTransaction");

		Connection conn = null;
		try {
			if (conn == null || conn.isClosed())
				conn = MySqlDataSource.getInstance().getConnection();

			conn.setAutoCommit(false);
		} catch (SQLException e) {
			log.error("Exception occured ",e);
		}
		return conn;

	}

	
}
