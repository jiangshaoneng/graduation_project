package com.jiang.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/** 
 * 顾客类
 * */
public class Customer implements Serializable{
	private String cusId;//用户的编号(在工具包中调用生成主键的方法)
	private String cusName;//用户名称
	private String cusRealname;//用户真实姓名
	private String cusPassword;//用户密码
	private String cusGender;//用户性别
	private String cusPhone;//用户电话
	private Date cusBirthday;//用户生日
	/*private String cusAddress;//用户地址*/
	private String cusRegtime;//注册时间(注册时的系统时间)
	private String cusBalance;//余额(默认为0)
	private String cusStatus;//用户状态(注册后：激活；管理员可冻结,解冻)
	private String cusScore;//用户积分 (默认0积分)
	private String cusEmail;//邮箱
	private String cusType;//用户类型 (未绑定邮箱：普通用户；绑定邮箱：认证用户)
	
	/**顾客的地址*/
	private List<Address> address;
	/**顾客的商品*/
	private List<Goods> goods;
	/**顾客的订单*/
	private List<Order> order;
	/**顾客的收藏*/
	private List<Collection> collection;
	/**顾客的评论*/
	private List<Comment> Comment;
	
	///////////////////构造器///////////////////////
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


	///////////////////get/set方法///////////////////////
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

	///////////////////toString方法///////////////////////
	@Override
	public String toString() {
		return "Customer [cusId=" + cusId + ", cusName=" + cusName + ", cusRealname=" + cusRealname + ", cusPassword="
				+ cusPassword + ", cusGender=" + cusGender + ", cusPhone=" + cusPhone + ", cusBirthday=" + cusBirthday
				+ ", cusRegtime=" + cusRegtime + ", cusBalance=" + cusBalance + ", cusStatus=" + cusStatus
				+ ", cusScore=" + cusScore + ", cusEmail=" + cusEmail + ", cusType=" + cusType + "]";
	}
	
}
