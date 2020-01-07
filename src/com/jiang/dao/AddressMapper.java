package com.jiang.dao;

import java.util.List;

import com.jiang.beans.Address;

public interface AddressMapper {

	/**通过顾客的编号查询该顾客的所有地址*/
	public List<Address> findAddressByCusId(String cusId);
	
	/**通过顾客的编号查询该顾客的默认地址*/
	public Address findDefAddressByCusId(String cusId);
	
	/**通过地址编号*/
	public Address findAddressById(String addressId);
	
	/**设置地址的普通属性*/
	public boolean updateAddress(String addressId);
	
	/**设置地址的默认属性*/
	public boolean updateDefAddress(String addressId);
	
	/**按编号删除地址*/
	public boolean delAddress(String addressId);
	
	/**插入新地址*/
	public boolean insertAddress(Address address);
	
	/**修改地址信息*/
	public boolean updateAddressInfo(Address address);
	
}
