/**
 * General account related operations, this doesn't deal with offering
 * or any kind of transaction, for those purposes check ClientDAO interface
 * 
 * @date Apr17
 * @author abdullah
 */
package com.serb_backend.dal;
import com.serb_backend.dto.AccountDTO;;
public interface AccountDAO {
	/**
	 * @param username
	 * @param password
	 * @return the authentication access-token to be used in other request
	 */
	String getAccessToken(String username, String password);
	
	/**
	 *  
	 * @param email
	 * @return a temporary password,In case of 
	 * forgetting password and a valid email address
	 */
	String getTemporaryPassword(String email);
	/**
	 * @param token
	 * @param email
	 */
	void sendRegisteringConfirmationEmail(String token, String email);
	/**
	 * Note: takes the same token as sendRegisteringConfirmationEmail
	 * to create a user
	 * @param token
	 */
	void createUser(String token);
	/**
	 * update the non null values of Account
	 * @param tocken
	 * @param account
	 */
	void updateUser(String tocken, AccountDTO account);
	/**
	 * 
	 * @param account
	 */
	void saveAccount(AccountDTO account);
	/**
	 * 
	 * @param id
	 */
	void grantSuperUserPriviliges(Long id);
	
}
