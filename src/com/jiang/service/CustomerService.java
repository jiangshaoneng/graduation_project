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

	
	/**检查用户名是否可用*/
	public boolean okCusName(String name) {
		Customer customer = customerMapper.findCustomerByName(name);
		if(customer!=null) {
			return false;			
		}else {
			return true;
		}
	}
	
	
	/**注册基本用户*/
	public boolean addCustomerInfo(String cusNickname,String cusRealname,String cusPassword,String cusGender,String cusPhone,String cusBirthday) {
		//将基本信息封装成对象
		Customer customer = new Customer();
		
		customer.setcusId(NewIdUtil.getId());
		customer.setcusName(cusNickname);
		customer.setcusRealname(cusRealname);
		customer.setcusPassword(cusPassword);
		customer.setcusGender(cusGender);
		customer.setcusPhone(cusPhone);
		customer.setcusBirthday(TimeUtil.formatDate(cusBirthday));
		customer.setcusType("普通用户");
		customer.setcusStatus("激活");
		return customerMapper.addCustomer(customer);
	}
	
	/**添加一条地址信息：注册时,设置为默认地址*/
	public boolean addAddress(String cusName,String addressProvince,String addressCity,
			String addressDistrict,String addressDescInfo) {
		Address address= new Address();
		address.setAddressId(NewIdUtil.getId());
		address.setAddressDefault("默认地址");
		address.setAddressProvince(addressProvince);
		address.setAddressCity(addressCity);
		address.setAddressDistrict(addressDistrict);
		address.setAddressDescInfo(addressDescInfo);
		//根据名称查询出用户
		Customer customer = customerMapper.findCustomerByName(cusName);
		address.setAddressCustomerId(customer.getcusId());
		return addressMapper.insertAddress(address);
	}
	
	/**绑定用户:添加邮箱，同时修改用的类型为：认证用户*/
	public boolean addEmail(String nickName,String email) {
		//通过用户名查询出用户用户的信息
		Customer customer = customerMapper.findCustomerByName(nickName);
		customer.setcusType("认证用户");
		customer.setcusEmail(email);
		return customerMapper.updateCustomer(customer);
	}
	
	public Customer findCustomerByCusId(String cusId) {
		return customerMapper.findCustomerById(cusId);
	}
	
	/**通过用户的编号查询用户的所有地址*/
	public List<Address> findAddressByCusId(String cusId){
		return addressMapper.findAddressByCusId(cusId);
	}
	
	/**修改个人信息*/
	public boolean updateCustomer(String cusId,String nickName,String cusGender,String cusBirthday,String cusPhone){
		//通过用户编号查询出顾客信息,进行修改
		Customer customer = customerMapper.findCustomerById(cusId);
		customer.setcusName(nickName);
		customer.setcusGender(cusGender);
		customer.setcusBirthday(TimeUtil.formatDate(cusBirthday));
		customer.setcusPhone(cusPhone);
		return customerMapper.updateCustomer(customer);
	}
	
	/**校验密码是否正确*/
	public boolean okOldpassword(String cusId,String password) {
		Customer customer = customerMapper.findCustomerById(cusId);
		if(customer.getcusPassword().equals(password)) {
			return true;			
		}else {
			return false;
		}
	}
	
	/**修改密码*/
	public boolean updatePwd(String cusId,String newPassword) {
		Customer customer = customerMapper.findCustomerById(cusId);
		customer.setcusPassword(newPassword);
		return customerMapper.updateCustomer(customer);
	}
	
	/**充值*/
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
	
	/**设置默认地址*/
	public boolean setDefAddress(String cusId,String addressId) {
		//将原来的默认地址改为普通地址
		Address defAddress= addressMapper.findDefAddressByCusId(cusId);
		boolean updateAddress = false;
		if(defAddress!=null) {
			updateAddress = addressMapper.updateAddress(defAddress.getAddressId());
		}else {
			updateAddress = true;
		}
		if(updateAddress) {
			//将页面传来的地址设置成默认地址
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
	
	/**添加地址*/
	public boolean addAddress2(String cusId,String addressProvince,String addressCity,
				String addressDistrict,String addressDescInfo) {
		
		Address address = new Address();
		address.setAddressId(NewIdUtil.getId());
		address.setAddressProvince(addressProvince);
		address.setAddressCity(addressCity);
		address.setAddressDistrict(addressDistrict);
		address.setAddressDescInfo(addressDescInfo);
		address.setAddressCustomerId(cusId);
		address.setAddressDefault("普通地址");
		
		return addressMapper.insertAddress(address);
	}
	
	/**删除地址*/
	public boolean delAddress(String addressId) {
		return addressMapper.delAddress(addressId);
	}
	
	/**修改地址*/
	public boolean updateAddress(String addressId,String addressProvince,String addressCity,
			String addressDistrict,String addressDescInfo) {
		Address address = addressMapper.findAddressById(addressId);
		address.setAddressProvince(addressProvince);
		address.setAddressCity(addressCity);
		address.setAddressDistrict(addressDistrict);
		address.setAddressDescInfo(addressDescInfo);
		return addressMapper.updateAddressInfo(address);
	}
	
	/**修改邮箱*/
	public boolean updateEmail(String cusId,String email) {
		Customer customer = customerMapper.findCustomerById(cusId);
		customer.setcusEmail(email);
		return customerMapper.updateCustomer(customer);
	}
	
	
	/**分页查询用户收藏的商品*/
	public Page findCusCollection() {
		
		return null;
	}
	
}
