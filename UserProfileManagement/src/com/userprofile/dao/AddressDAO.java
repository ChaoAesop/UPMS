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

	public void addNewAddress(AddressBO addressBO);
	public List<AddressBO> fetchAllAddresses();
	public void fetchAddress(AddressBO addressBO);
	void updateAddress(AddressBO addressBO);
	void deleteAddress(AddressBO addressBO);
	
}
