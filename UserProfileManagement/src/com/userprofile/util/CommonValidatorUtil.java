package com.userprofile.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author analian
 * 	Common Util class for all validation types
 */
public class CommonValidatorUtil {

	private static Log logger = LogFactory.getLog(CommonValidatorUtil.class);

	 private static final String PASSWORD_PATTERN = 
			  "^[a-zA-Z0-9]{6,10}$";
	 
	 private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"  
			   + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  
	 
	 private static Pattern pattern ;
	  private static Matcher matcher;
	  
	  public static boolean validatePassword(String password){
		  pattern =  Pattern.compile(PASSWORD_PATTERN);
		  matcher = pattern.matcher(password);
		  return matcher.matches();
	  }
	  
	  public static boolean validateNumber(String number){
		try{
			Integer integer = Integer.valueOf(number);
			logger.info(integer);
		}catch(Exception exception){
			logger.error("Not a number");
			return false;
		}
		  return true;
	  }
	  
	  public static boolean validateEmail(String email){
		  pattern = Pattern.compile(EMAIL_PATTERN);
		  matcher = pattern.matcher(email);
		  return matcher.matches();
	  }

}
