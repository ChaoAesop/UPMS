package com.userprofile.bo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_OTP")
public class LoginBO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "USER_ID", nullable = false)
	private String user_id;
	
	@Column(name = "OTP", nullable = false)
	private String OTPString;
	
	@Column(name = "DATE_TIMESTAMP", nullable = false)
	private Timestamp dateTimeStamp;

	@OneToOne
	@JoinColumn(name="USER_ID",insertable=false, updatable=false)
	private UserBO userBO;
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


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
	 * @return the dateTimeStamp
	 */
	public Timestamp getDateTimeStamp() {
		return dateTimeStamp;
	}
	/**
	 * @param l the dateTimeStamp to set
	 */
	public void setDateTimeStamp(Timestamp timestamp) {
		this.dateTimeStamp = timestamp;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the userBO
	 */
	public UserBO getUserBO() {
		return userBO;
	}

	/**
	 * @param userBO the userBO to set
	 */
	public void setUserBO(UserBO userBO) {
		this.userBO = userBO;
	}
	
	
}
