/**
 * 
 */
package com.userprofile.bo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author analian
 *
 */
@Entity
@Table(name = "USER")
public class UserBO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String user_id;

	@Column(name = "USER_NAME", nullable = false)
	private String user_name;

	@Column(name = "EMAIL_ID")
	private String email_id;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@OneToOne(mappedBy = "userBO")
	private LoginBO loginBO;

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name = "USER_ADDRESS", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "address_id") })
	private Set<AddressBO> addressBOs;

	/**
	 * @return the user_id
	 */
	public String getId() {
		return user_id;
	}

	/**
	 * @param user_id
	 *            the user_id to set
	 */
	public void setId(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}

	/**
	 * @param user_name
	 *            the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * @return the email_id
	 */
	public String getEmail_id() {
		return email_id;
	}

	/**
	 * @param email_id
	 *            the email_id to set
	 */
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the loginBO
	 */
	public LoginBO getLoginBO() {
		return loginBO;
	}

	/**
	 * @param loginBO
	 *            the loginBO to set
	 */
	public void setLoginBO(LoginBO loginBO) {
		this.loginBO = loginBO;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id
	 *            the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the addressBOs
	 */
	public Set<AddressBO> getAddressBOs() {
		return addressBOs;
	}

	/**
	 * @param addressBOs
	 *            the addressBOs to set
	 */
	public void setAddressBOs(Set<AddressBO> addressBOs) {
		this.addressBOs = addressBOs;
	}

}
