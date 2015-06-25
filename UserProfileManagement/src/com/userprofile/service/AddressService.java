package com.userprofile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userprofile.bo.AddressBO;
import com.userprofile.bo.UserBO;
import com.userprofile.dao.AddressDAO;
import com.userprofile.vo.AddressVO;

/**
 * @author analian
 *
 */
@Service
public class AddressService {
	
	@Autowired
	AddressDAO daoImpl;
	
	@Autowired
	ProfileService profileService;
	
	public UserBO addNewAddress(AddressVO addressVO){
		AddressBO addressBO = new AddressBO();
		addressBO.setCity(addressVO.getCity());
		addressBO.setHouse_number(addressVO.getHouseNo());
		addressBO.setCountry(addressVO.getCountry());
		addressBO.setState(addressVO.getSelectedState());
		addressBO.setStreet(addressVO.getStreet());
		
		UserBO bo = profileService.fetchExistingProfile(addressVO.getProfileID());
		bo.getAddressBOs().add(addressBO);
		
		 return profileService.updateRegistrationWithAddress(bo);
		
		
	}
	
	public void updateAddress(){
		
	}

	public void deleteAddress(){
		
	}
	
	public void fetchAllAddresses(){
		
	}
	
	public void fetchAddress(AddressVO addressVO){
	}
}
