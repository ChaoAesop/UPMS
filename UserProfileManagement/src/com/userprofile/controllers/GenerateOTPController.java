/**
 * 
 */
package com.userprofile.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.userprofile.service.LoginService;
import com.userprofile.vo.GenerateOTPVO;
import com.userprofile.vo.LoginVO;


/**
 * @author aigis
 *
 */
@Controller
public class GenerateOTPController {
	private static final Logger logger = Logger.getLogger(GenerateOTPController.class);
	@Autowired
	LoginService loginService;
	

	/**
	 * @param map
	 * @param httpRequest
	 * @param redirectAttributes
	 * @param generateOTPVO
	 * @return
	 */
	@RequestMapping(value="/backFromGenerateOTP")
	public RedirectView  showLoginPage(ModelMap map, HttpServletRequest httpRequest, 
			RedirectAttributes redirectAttributes, @ModelAttribute("generateOTP")GenerateOTPVO generateOTPVO){
		logger.info("Inside the OTP Controller... going back to login page");
		LoginVO loginvo = new LoginVO();
		loginvo.setName(generateOTPVO.getUserId());
		loginvo.setOTPString(generateOTPVO.getOTPString());
		
		redirectAttributes.addFlashAttribute("loginvo",loginvo);
		logger.info("sending Redirect View to login");
		return new RedirectView("/", true);
	}
}
