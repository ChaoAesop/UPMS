/**
 * 
 */
package com.userprofile.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.userprofile.bo.AddressBO;
import com.userprofile.dao.AbstractDAO;
import com.userprofile.dao.AddressDAO;

/**
 * @author analian
 *
 */
@Repository("addressDAO")
@Transactional
public class AddressDAOImpl extends AbstractDAO<AddressBO> implements AddressDAO {

	/* (non-Javadoc)
	 * @see com.userprofile.dao.AddressDAO#addNewAddress()
	 */
	@Override
	public void addNewAddress(AddressBO entity) {
		super.create(entity);
	}

	/* (non-Javadoc)
	 * @see com.userprofile.dao.AddressDAO#updateAddress()
	 */
	@Override
	public void updateAddress(AddressBO addressBO) {
		super.update(addressBO);
	}

	/* (non-Javadoc)
	 * @see com.userprofile.dao.AddressDAO#deleteAddress()
	 */
	@Override
	public void deleteAddress(AddressBO addressBO) {
		super.delete(addressBO);
	}

	/* (non-Javadoc)
	 * @see com.userprofile.dao.AddressDAO#fetchAllAddresses()
	 */
	@Override
	public List<AddressBO> fetchAllAddresses() {
		List<AddressBO> addressBOs = super.findAll();
		return addressBOs;
	}

	/* (non-Javadoc)
	 * @see com.userprofile.dao.AddressDAO#fetchAddress()
	 */
	@Override
	public void fetchAddress(AddressBO addressBO) {
		
	}

}
