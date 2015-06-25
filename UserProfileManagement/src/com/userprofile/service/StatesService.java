/**
 * 
 */
package com.userprofile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.userprofile.bo.StatesBO;
import com.userprofile.dao.StatesDAO;
import com.userprofile.vo.StatesVO;

/**
 * @author analian
 *
 */
@Service
public class StatesService {
	
	@Autowired
	StatesDAO statesDAOImpl;  
	
	/** 
	 *  This is cacheable as it is called in multiple places 
	 *  and it calls DB for same set of results.
	 *  
	 * @return
	 */
	@Cacheable(value = { "states" })
	public List<StatesVO> fetchAllStates(){
		List<StatesBO> statesBOs = statesDAOImpl.fetchAllStates();

		List<StatesVO> stateList = new ArrayList<StatesVO>();
		if(statesBOs!=null){
			for (StatesBO statesBO : statesBOs) {
				StatesVO statesVO = new StatesVO();
				statesVO.setStateId(String.valueOf(statesBO.getState_id()));
				statesVO.setStateName(statesBO.getState_name());
				stateList.add(statesVO);
			}
		}
		return stateList;
	}
	

}
