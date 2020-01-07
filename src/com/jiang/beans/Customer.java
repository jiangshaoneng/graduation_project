package com.jiang.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/** 
 * �˿���
 * */
public class Customer implements Serializable{
	private String cusId;//�û��ı��(�ڹ��߰��е������������ķ���)
	private String cusName;//�û�����
	private String cusRealname;//�û���ʵ����
	private String cusPassword;//�û�����
	private String cusGender;//�û��Ա�
	private String cusPhone;//�û��绰
	private Date cusBirthday;//�û�����
	/*private String cusAddress;//�û���ַ*/
	private String cusRegtime;//ע��ʱ��(ע��ʱ��ϵͳʱ��)
	private String cusBalance;//���(Ĭ��Ϊ0)
	private String cusStatus;//�û�״̬(ע��󣺼������Ա�ɶ���,�ⶳ)
	private String cusScore;//�û����� (Ĭ��0����)
	private String cusEmail;//����
	private String cusType;//�û����� (δ�����䣺��ͨ�û��������䣺��֤�û�)
	
	/**�˿͵ĵ�ַ*/
	private List<Address> address;
	/**�˿͵���Ʒ*/
	private List<Goods> goods;
	/**�˿͵Ķ���*/
	private List<Order> order;
	/**�˿͵��ղ�*/
	private List<Collection> collection;
	/**�˿͵�����*/
	private List<Comment> Comment;
	
	///////////////////������///////////////////////
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String cusId, String cusName, String cusRealname, String cusPassword, String cusGender,
			String cusPhone, Date cusBirthday, /*String cusAddress,*/ String cusRegtime, String cusBalance,
			String cusStatus, String cusScore, String cusEmail, String cusType) {
		super();
		this.cusId = cusId;
		this.cusName = cusName;
		this.cusRealname = cusRealname;
		this.cusPassword = cusPassword;
		this.cusGender = cusGender;
		this.cusPhone = cusPhone;
		this.cusBirthday = cusBirthday;
		/*this.cusAddress = cusAddress;*/
		this.cusRegtime = cusRegtime;
		this.cusBalance = cusBalance;
		this.cusStatus = cusStatus;
		this.cusScore = cusScore;
		this.cusEmail = cusEmail;
		this.cusType = cusType;
	}


	///////////////////get/set����///////////////////////
	public String getcusId() {
		return cusId;
	}
	public void setcusId(String cusId) {
		this.cusId = cusId;
	}
	public String getcusName() {
		return cusName;
	}
	public void setcusName(String cusName) {
		this.cusName = cusName;
	}
	public String getcusRealname() {
		return cusRealname;
	}
	public void setcusRealname(String cusRealname) {
		this.cusRealname = cusRealname;
	}
	public String getcusPassword() {
		return cusPassword;
	}
	public void setcusPassword(String cusPassword) {
		this.cusPassword = cusPassword;
	}
	public String getcusGender() {
		return cusGender;
	}
	public void setcusGender(String cusGender) {
		this.cusGender = cusGender;
	}
	public String getcusPhone() {
		return cusPhone;
	}
	public void setcusPhone(String cusPhone) {
		this.cusPhone = cusPhone;
	}
	public Date getcusBirthday() {
		return cusBirthday;
	}
	public void setcusBirthday(Date cusBirthday) {
		this.cusBirthday = cusBirthday;
	}
/*	public String getcusAddress() {
		return cusAddress;
	}
	public void setcusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}*/
	public String getcusRegtime() {
		return cusRegtime;
	}
	public void setcusRegtime(String cusRegtime) {
		this.cusRegtime = cusRegtime;
	}
	public String getcusBalance() {
		return cusBalance;
	}
	public void setcusBalance(String cusBalance) {
		this.cusBalance = cusBalance;
	}
	public String getcusStatus() {
		return cusStatus;
	}
	public void setcusStatus(String cusStatus) {
		this.cusStatus = cusStatus;
	}
	public String getcusScore() {
		return cusScore;
	}
	public void setcusScore(String cusScore) {
		this.cusScore = cusScore;
	}
	public String getcusEmail() {
		return cusEmail;
	}
	public void setcusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}
	public String getcusType() {
		return cusType;
	}
	public void setcusType(String cusType) {
		this.cusType = cusType;
	}
	
	
	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public List<Goods> getGoods() {
		return goods;
	}

	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public List<Collection> getCollection() {
		return collection;
	}

	public void setCollection(List<Collection> collection) {
		this.collection = collection;
	}

	public List<Comment> getComment() {
		return Comment;
	}

	public void setComment(List<Comment> comment) {
		Comment = comment;
	}

	///////////////////toString����///////////////////////
	@Override
	public String toString() {
		return "Customer [cusId=" + cusId + ", cusName=" + cusName + ", cusRealname=" + cusRealname + ", cusPassword="
				+ cusPassword + ", cusGender=" + cusGender + ", cusPhone=" + cusPhone + ", cusBirthday=" + cusBirthday
				+ ", cusRegtime=" + cusRegtime + ", cusBalance=" + cusBalance + ", cusStatus=" + cusStatus
				+ ", cusScore=" + cusScore + ", cusEmail=" + cusEmail + ", cusType=" + cusType + "]";
	}
	
}
