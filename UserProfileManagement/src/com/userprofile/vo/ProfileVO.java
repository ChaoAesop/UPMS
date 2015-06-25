package com.userprofile.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.userprofile.bo.AddressBO;

public class ProfileVO {

	private String userID;
	private String userName;
	private Integer selectedAddress;
	private String password;
	private String emailID;
	private Integer houseNumber;
	private String street;
	private String city;
	private String country;
	private Map<Integer, AddressBO> addressMap;
	private List<StatesVO> states = new ArrayList<StatesVO>();
	private String selectedState;
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	/**
	 * @return the emailID
	 */
	public String getEmailID() {
		return emailID;
	}
	/**
	 * @param emailID the emailID to set
	 */
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the states
	 */
	public List<StatesVO> getStates() {
		return states;
	}
	/**
	 * @param states the states to set
	 */
	public void setStates(List<StatesVO> states) {
		this.states = states;
	}
	/**
	 * @return the selectedState
	 */
	public String getSelectedState() {
		return selectedState;
	}
	/**
	 * @param selectedState the selectedState to set
	 */
	public void setSelectedState(String selectedState) {
		this.selectedState = selectedState;
	}
	/**
	 * @return the houseNumber
	 */
	public Integer getHouseNumber() {
		return houseNumber;
	}
	/**
	 * @param houseNumber the houseNumber to set
	 */
	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}
	/**
	 * @return the selectedAddress
	 */
	public Integer getSelectedAddress() {
		return selectedAddress;
	}
	/**
	 * @param selectedAddress the selectedAddress to set
	 */
	public void setSelectedAddress(Integer selectedAddress) {
		this.selectedAddress = selectedAddress;
	}
	/**
	 * @return the addressMap
	 */
	public Map<Integer, AddressBO> getAddressMap() {
		return addressMap;
	}
	/**
	 * @param addressMap the addressMap to set
	 */
	public void setAddressMap(Map<Integer, AddressBO> addressMap) {
		this.addressMap = addressMap;
	}
	
}
