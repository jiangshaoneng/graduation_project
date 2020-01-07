package com.jiang.dao;

import java.util.List;
import java.util.Map;

import com.jiang.beans.Customer;

public interface CustomerMapper {
	
	/**通过用户名查找查用户*/
	public Customer findCustomerByName(String name);
	
	/**通过顾客编号,修改用户的信息*/
	public boolean updateCustomer(Customer customer);
	
	/**通过顾客编号,修改用户状态*/
	public boolean freezeCustomer(String status,String cusId);
	
	/**添加用户信息：注册*/
	public boolean addCustomer(Customer customer);
	
	/**通过用户编号查找查用户*/
	public Customer findCustomerById(String cusId);
	
	/**管理员查询用户数量*/
	public Integer manageFindCustomerCount(Map<String,Object> map);

	/**管理员查询用户*/
	public List<Customer> manageFindCustomer(Map<String,Object> map);
	
}
