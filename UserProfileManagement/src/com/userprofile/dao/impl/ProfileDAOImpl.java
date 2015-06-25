/**
 * 
 */
package com.userprofile.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.userprofile.bo.UserBO;
import com.userprofile.dao.AbstractDAO;
import com.userprofile.dao.ProfileDAO;

/**
 * @author analian
 *
 */
@Transactional
@Repository("profileDAO")
public class ProfileDAOImpl extends AbstractDAO<UserBO> implements ProfileDAO {

	/* (non-Javadoc)
	 * @see com.userprofile.dao.ProfileDAO#saveNewRegistration(com.userprofile.bo.UserBO)
	 */
	@Override
	public void saveNewRegistration(UserBO userBO) {
		entityManager.persist(userBO);
	}
	
	@Override
	public UserBO readUserRegistration(String userID){
		return entityManager.find(UserBO.class, userID);
	}
	
	@Override
	public UserBO update(UserBO entity) {
		// TODO Auto-generated method stub
		return super.update(entity);
	}

	@Override
	public UserBO updateUserBOWithAddress(UserBO bo) {
		// TODO Auto-generated method stub
		return update(bo);
	}
}
