/**
 * General account related operations, this doesn't deal with offering
 * or any kind of transaction, for those purposes check ClientDAO interface
 * 
 * @date Apr17
 * @author abdullah
 */
package com.serb.serb_backend.dal;
import com.serb.serb_backend.dto.AccountDTO;;
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
	 * @return if sending done successfully
	 */
	boolean sendRegisteringConfirmationEmail(String token, String email);
	/**
	 * Note: takes the same token as sendRegisteringConfirmationEmail
	 * to create a user
	 * @param token
	 * @return if operation went successfully
	 */
	boolean createUser(String token);
	/**
	 * update the non null values of Account
	 * @param tocken
	 * @param account
	 * @return
	 */
	boolean updateUser(String tocken, AccountDTO account);
}
