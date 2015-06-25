package com.userprofile.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.userprofile.service.LoginService;
import com.userprofile.service.ProfileService;
import com.userprofile.service.StatesService;
import com.userprofile.util.CreateOTPUtil;
import com.userprofile.vo.GenerateOTPVO;
import com.userprofile.vo.LoginVO;
import com.userprofile.vo.ProfileVO;
import com.userprofile.vo.StatesVO;

/**
 * @author analian
 *
 *	Main Controller class for Login and other activities.
 */
@Controller
public class LoginController  {

	private static final Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	LoginService loginService;

	@Autowired
	@Qualifier("loginValidator")
	Validator validator;
	
	@Autowired
	ProfileService profileService;
	
	@Autowired
	StatesService statesService;
	
	@InitBinder("loginForm")
	    private void initBinder(WebDataBinder binder) {
	        binder.setValidator(validator);
	    }

	/**
	 * Default dispatcher servlet mapping to this method.
	 * 
	 * @param model
	 * @param loginVO
	 * @return
	 */
	@RequestMapping("/")
	public String viewLogin(Model  model,
			@ModelAttribute("loginvo") LoginVO loginVO) {
		logger.debug("Base URL of the application context");
		if (loginVO == null) {
			loginVO = new LoginVO();
		}
		model.asMap().put("loginForm", loginVO);
		return "/login/login";
	}
	
	
	/**
	 * 	Takes user to the Generate OTP Page.
	 * 
	 * @param map
	 * @param loginVO
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value="/generateOTP", method=RequestMethod.GET)
	public String showGenerateOTPPage(ModelMap map, @ModelAttribute("loginForm")LoginVO loginVO,
			BindingResult bindingResult){
		logger.info("Checking existing user ID");
		if(loginService.checkExistingUserID(loginVO.getName())){
			GenerateOTPVO generateOTPVO = new GenerateOTPVO();
			generateOTPVO.setUserId(loginVO.getName());
			generateOTPVO.setOTPString(CreateOTPUtil.getRandomString());
			map.put("generateOTP", generateOTPVO);
			return "/otp/generateOTP"; 
		}
		else{
			bindingResult.rejectValue("name", "user.doesnt.exist");
			return "/login/login"; 
		}
	}

	/**
	 * 	Main login Method:
	 * 
	 * 	a: validates user login form for binding errors
	 *  b: validates to see if user exists in DB, if does then add to OTP table.
	 *  c: validates that user credentials are correct.
	 *  d: creates profile object to go to update page
	 *  e: delete OTP on successful login.
	 *  
	 * @param user
	 * @param bindingResult
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String userLogin(
			@ModelAttribute("loginForm") @Validated LoginVO user, BindingResult bindingResult, Model map) {
		if (bindingResult.hasErrors()) {
			return "/login/login";
		}

		// call service to validate user id
		logger.info("Checking existing user ID");
		if (loginService.checkExistingUserID(user.getName())) {
			loginService.saveUserOTPDetails(user.getName(), user.getOTPString());
		}// This means that this user id does not exist in DATABASE. Need to
			// show error message and not allow login
		else {
			bindingResult.rejectValue("name", "not.a.valid.user",
					"User is not in DB");
			logger.debug("return to login page");
			return "/login/login";
		}
		
		if(!loginService.validateUserLogin(user.getName(), user.getPassword(), user.getOTPString())){
			bindingResult.rejectValue("name", "user.credentials.mismatch",
					"User Credentials Do Not Match");
			logger.debug("return to login page");
			return "/login/login";
		}
		
		//matches - need to login to update profile
		createProfileVOForUser(user, map);
		
		//delete otp on successful login
		loginService.deleteOTPDetails(user.getName());
		logger.info("Go to Update Profile Page");
		return "/update/updateProfile";
	}

	@ModelAttribute("statesList")
	public List<StatesVO> statesList() {
		logger.info("getting the states to populate");
		List<StatesVO> statesVOs = statesService.fetchAllStates();
		return statesVOs;
	}

	/**
	 * @param user
	 * @param map
	 */
	private void createProfileVOForUser(LoginVO user, Model map) {
		logger.info("Calling the service class for creating the profile vo object");
		ProfileVO profileVO = profileService.createProfileForUpdate(user.getName(), map);
		map.asMap().put("profileVO", profileVO);
	}
}
