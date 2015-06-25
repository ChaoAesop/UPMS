package com.userprofile.service;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.userprofile.bo.AddressBO;
import com.userprofile.bo.UserBO;
import com.userprofile.dao.ProfileDAO;
import com.userprofile.vo.ProfileVO;


/**
 * @author analian
 *
 *	Main class where all the profile related activities occurs.
 */
@Service
public class ProfileService {


	@Autowired
	ProfileDAO daoImpl;
	
	/**
	 * @param userBO
	 * 
	 * 	Saves a new user 
	 */
	public void saveNewRegistration(UserBO userBO){
		
		daoImpl.saveNewRegistration(userBO);
	}
	
	/**
	 * 	Fetches the existing user from the DB.
	 * 
	 * @param userID
	 * @return
	 */
	public UserBO fetchExistingProfile(String userID){
		
		return daoImpl.readUserRegistration(userID);
	}
	
	/**
	 * 	Re-used method - creates the profile vo object for update
	 *  and delete and adding a new address,etc.	
	 * 
	 * @param userName
	 * @param map
	 * @return
	 */
	public ProfileVO createProfileForUpdate(String userName,  Model map){
		
		ProfileVO profileVO = new ProfileVO();
		profileVO.setUserID(userName);
		
		UserBO bo = fetchExistingProfile(profileVO.getUserID());

		Set<AddressBO> addressesList = bo.getAddressBOs();
		int counter = 0;
		Map<Integer, AddressBO> addressMap = new LinkedHashMap<Integer, AddressBO>();
		Iterator<AddressBO> addressIterator = addressesList.iterator();
		
		while (addressIterator.hasNext()) {
			addressMap.put(counter, addressIterator.next());
			counter++;
		}
		
		profileVO.setEmailID(bo.getEmail_id());
		profileVO.setUserName(bo.getUser_name());

		AddressBO firstAddress = addressMap.get(Integer.valueOf(0));
		
		if (firstAddress!=null) {
			profileVO.setHouseNumber(firstAddress.getHouse_number());
			profileVO.setCity(firstAddress.getCity());
			profileVO.setCountry(firstAddress.getCountry());
			profileVO.setStreet(firstAddress.getStreet());
			profileVO.setSelectedState(firstAddress.getState());
			profileVO.setSelectedAddress(0);
			profileVO.setAddressMap(addressMap);
		}else{
			profileVO.setHouseNumber(0);
			profileVO.setCity("");
			profileVO.setCountry("");
			profileVO.setStreet("");
			profileVO.setSelectedState("");
			profileVO.setSelectedAddress(0);
			profileVO.setAddressMap(addressMap);
		}
		map.asMap().put("addressMap", addressMap);
		return profileVO;
	}
	
	public ProfileVO processProfileUpdate(Model map, ProfileVO profileVO,
			Integer selectedAddString) {
		
		UserBO usrBO = fetchExistingProfile(profileVO.getUserID());
		usrBO.setEmail_id(profileVO.getEmailID());
		usrBO.setUser_name(profileVO.getUserName());
		
		AddressBO addressBO = new AddressBO();
		addressBO.setCity(profileVO.getCity());
		addressBO.setCountry(profileVO.getCountry());
		addressBO.setStreet(profileVO.getStreet());
		addressBO.setState(profileVO.getSelectedState());
		addressBO.setHouse_number(profileVO.getHouseNumber());
		
		// get the address map
		profileVO = createProfileForUpdate(profileVO.getUserID(), map);
		usrBO.setUser_name(profileVO.getUserName());
		Map<Integer, AddressBO> addressMap = profileVO.getAddressMap();

		// update the selected address from the map
		if (addressMap.containsKey(selectedAddString)) {
			addressMap.put(selectedAddString, addressBO);
		}
		// update the user BO and then table
		usrBO.getAddressBOs().clear();

		for (AddressBO addresses : addressMap.values()) {
			usrBO.getAddressBOs().add(addresses);
		}

		updateRegistrationWithAddress(usrBO);
		return profileVO;
	}
	
	
	public ProfileVO processAddressDelete(Model map, ProfileVO profileVO,
			Integer selectedAddString) {
		// get the address map
		profileVO = createProfileForUpdate(
				profileVO.getUserID(), map);

		Map<Integer, AddressBO> addressMap = profileVO.getAddressMap();

		// remove the selected address from the map
		if (addressMap.containsKey(selectedAddString)) {
			addressMap.remove(selectedAddString);
		}

		UserBO usrBO = fetchExistingProfile(profileVO
				.getUserID());
		// update the user BO and then table
		usrBO.getAddressBOs().clear();

		for (AddressBO addressBO : addressMap.values()) {
			usrBO.getAddressBOs().add(addressBO);
		}

		updateRegistrationWithAddress(usrBO);
		return profileVO;
	}

	/**
	 * @param json
	 * @return
	 */
	public ProfileVO getSelectedAddressAndProfileID(String json) {
		ProfileVO requestValue = new ProfileVO();
		StringTokenizer stringTokenizer = new StringTokenizer(json, "&");
		String[] tokenArr = new String[stringTokenizer.countTokens()];
		int counter = 0;
		while (stringTokenizer.hasMoreTokens()) {
			tokenArr[counter] = stringTokenizer.nextToken();
			counter++;
		}

		for (String string : tokenArr) {
			int newCounter = 0;
			StringTokenizer nextTokenizer = new StringTokenizer(string, "=");
			String[] jsonArray = new String[nextTokenizer.countTokens()];
			while (nextTokenizer.hasMoreTokens()) {
				jsonArray[newCounter] = nextTokenizer.nextToken();
				newCounter++;
			}
			if ("userID".equals(jsonArray[0])) {
				requestValue.setUserID(jsonArray[1]);
			} else if ("selectedAddress".equalsIgnoreCase(jsonArray[0])) {
				requestValue.setSelectedAddress(Integer.valueOf(jsonArray[1]));
			}
		}
		return requestValue;
	}
	
	
	public UserBO updateRegistrationWithAddress(UserBO bo) {
		UserBO userBO = daoImpl.updateUserBOWithAddress(bo);
		return userBO;
	}
}
