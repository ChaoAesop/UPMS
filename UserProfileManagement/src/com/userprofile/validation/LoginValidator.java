/**
 * 
 */
package com.userprofile.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.userprofile.util.CommonValidatorUtil;
import com.userprofile.vo.LoginVO;

/**
 * @author analian
 *
 */
@Component
public class LoginValidator implements Validator {
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> classToValidate) {
		return LoginVO.class.equals(classToValidate);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object object, Errors errorArguments) {
		   ValidationUtils.rejectIfEmptyOrWhitespace(errorArguments, "name", "name.empty");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errorArguments, "OTPString", "OTPString.empty");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errorArguments, "password", "password.empty");
		   
		   LoginVO loginVo = (LoginVO)object;
		   if(!(CommonValidatorUtil.validatePassword(loginVo.getPassword()))){
			errorArguments.rejectValue("password", "password.rules");
		   }
	}

}
