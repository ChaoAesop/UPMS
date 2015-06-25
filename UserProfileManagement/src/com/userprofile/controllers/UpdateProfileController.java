/**
 * 
 */
package com.userprofile.controllers;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
	private static final Logger logger = Logger.getLogger(UpdateProfileController.class);
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
			BindingResult bindingResult, HttpSession session) {
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
		logger.debug("Selected Address is :" + selectedAddressNumber);
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
		logger.info("Back to profile page ");
		ProfileVO profileVO = profileService.createProfileForUpdate(addressVO.getProfileID(), map);
		map.addAttribute("profileVO", profileVO);
		return "/update/updateProfile";
	}

	/**
	 * Cached List of states
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
	    public String getAjaxRequestForSelectedAddress(@RequestBody String json,HttpSession session) throws IOException {
		 logger.info("Inside ajax method to get changed address");
			@SuppressWarnings("unchecked")
			LinkedHashMap<Integer, AddressBO> map = 
			(LinkedHashMap<Integer, AddressBO>) session.getAttribute("addressMap");
	
			ProfileVO requestValue = profileService.getSelectedAddressAndProfileID(json);
	
			AddressBO addressBO = null;
			if (map.containsKey(requestValue.getSelectedAddress())) {
				addressBO = map.get(requestValue.getSelectedAddress());
			}
			 logger.debug("the address bo to be returned as ajax response" + addressBO);
			return toJson(addressBO);
	 }

	 	/**
	 	 * 	Checking if only 1 address remains in the address map before deleting.
	 	 * @param json
	 	 * @param session
	 	 * @return
	 	 * @throws IOException
	 	 */
	 	@RequestMapping(value = "/checkDeleteSelectedAddress", method = RequestMethod.POST)
	    @ResponseBody
	    public String getAjaxResponseForDeleteSelectedAddress(@RequestBody String json,
	    										HttpSession session) throws IOException {
	 		
	 	logger.info("Inside the AJAX request method for delete selected address");	
		@SuppressWarnings("unchecked")
		LinkedHashMap<Integer, AddressBO> map = (LinkedHashMap<Integer, AddressBO>) session
				.getAttribute("addressMap");

		if (map != null) {

			if (map.size() == 1) {
				return "false";
			}
		} else {
			return "false";
		}
		return "true";
	}


	 /**
	  * 	Converts object to JSON String. 
	 * @param addressBO
	 * @return
	 */
	private String toJson(AddressBO addressBO) {
	        ObjectMapper mapper = new ObjectMapper();
	        try {
	            String value = mapper.writeValueAsString(addressBO);
	            return value;
	        } catch (JsonProcessingException e) {
	            logger.error(e.getMessage());
	            return null;
	        } catch (IOException e) {
	        	 logger.error(e.getMessage());
			}
			return null;
	    }
}
