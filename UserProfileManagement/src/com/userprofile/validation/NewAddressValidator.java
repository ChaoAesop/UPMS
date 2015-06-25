/**
 * 
 */
package com.userprofile.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.userprofile.util.CommonValidatorUtil;
import com.userprofile.vo.AddressVO;

/**
 * @author analian
 *	
 *	Validator class for adding new addresses page.
 */
@Component
public class NewAddressValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return AddressVO.class.equals(clazz);
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errorArguments) {
		  ValidationUtils.rejectIfEmptyOrWhitespace(errorArguments, "houseNo", "houseNumber.empty");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errorArguments, "city", "city.empty");
		   ValidationUtils.rejectIfEmptyOrWhitespace(errorArguments, "street", "street.empty");
		   AddressVO addressVO = (AddressVO)target;
		   try{
				 if(!CommonValidatorUtil.validateNumber(String.valueOf(addressVO.getHouseNo()))){
					 errorArguments.rejectValue("houseNo", "not.a.number");
				   }
				}catch(Exception exception){
					errorArguments.rejectValue("houseNo", "not.a.number");
				}
	}

}
