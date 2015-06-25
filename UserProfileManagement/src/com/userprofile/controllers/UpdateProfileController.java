/**
 * 
 */
package com.userprofile.controllers;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.userprofile.bo.AddressBO;
import com.userprofile.bo.UserBO;
import com.userprofile.service.AddressService;
import com.userprofile.service.ProfileService;
import com.userprofile.service.StatesService;
import com.userprofile.validation.ProfileValidator;
import com.userprofile.vo.AddressVO;
import com.userprofile.vo.ProfileVO;
import com.userprofile.vo.StatesVO;

/**
 * @author analian
 *
 */
@Controller
public class UpdateProfileController {

	@Autowired
	AddressService addressService;

	@Autowired
	ProfileService profileService;

	@Autowired
	StatesService statesService;

	@Autowired
	@Qualifier("newAddressValidator")
	Validator validator;
	
	@InitBinder("profileVO")
	protected void initProfileBinder(WebDataBinder binder) {
	    binder.setValidator(new ProfileValidator());
	}

	@InitBinder("addressForm")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	/**
	 * Method to go to addnewaddress page
	 * @param map
	 * @param profileVO
	 * @param request
	 * @param bindingResult
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addNewAddress", method = RequestMethod.GET)
	public String populateUpdateProfilePage(Model map,
			@ModelAttribute("profileVO") ProfileVO profileVO,
			HttpServletRequest request, BindingResult bindingResult, Model model) {
		AddressVO addressVO = new AddressVO();
		addressVO.setProfileID(profileVO.getUserID());
		addressVO.setCountry(profileVO.getCountry());
		model.asMap().put("addressForm", addressVO);
		return "/address/newAddress";
	}

	/**
	 * Method to add a new address to Profile
	 * 
	 * @param map
	 * @param addressVO
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/addAddressToProfile", method = RequestMethod.POST)
	public String addNewAddressToProfile(Model map,
			@ModelAttribute("addressForm") @Validated AddressVO addressVO,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "/address/newAddress";
		}
		UserBO bo = addressService.addNewAddress(addressVO);
		
		ProfileVO profileVO = profileService.createProfileForUpdate(bo.getUser_id(), map);
		map.addAttribute("profileVO", profileVO);
		return "/update/updateProfile";
	}

	/**
	 * 
	 * Method to delete the selected address from profile
	 * 
	 * @param map
	 * @param profileVO
	 * @param bindingResult
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteSelectedAddress", method = RequestMethod.GET)
	public String deleteSelectedAddress(Model map,
			@ModelAttribute("profileVO") @Validated ProfileVO profileVO,
			BindingResult bindingResult, HttpServletRequest request) {
		if(bindingResult.hasErrors()){
			return "/update/updateProfile";
		}
		// Get the selected address number
		Integer selectedAddressNumber = profileVO.getSelectedAddress();

		profileVO = profileService.processAddressDelete(map, profileVO, selectedAddressNumber);

		profileVO = profileService.createProfileForUpdate(profileVO.getUserID(), map);
		map.asMap().put("profileVO", profileVO);
		return "/update/updateProfile";
	}

	

	/**
	 * Method to update the profile with the address details
	 *  
	 * @param map
	 * @param profileVO
	 * @param bindingResult
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateChangedAddress", method = RequestMethod.GET)
	public String updateChangedAddress(Model map,
			@ModelAttribute("profileVO") @Validated ProfileVO profileVO,
			BindingResult bindingResult, HttpServletRequest request) {
		if(bindingResult.hasErrors()){
			return "/update/updateProfile";
		}
		// Get the selected address number
		Integer selectedAddressNumber = profileVO.getSelectedAddress();

		profileVO = profileService.processProfileUpdate(map, profileVO, selectedAddressNumber);

		profileVO = profileService.createProfileForUpdate(profileVO.getUserID(), map);
		map.asMap().put("profileVO", profileVO);
		return "/update/updateProfile";
	}

	/**
	 * Method to go back to profile page from add new address page
	 * 
	 * @param addressVO
	 * @param bindingResult
	 * @param map
	 * @return
	 */
	@RequestMapping("/backToProfilePage")
	public String backToProfilePage(@ModelAttribute("addressForm") AddressVO addressVO,
			BindingResult bindingResult,Model map){
		ProfileVO profileVO = profileService.createProfileForUpdate(
				addressVO.getProfileID(), map);
		map.addAttribute("profileVO", profileVO);
		return "/update/updateProfile";
	}

	/**
	 * List of states
	 * 
	 * @return
	 */
	@ModelAttribute("statesList")
	public List<StatesVO> statesList() {
		List<StatesVO> statesVOs = statesService.fetchAllStates();
		return statesVOs;
	}
	
	 @RequestMapping(value = "/getChangedAddress", 
	            method = RequestMethod.POST)
	    @ResponseBody
	    public String getAjaxRequestForSelectedAddress(@RequestBody String json,HttpSession  session) throws IOException {
		 
			@SuppressWarnings("unchecked")
			LinkedHashMap<Integer, AddressBO> map = (LinkedHashMap<Integer, AddressBO>) session
					.getAttribute("addressMap");
	
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
	
			AddressBO addressBO = null;
			if (map.containsKey(requestValue.getSelectedAddress())) {
				addressBO = map.get(requestValue.getSelectedAddress());
			}
			return toJson(addressBO);
	 }

	 private String toJson(AddressBO addressBO) {
	        ObjectMapper mapper = new ObjectMapper();
	        try {
	            String value = mapper.writeValueAsString(addressBO);
	            return value;
	        } catch (JsonProcessingException e) {
	            e.printStackTrace();
	            return null;
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	    }
}
