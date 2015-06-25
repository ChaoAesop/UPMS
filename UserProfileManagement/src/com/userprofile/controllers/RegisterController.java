package com.userprofile.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.userprofile.bo.AddressBO;
import com.userprofile.bo.UserBO;
import com.userprofile.service.ProfileService;
import com.userprofile.service.StatesService;
import com.userprofile.vo.RegistrationVO;
import com.userprofile.vo.StatesVO;

@Controller
public class RegisterController {

	@Autowired
	StatesService statesService;

	@Autowired
	@Qualifier("registrationValidator")
	Validator validator;

	@Autowired
	ProfileService profileService;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@ModelAttribute("statesList")
	public List<StatesVO> statesList() {
		List<StatesVO> statesVOs = statesService.fetchAllStates();
		return statesVOs;
	}

	@RequestMapping("/showRegisterPage")
	public ModelAndView showRegistrationPage(Map<String, Object> model,
			@ModelAttribute("registration") RegistrationVO registrationVO) {

		if (registrationVO == null) {
			registrationVO = new RegistrationVO();
		}
		ModelAndView modelAndView = new ModelAndView(
				"/registration/newRegistration", "registration", registrationVO);
		return modelAndView;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerNewUser(
			@ModelAttribute("registration") @Validated RegistrationVO registrationVO,
			BindingResult bindingResult, ModelMap map) {

		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("/registration/newRegistration");
			return modelAndView;
		}

		UserBO bo = mapData(registrationVO);
		profileService.saveNewRegistration(bo);

		return new ModelAndView("redirect:/");
	}

	private UserBO mapData(RegistrationVO registrationVO) {
		AddressBO addressBO = new AddressBO();
		addressBO.setState(registrationVO.getState());
		addressBO.setCity(registrationVO.getCity());
		addressBO.setHouse_number(Integer.valueOf(registrationVO
				.getHouseNumber()));
		addressBO.setCountry(registrationVO.getCountry());
		addressBO.setStreet(registrationVO.getStreet());

		Set<AddressBO> addressBOs = new HashSet<AddressBO>();
		addressBOs.add(addressBO);

		UserBO bo = new UserBO();
		bo.setEmail_id(registrationVO.getEmailID());
		bo.setId(registrationVO.getUserId());
		bo.setPassword(registrationVO.getPassword());
		bo.setUser_name(registrationVO.getUserName());
		bo.setAddressBOs(addressBOs);
		return bo;
	}

}
