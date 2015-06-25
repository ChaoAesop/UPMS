package com.userprofile.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * @author analian
 *
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {
	
	@Autowired
	RequestMappingHandlerAdapter requestMappingHandlerAdapter;
	
	@RequestMapping(method=RequestMethod.POST)
	public String onLogoutUser(ModelMap map, HttpServletRequest servletRequest, HttpServletResponse response){
		
		/* Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		 SecurityContextLogoutHandler ctxLogOut = new SecurityContextLogoutHandler(); 
		   ctxLogOut.logout(servletRequest, response, auth);
*/
		HttpSession session = servletRequest.getSession(false);
		if(session!=null){
			session.invalidate();
		}
		requestMappingHandlerAdapter.setServletContext(null);
		return "/login/logout";
	}
}
