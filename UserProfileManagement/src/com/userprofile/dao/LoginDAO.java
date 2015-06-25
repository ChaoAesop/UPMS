/**
 * 
 */
package com.userprofile.dao;



/**
 * @author analian
 *
 */
public interface LoginDAO {
	
	/**
	 * @param userName
	 * @param otp
	 */
	void saveNewUserID(String userName,String otp);
	/**
	 * @param userID
	 * @return
	 */
	boolean checkExistingUserID(String userID);
	/**
	 * @param userID
	 * @param password
	 * @param otpString
	 * @return
	 */
	boolean validateLogin(String userID,String password, String otpString);
	/**
	 * @param userName
	 */
	void removeUserOTP(String userName);

}
