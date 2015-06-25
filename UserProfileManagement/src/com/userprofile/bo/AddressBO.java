/**
 * 
 */
package com.userprofile.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author analian
 *
 */
@Entity
@Table(name="ADDRESS")
public class AddressBO implements Serializable {

	/**
	 * generated
	 */
	private static final long serialVersionUID = -2088346074425187265L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	 int address_id;
	
	@Column(name="HOUSE_NUMBER")
	 Integer house_number;
	
	@Column(name="STREET")
	 String street;
	
	@Column(name="CITY")
	 String city;
	
	@Column(name="STATE")
	 String state;
	
	@Column(name="COUNTRY")
	 String country;

	
	@OneToOne
	@JoinColumn(name="STATE_ID")
	StatesBO statesBO;
	
	/**
	 * @return the address_id
	 */
	public int getAddress_id() {
		return address_id;
	}

	/**
	 * @param address_id the address_id to set
	 */
	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	/**
	 * @return the house_number
	 */
	public Integer getHouse_number() {
		return house_number;
	}

	/**
	 * @param house_number the house_number to set
	 */
	public void setHouse_number(Integer house_number) {
		this.house_number = house_number;
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
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the statesBO
	 */
	public StatesBO getStatesBO() {
		return statesBO;
	}

	/**
	 * @param statesBO the statesBO to set
	 */
	public void setStatesBO(StatesBO statesBO) {
		this.statesBO = statesBO;
	}

}
