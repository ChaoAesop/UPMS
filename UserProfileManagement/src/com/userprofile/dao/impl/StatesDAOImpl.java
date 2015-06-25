package com.userprofile.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.userprofile.bo.StatesBO;
import com.userprofile.dao.AbstractDAO;
import com.userprofile.dao.StatesDAO;

/**
 * @author analian
 *
 */
@Repository("statesDAO")
public class StatesDAOImpl extends AbstractDAO<StatesBO> implements StatesDAO {

	public StatesDAOImpl() {
	setClass(StatesBO.class);
	}
	
	public List<StatesBO> fetchAllStates() {
		return findAll();
	}
}
