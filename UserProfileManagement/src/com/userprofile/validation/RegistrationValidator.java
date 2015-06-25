package com.userprofile.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.userprofile.service.LoginService;
import com.userprofile.util.CommonValidatorUtil;
import com.userprofile.vo.RegistrationVO;

/**
 * @author analian
 *
 */
@Component
public class RegistrationValidator implements Validator {
	
	@Autowired
	LoginService loginService;

	@Override
	public boolean supports(Class<?> clazz) {
		return RegistrationVO.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errorArguments) {
		
		   ValidationUtils.rejectIfEmptyOrWhitespace(errorArguments, "userId", "userid.empty");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errorArguments, "userName", "name.empty");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errorArguments, "password", "password.empty");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errorArguments, "emailID", "emailID.empty");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errorArguments, "houseNumber", "houseNumber.empty");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errorArguments, "street", "street.empty");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errorArguments, "city", "city.empty");
		   RegistrationVO regVo = (RegistrationVO)target;
		   if(!CommonValidatorUtil.validatePassword(regVo.getPassword())){
			   errorArguments.rejectValue("password", "password.rules");
		   }
		   if(!CommonValidatorUtil.validateNumber(regVo.getHouseNumber())){
			   errorArguments.rejectValue("houseNumber", "not.a.number");
		   }
		   
		   if(!CommonValidatorUtil.validateEmail(regVo.getEmailID())){
			   errorArguments.rejectValue("emailID", "not.a.valid.email");
		   }
		   
		   if(loginService.checkExistingUserID(regVo.getUserId())){
			   ValidationUtils.rejectIfEmptyOrWhitespace(errorArguments, "userId", "userid.exists");
		   }
	}

}
