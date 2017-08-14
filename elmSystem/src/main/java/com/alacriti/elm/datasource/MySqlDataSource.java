package com.alacriti.elm.datasource;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class MySqlDataSource {
	private static MySqlDataSource ms_this = null;
	private static DataSource dbSource = null;

	private MySqlDataSource() {
		init();
	}

	protected void init() {
		initialize();
	}

	public static MySqlDataSource getInstance() {
		synchronized (MySqlDataSource.class) {
			if (ms_this == null) {
				synchronized (MySqlDataSource.class) {
					ms_this = new MySqlDataSource();
				}
			}
		}

		return ms_this;
	}

	protected void initialize() {
		try {
			if (dbSource == null) {
				InitialContext aInitialContext = new InitialContext();
				dbSource = (DataSource) aInitialContext.lookup("java:jboss/datasources/TRAINEE");

			}
		} catch (NamingException e) {
		} catch (Exception e) {
		}
	}

	public Connection getConnection() {
		try {
			Connection dbCon = dbSource.getConnection();
			dbCon.setAutoCommit(false);
			/*
			 * if (isConneLeakChkEnabled) { Handler handler = new
			 * Handler(dbCon); Connection mapProxy = (Connection)
			 * Proxy.newProxyInstance(handler.getClass().getClassLoader(), new
			 * Class[] { Connection.class }, handler); dbCon = mapProxy;
			 * 
			 * synchronized (connLeakVarlock) { totalOpenConnCnt++;
			 * connOpenedNow++; handler.setId("" + totalOpenConnCnt); String
			 * localStackTrace = ExceptionUtil .getStackTrace(new
			 * Exception("No Problem in this exception.")).replaceAll("\\n",
			 * ""); openConnIdMap.put(handler.getId(), localStackTrace); if
			 * (localStackTrace.length() > 500) { localStackTrace =
			 * localStackTrace.substring(0, 499); }
			 * connLog.info("::Connection pulled:" + totalOpenConnCnt + "::" +
			 * (-totalClosedConnCnt) + ">" + connOpenedNow + ":\n" +
			 * localStackTrace);
			 * 
			 * } }
			 */
			return dbCon;
		} catch (Exception e) {
		}
		return null;
	
}
}
