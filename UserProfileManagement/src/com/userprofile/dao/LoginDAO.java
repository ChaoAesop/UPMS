/**
 * 
 */
package com.userprofile.dao;



/**
 * @author analian
 *
 */
public interface LoginDAO {
	
	void saveNewUserID(String userName,String otp);
	boolean checkExistingUserID(String userID);
	boolean validateLogin(String userID,String password, String otpString);
	void removeUserOTP(String userName);

}
