package com.jiang.dao;

import java.util.List;
import java.util.Map;

import com.jiang.beans.Customer;

public interface CustomerMapper {
	
	/**ͨ���û������Ҳ��û�*/
	public Customer findCustomerByName(String name);
	
	/**ͨ���˿ͱ��,�޸��û�����Ϣ*/
	public boolean updateCustomer(Customer customer);
	
	/**ͨ���˿ͱ��,�޸��û�״̬*/
	public boolean freezeCustomer(String status,String cusId);
	
	/**����û���Ϣ��ע��*/
	public boolean addCustomer(Customer customer);
	
	/**ͨ���û���Ų��Ҳ��û�*/
	public Customer findCustomerById(String cusId);
	
	/**����Ա��ѯ�û�����*/
	public Integer manageFindCustomerCount(Map<String,Object> map);

	/**����Ա��ѯ�û�*/
	public List<Customer> manageFindCustomer(Map<String,Object> map);
	
}
