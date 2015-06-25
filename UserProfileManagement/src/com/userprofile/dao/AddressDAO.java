/**
 * 
 */
package com.userprofile.dao;

import java.util.List;

import com.userprofile.bo.AddressBO;

/**
 * @author analian
 *
 */
public interface AddressDAO {

	/**
	 * @param addressBO
	 */
	public void addNewAddress(AddressBO addressBO);
	
	/**
	 * @return
	 */
	public List<AddressBO> fetchAllAddresses();
	
	/**
	 * @param addressBO
	 */
	public void fetchAddress(AddressBO addressBO);
	
	/**
	 * @param addressBO
	 */
	void updateAddress(AddressBO addressBO);
	
	/**
	 * @param addressBO
	 */
	void deleteAddress(AddressBO addressBO);
	
}
