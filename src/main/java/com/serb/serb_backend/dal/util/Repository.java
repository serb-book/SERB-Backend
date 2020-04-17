package com.serb.serb_backend.dal.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * A singleton class shared between repositories represents the main data for
 * connection retrieval
 * 
 * @author abdullah
 *
 */
public class Repository {
	private String username;
	private String password;
	private int numberOfCores;
	private boolean oracleDBExpressEdition = true;
	private String jdbcUrl;
	private static Repository repo;

	private Repository() {
	}

	private static void nullibiltyCheck() {
		if(repo == null) {
			repo = new Repository();
		}
	}
	
	public static void setRepoUsername(String username) {
		nullibiltyCheck();
		repo.username = username;
	}
	
	public static void setRepoPassword(String password) {
		nullibiltyCheck();
		repo.password = password;
	}
	
	public  static void setRepoNumberOfCores(int numberOfCores) {
		nullibiltyCheck();
		repo.numberOfCores = numberOfCores;
	}
	
	public static void setJDBCUrl(String jdbcUrl) {
		repo.oracleDBExpressEdition = false;
		repo.jdbcUrl = jdbcUrl;
	}
	/**
	 * 
	 * @return
	 * @throws IllegalAccessException
	 */
	public static Repository getRepository() throws IllegalAccessException{
		if (repo == null) {
			//use the defaults
				throw new IllegalAccessException("you should initialize the repositry before using this getter");
		} 
		return repo;
	}
	
	public static Repository getRepository(String username, String password, int numberOfCores) {
		if (repo == null) {
			repo = new Repository();
			repo.username = username;
			repo.password = password;
			repo.numberOfCores = numberOfCores;
			repo.oracleDBExpressEdition = true;	
		}
		return repo;
	}

	public static Repository getRepository(String jdbcUrl, String username, String password, int numberOfCores) {
		if (repo == null) {
			repo = getRepository(username, password, numberOfCores);
			repo.jdbcUrl = jdbcUrl;
			repo.oracleDBExpressEdition = false;
		}
		return repo;
	}

	public Connection getRepoConnection() throws SQLException {
		ConnectionProvider provider = new ConnectionProviderWithPoolImpl();
		return repo.oracleDBExpressEdition ? provider.getConnection(username, password, numberOfCores)
				: provider.getConnection(jdbcUrl, username, password, numberOfCores);
	}

}
