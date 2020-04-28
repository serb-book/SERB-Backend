package com.serb_backend.dal.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ConnectionProviderWithPoolImpl implements ConnectionProvider {
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;

	/**
	 * getConnection function used to get a HikariCPed JDBC Connection given
	 * UserName password and defaults to oracleDB express edition with the default
	 * port "1521"
	 * 
	 * @param username
	 * @param password
	 * @param numberOfCores
	 * @return Connection instance (java.sql.Connection)
	 * @throws SQLException
	 */

	public Connection getConnection(String username, String password, int numberOfCores) throws SQLException {
		config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		config.setUsername(username);
		config.setPassword(password);
		setMiscDataSourceProperties(numberOfCores);
		ds = new HikariDataSource(config);
		return ds.getConnection();
	}

	/**
	 * getConnection function used to get a HikariCPed JDBC Connection given
	 * UserName Password
	 * 
	 * @param jdbcUrl
	 * @param username
	 * @param passsword
	 * @param numberOfCores
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection(String jdbcUrl, String username, String passsword, int numberOfCores)
			throws SQLException {
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(username);
		config.setPassword(passsword);
		setMiscDataSourceProperties(numberOfCores);
		ds = new HikariDataSource(config);
		return ds.getConnection();
	}

	// using Hikari recommended values for Misc properties
	private static void setMiscDataSourceProperties(int numberOfCores) {
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		// use the PostgreSQL formula for pool size
		config.addDataSourceProperty("maximumPoolSize", 2 * numberOfCores + 1); // refer to
																				// https://github.com/brettwooldridge/HikariCP
																				// for more info about pool size
		config.addDataSourceProperty("driverClassName", "oracle.jdbc.driver.OracleDriver");
		
	}

	@Override
	public DataSource getDataSource(String jdbcUrl, String username, String passsword, int numberOfCores)
			throws SQLException {
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(username);
		config.setPassword(passsword);
		setMiscDataSourceProperties(numberOfCores);
		return new HikariDataSource(config);	
	}

	@Autowired
	private Environment env;

	@Bean
	public DataSource dataSource(){
		config.setJdbcUrl(env.getProperty("database.jdbc-url"));
		config.setUsername(env.getProperty("database.username"));
		config.setPassword(env.getProperty("database.password"));
		setMiscDataSourceProperties(Integer.parseInt(env.getProperty("database.hikari.cores")));
		return new HikariDataSource(config);

	}
}
