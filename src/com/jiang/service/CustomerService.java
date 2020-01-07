package com.jiang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.beans.Address;
import com.jiang.beans.Customer;
import com.jiang.dao.AddressMapper;
import com.jiang.dao.CustomerMapper;
import com.jiang.servicepage.Page;
import com.jiang.utils.NewIdUtil;
import com.jiang.utils.TimeUtil;

@Service
public class CustomerService {

	@Autowired
	CustomerMapper customerMapper;
	@Autowired
	AddressMapper addressMapper; 

	
	/**����û����Ƿ����*/
	public boolean okCusName(String name) {
		Customer customer = customerMapper.findCustomerByName(name);
		if(customer!=null) {
			return false;			
		}else {
			return true;
		}
	}
	
	
	/**ע������û�*/
	public boolean addCustomerInfo(String cusNickname,String cusRealname,String cusPassword,String cusGender,String cusPhone,String cusBirthday) {
		//��������Ϣ��װ�ɶ���
		Customer customer = new Customer();
		
		customer.setcusId(NewIdUtil.getId());
		customer.setcusName(cusNickname);
		customer.setcusRealname(cusRealname);
		customer.setcusPassword(cusPassword);
		customer.setcusGender(cusGender);
		customer.setcusPhone(cusPhone);
		customer.setcusBirthday(TimeUtil.formatDate(cusBirthday));
		customer.setcusType("��ͨ�û�");
		customer.setcusStatus("����");
		return customerMapper.addCustomer(customer);
	}
	
	/**���һ����ַ��Ϣ��ע��ʱ,����ΪĬ�ϵ�ַ*/
	public boolean addAddress(String cusName,String addressProvince,String addressCity,
			String addressDistrict,String addressDescInfo) {
		Address address= new Address();
		address.setAddressId(NewIdUtil.getId());
		address.setAddressDefault("Ĭ�ϵ�ַ");
		address.setAddressProvince(addressProvince);
		address.setAddressCity(addressCity);
		address.setAddressDistrict(addressDistrict);
		address.setAddressDescInfo(addressDescInfo);
		//�������Ʋ�ѯ���û�
		Customer customer = customerMapper.findCustomerByName(cusName);
		address.setAddressCustomerId(customer.getcusId());
		return addressMapper.insertAddress(address);
	}
	
	/**���û�:������䣬ͬʱ�޸��õ�����Ϊ����֤�û�*/
	public boolean addEmail(String nickName,String email) {
		//ͨ���û�����ѯ���û��û�����Ϣ
		Customer customer = customerMapper.findCustomerByName(nickName);
		customer.setcusType("��֤�û�");
		customer.setcusEmail(email);
		return customerMapper.updateCustomer(customer);
	}
	
	public Customer findCustomerByCusId(String cusId) {
		return customerMapper.findCustomerById(cusId);
	}
	
	/**ͨ���û��ı�Ų�ѯ�û������е�ַ*/
	public List<Address> findAddressByCusId(String cusId){
		return addressMapper.findAddressByCusId(cusId);
	}
	
	/**�޸ĸ�����Ϣ*/
	public boolean updateCustomer(String cusId,String nickName,String cusGender,String cusBirthday,String cusPhone){
		//ͨ���û���Ų�ѯ���˿���Ϣ,�����޸�
		Customer customer = customerMapper.findCustomerById(cusId);
		customer.setcusName(nickName);
		customer.setcusGender(cusGender);
		customer.setcusBirthday(TimeUtil.formatDate(cusBirthday));
		customer.setcusPhone(cusPhone);
		return customerMapper.updateCustomer(customer);
	}
	
	/**У�������Ƿ���ȷ*/
	public boolean okOldpassword(String cusId,String password) {
		Customer customer = customerMapper.findCustomerById(cusId);
		if(customer.getcusPassword().equals(password)) {
			return true;			
		}else {
			return false;
		}
	}
	
	/**�޸�����*/
	public boolean updatePwd(String cusId,String newPassword) {
		Customer customer = customerMapper.findCustomerById(cusId);
		customer.setcusPassword(newPassword);
		return customerMapper.updateCustomer(customer);
	}
	
	/**��ֵ*/
	public boolean addMoney(String cusId,String money) {
		Customer customer = customerMapper.findCustomerById(cusId);
		float addMoney = 0;
		if(money!=null) {
			addMoney = Float.parseFloat((money));			
		}
		String balance = ""+(Float.parseFloat(customer.getcusBalance())+addMoney);
		customer.setcusBalance(balance);
		return customerMapper.updateCustomer(customer);
	}
	
	/**����Ĭ�ϵ�ַ*/
	public boolean setDefAddress(String cusId,String addressId) {
		//��ԭ����Ĭ�ϵ�ַ��Ϊ��ͨ��ַ
		Address defAddress= addressMapper.findDefAddressByCusId(cusId);
		boolean updateAddress = false;
		if(defAddress!=null) {
			updateAddress = addressMapper.updateAddress(defAddress.getAddressId());
		}else {
			updateAddress = true;
		}
		if(updateAddress) {
			//��ҳ�洫���ĵ�ַ���ó�Ĭ�ϵ�ַ
			boolean updateDefAddress = addressMapper.updateDefAddress(addressId);
			if(updateDefAddress) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;			
		}
	}
	
	/**��ӵ�ַ*/
	public boolean addAddress2(String cusId,String addressProvince,String addressCity,
				String addressDistrict,String addressDescInfo) {
		
		Address address = new Address();
		address.setAddressId(NewIdUtil.getId());
		address.setAddressProvince(addressProvince);
		address.setAddressCity(addressCity);
		address.setAddressDistrict(addressDistrict);
		address.setAddressDescInfo(addressDescInfo);
		address.setAddressCustomerId(cusId);
		address.setAddressDefault("��ͨ��ַ");
		
		return addressMapper.insertAddress(address);
	}
	
	/**ɾ����ַ*/
	public boolean delAddress(String addressId) {
		return addressMapper.delAddress(addressId);
	}
	
	/**�޸ĵ�ַ*/
	public boolean updateAddress(String addressId,String addressProvince,String addressCity,
			String addressDistrict,String addressDescInfo) {
		Address address = addressMapper.findAddressById(addressId);
		address.setAddressProvince(addressProvince);
		address.setAddressCity(addressCity);
		address.setAddressDistrict(addressDistrict);
		address.setAddressDescInfo(addressDescInfo);
		return addressMapper.updateAddressInfo(address);
	}
	
	/**�޸�����*/
	public boolean updateEmail(String cusId,String email) {
		Customer customer = customerMapper.findCustomerById(cusId);
		customer.setcusEmail(email);
		return customerMapper.updateCustomer(customer);
	}
	
	
	/**��ҳ��ѯ�û��ղص���Ʒ*/
	public Page findCusCollection() {
		
		return null;
	}
	
}
