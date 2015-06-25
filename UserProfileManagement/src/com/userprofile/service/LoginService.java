package com.userprofile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userprofile.dao.LoginDAO;

@Service
public class LoginService {

	@Autowired
	LoginDAO daoImpl;
	
	/**
	 * @param userID
	 * @return
	 */
	public boolean checkExistingUserID(String userID){
		return daoImpl.checkExistingUserID(userID);
	}
	
	/**
	 * @param userName
	 * @param otp
	 */
	public void saveUserOTPDetails(String userName, String otp){
		
		daoImpl.saveNewUserID(userName, otp);
	}
	
	public void deleteOTPDetails(String userName){
		daoImpl.removeUserOTP(userName);
	}
	
	public boolean validateUserLogin(String userID, String password,String otpString){
		
		return daoImpl.validateLogin(userID, password,otpString);
	}
}
