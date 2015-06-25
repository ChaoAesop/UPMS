package com.userprofile.dao;

import com.userprofile.bo.UserBO;

public interface ProfileDAO {

	public void saveNewRegistration(UserBO userBO);

	public UserBO readUserRegistration(String userID);

	public UserBO updateUserBOWithAddress(UserBO bo);
}
