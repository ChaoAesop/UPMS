/**
 * 
 */
package com.userprofile.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.userprofile.util.CommonValidatorUtil;
import com.userprofile.vo.ProfileVO;

/**
 * @author analian
 *
 */
@Component
public class ProfileValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return ProfileVO.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "houseNumber","houseNumber.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city","city.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "street","street.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailID","emailID.empty");
		ProfileVO profileVO = (ProfileVO)target;
		try{
		 if(!CommonValidatorUtil.validateNumber(String.valueOf(profileVO.getHouseNumber()))){
			 errors.rejectValue("houseNumber", "not.a.number");
		   }
		}catch(Exception exception){
			 errors.rejectValue("houseNumber", "not.a.number");
		}
		 if(!CommonValidatorUtil.validateEmail(profileVO.getEmailID())){
			 errors.rejectValue("emailID", "not.a.valid.email");
		   }
	}

}
