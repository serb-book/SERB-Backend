package com.serb.serb_backend.dal.util;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionProvider {
	public Connection getConnection(String jdbcUrl, String username, String passsword, int numberOfCores)
			throws SQLException;
	public Connection getConnection(String username, String passsword, int numberOfCores)
			throws SQLException;
}
