package com.userprofile.dao;

import com.userprofile.bo.UserBO;

public interface ProfileDAO {

	/**
	 * @param userBO
	 */
	public void saveNewRegistration(UserBO userBO);

	/**
	 * @param userID
	 * @return
	 */
	public UserBO readUserRegistration(String userID);

	/**
	 * @param bo
	 * @return
	 */
	public UserBO updateUserBOWithAddress(UserBO bo);
}
