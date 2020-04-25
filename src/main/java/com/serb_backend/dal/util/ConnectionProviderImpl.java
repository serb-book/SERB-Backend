package com.serb_backend.dal.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ConnectionProviderImpl implements ConnectionProvider {

	@Override
	public Connection getConnection(String jdbcUrl, String username, String passsword, int numberOfCores)
			throws SQLException {
		return DriverManager.getConnection(jdbcUrl, username, passsword);
	}

	@Override
	public Connection getConnection(String username, String passsword, int numberOfCores) throws SQLException {
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", username, passsword);
	}

	@Override
	public DataSource getDataSource(String jdbcUrl, String username, String passsword, int numberOfCores)
	throws SQLException{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		// dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(passsword);
		return dataSource;
	  }	
}
