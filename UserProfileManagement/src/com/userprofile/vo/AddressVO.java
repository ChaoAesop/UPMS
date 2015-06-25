package com.userprofile.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author analian
 *
 */
public class AddressVO {

	private Integer houseNo;
	private String street;
	private String country;
	private String city;
	private List<StatesVO> statesList = new ArrayList<StatesVO>();
	private String selectedState;
	private String profileID;

	/**
	 * @return the houseNo
	 */
	public Integer getHouseNo() {
		return houseNo;
	}

	/**
	 * @param houseNo the houseNo to set
	 */
	public void setHouseNo(Integer houseNo) {
		this.houseNo = houseNo;
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
	 * @return the statesList
	 */
	public List<StatesVO> getStatesList() {
		return statesList;
	}

	/**
	 * @param statesList the statesList to set
	 */
	public void setStatesList(List<StatesVO> statesList) {
		this.statesList = statesList;
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
	 * @return the profileID
	 */
	public String getProfileID() {
		return profileID;
	}

	/**
	 * @param profileID the profileID to set
	 */
	public void setProfileID(String profileID) {
		this.profileID = profileID;
	}
}
