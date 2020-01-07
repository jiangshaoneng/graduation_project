package com.jiang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.beans.Customer;
import com.jiang.dao.CustomerMapper;

@Service
public class LoginService {
	
	@Autowired
	CustomerMapper customerMapper;
	
	/**通过用户名查找用户*/
	public Customer customerLogin(String name) {
		Customer customer = customerMapper.findCustomerByName(name);
		return customer;
	}
	
	
	
	
}
