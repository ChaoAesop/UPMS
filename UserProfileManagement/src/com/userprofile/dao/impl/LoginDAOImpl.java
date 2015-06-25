/**
 * 
 */
package com.userprofile.dao.impl;

import java.sql.Timestamp;
import java.util.List;







import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.userprofile.bo.LoginBO;
import com.userprofile.bo.UserBO;
import com.userprofile.dao.AbstractDAO;
import com.userprofile.dao.LoginDAO;

/**
 * @author analian
 *
 */
@Repository("loginDAO")
public class LoginDAOImpl extends AbstractDAO<LoginBO> implements LoginDAO {

	/* (non-Javadoc)
	 * @see com.userprofile.dao.LoginDAO#checkExistingUserID(java.lang.String)
	 */
	@Override
	public boolean checkExistingUserID(String userID) {
		
		@SuppressWarnings("unchecked")
		List<UserBO> queryResults = entityManager.createQuery( "from UserBO where user_id ='" + userID +"'" ).getResultList();
		if(queryResults!=null && !queryResults.isEmpty()){
			return true;
		}
		return false;
	}
	

	/* (non-Javadoc)
	 * @see com.userprofile.dao.LoginDAO#saveNewUserID(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional 
	public void saveNewUserID(String userName, String otp) {

		LoginBO bo = new LoginBO();
		bo.setDateTimeStamp(new Timestamp(System.currentTimeMillis()));
		bo.setOTPString(otp);
		bo.setUser_id(userName);
		entityManager.merge(bo);
			
	}


	/* (non-Javadoc)
	 * @see com.userprofile.dao.LoginDAO#validateLogin(java.lang.String, java.lang.String, java.lang.String)
	 * 
	 * Validates the user id and password
	 * validates the user id and otp 
	 * and also if the otp is within 5 minute range.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public boolean validateLogin(String userID, String password, String otpString) {
		
		List<UserBO> bos = entityManager.createQuery( "from UserBO where user_id ='" + userID +"' and password = '"+ password + "'").getResultList();
		boolean otpCheck = false;
		List<LoginBO> loginBOs = entityManager.createQuery("from LoginBO where user_id = '"+userID + "' and OTPString = '" + otpString + "'" ).getResultList();
		if(loginBOs!=null && !loginBOs.isEmpty()){
			LoginBO bo = loginBOs.get(0);
			long now = System.currentTimeMillis(); 
			long then = bo.getDateTimeStamp().getTime();

			long minutes = TimeUnit.MILLISECONDS.toMinutes(now - then);
			System.out.println(minutes);
			if(minutes < 5l){
				otpCheck = true;
			}
		}
		
		return ((bos!=null) && (!bos.isEmpty()) && otpCheck);
	}


	/* (non-Javadoc)
	 * @see com.userprofile.dao.LoginDAO#removeUserOTP(java.lang.String)
	 * 
	 * Removes OTP on successful login
	 */
	@Override
	@Transactional 
	public void removeUserOTP(String userName) {
			
		LoginBO bo = new LoginBO();
		bo.setUser_id(userName);
		delete(bo);
		
	}

}
