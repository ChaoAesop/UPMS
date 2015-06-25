package com.userprofile.vo;


/**
 * @author analian
 *
 */
public class LoginVO {
	
	private String name;
	
	private String password;
	
	private String OTPString;

	/**
	 * @return the oTPString
	 */
	public String getOTPString() {
		return OTPString;
	}

	/**
	 * @param oTPString the oTPString to set
	 */
	public void setOTPString(String oTPString) {
		OTPString = oTPString;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
