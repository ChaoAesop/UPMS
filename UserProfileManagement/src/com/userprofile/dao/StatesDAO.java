package com.userprofile.dao;

import java.util.List;

import com.userprofile.bo.StatesBO;

/**
 * @author analian
 *
 */
public interface StatesDAO {

	/**
	 * @return
	 */
	public List<StatesBO> fetchAllStates();
}
