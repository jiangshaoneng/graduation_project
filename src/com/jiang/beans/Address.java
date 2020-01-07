package com.jiang.beans;

import java.io.Serializable;

/** 
 * 顾客地址表
 * */
public class Address implements Serializable{

	private String addressId;//地址编号
	private String addressProvince;//省
	private String addressCity;//市
	private String addressDistrict;//区
	private String addressDescInfo;//地址信息
	private String addressDefault;//是否为默认地址(默认地址;普通地址)
	private String addressCustomerId;//地址所属的用户Id
	
	/**地址所属的用户*/
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
