package com.serb_backend.dal.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
public interface ConnectionProvider {
	public Connection getConnection(String jdbcUrl, String username, String passsword, int numberOfCores)
			throws SQLException;
	public Connection getConnection(String username, String passsword, int numberOfCores)
			throws SQLException;
	public DataSource getDataSource(String jdbcUrl, String username, String passsword, int numberOfCores);
}
