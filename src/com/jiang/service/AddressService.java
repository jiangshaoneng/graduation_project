package com.jiang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiang.beans.Address;
import com.jiang.dao.AddressMapper;

@Service
public class AddressService {

	@Autowired
	private AddressMapper addressMapper;
	
	//
	public List<Address> findAddressListByCusId(String cusId){
		return addressMapper.findAddressByCusId(cusId);
	}
	
}
