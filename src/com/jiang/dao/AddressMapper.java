package com.jiang.dao;

import java.util.List;

import com.jiang.beans.Address;

public interface AddressMapper {

	/**ͨ���˿͵ı�Ų�ѯ�ù˿͵����е�ַ*/
	public List<Address> findAddressByCusId(String cusId);
	
	/**ͨ���˿͵ı�Ų�ѯ�ù˿͵�Ĭ�ϵ�ַ*/
	public Address findDefAddressByCusId(String cusId);
	
	/**ͨ����ַ���*/
	public Address findAddressById(String addressId);
	
	/**���õ�ַ����ͨ����*/
	public boolean updateAddress(String addressId);
	
	/**���õ�ַ��Ĭ������*/
	public boolean updateDefAddress(String addressId);
	
	/**�����ɾ����ַ*/
	public boolean delAddress(String addressId);
	
	/**�����µ�ַ*/
	public boolean insertAddress(Address address);
	
	/**�޸ĵ�ַ��Ϣ*/
	public boolean updateAddressInfo(Address address);
	
}
