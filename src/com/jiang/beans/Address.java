package com.jiang.beans;

import java.io.Serializable;

/** 
 * �˿͵�ַ��
 * */
public class Address implements Serializable{

	private String addressId;//��ַ���
	private String addressProvince;//ʡ
	private String addressCity;//��
	private String addressDistrict;//��
	private String addressDescInfo;//��ַ��Ϣ
	private String addressDefault;//�Ƿ�ΪĬ�ϵ�ַ(Ĭ�ϵ�ַ;��ͨ��ַ)
	private String addressCustomerId;//��ַ�������û�Id
	
	/**��ַ�������û�*/
	private Customer addressCustomer;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(String addressId, String addressProvince, String addressCity, String addressDistrict,
			String addressDescInfo, String addressDefault, String addressCustomerId) {
		super();
		this.addressId = addressId;
		this.addressProvince = addressProvince;
		this.addressCity = addressCity;
		this.addressDistrict = addressDistrict;
		this.addressDescInfo = addressDescInfo;
		this.addressDefault = addressDefault;
		this.addressCustomerId = addressCustomerId;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getAddressProvince() {
		return addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressDistrict() {
		return addressDistrict;
	}

	public void setAddressDistrict(String addressDistrict) {
		this.addressDistrict = addressDistrict;
	}

	public String getAddressDescInfo() {
		return addressDescInfo;
	}

	public void setAddressDescInfo(String addressDescInfo) {
		this.addressDescInfo = addressDescInfo;
	}

	public String getAddressDefault() {
		return addressDefault;
	}

	public void setAddressDefault(String addressDefault) {
		this.addressDefault = addressDefault;
	}

	public String getAddressCustomerId() {
		return addressCustomerId;
	}

	public void setAddressCustomerId(String addressCustomerId) {
		this.addressCustomerId = addressCustomerId;
	}

	public Customer getAddressCustomer() {
		return addressCustomer;
	}

	public void setAddressCustomer(Customer addressCustomer) {
		this.addressCustomer = addressCustomer;
	}
	
}
