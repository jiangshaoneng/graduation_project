package com.jiang.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**订单类*/
public class Order implements Serializable{

	private static final long serialVersionUID = 1L;

	private String orderId;//订单编号
	private Date orderCreatetime;//创建日期
	private Date orderPaytime;//支付日期
	private String orderStatus;//订单状态
	private String orderComment;//备注
	private String orderAddress;//地址
	private String orderTotalprice;//总价
	private String orderPaytype;//支付类型
	private String orderPaycustomerId;//卖家Id
	
	/**买家*/
	private Customer orderPaycustomer;
	
	/**订单项*/
	private List<Orderopt> orderoptList;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(String orderId, Date orderCreatetime, Date orderPaytime, String orderStatus, String orderComment,
			String orderAddress, String orderTotalprice, String orderPaytype, String orderPaycustomerId) {
		super();
		this.orderId = orderId;
		this.orderCreatetime = orderCreatetime;
		this.orderPaytime = orderPaytime;
		this.orderStatus = orderStatus;
		this.orderComment = orderComment;
		this.orderAddress = orderAddress;
		this.orderTotalprice = orderTotalprice;
		this.orderPaytype = orderPaytype;
		this.orderPaycustomerId = orderPaycustomerId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Date getOrderCreatetime() {
		return orderCreatetime;
	}

	public void setOrderCreatetime(Date orderCreatetime) {
		this.orderCreatetime = orderCreatetime;
	}

	public Date getOrderPaytime() {
		return orderPaytime;
	}

	public void setOrderPaytime(Date orderPaytime) {
		this.orderPaytime = orderPaytime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderComment() {
		return orderComment;
	}

	public void setOrderComment(String orderComment) {
		this.orderComment = orderComment;
	}

	public String getOrderAddress() {
		return orderAddress;
	}

	public void setOrderAddress(String orderAddress) {
		this.orderAddress = orderAddress;
	}

	public String getOrderTotalprice() {
		return orderTotalprice;
	}

	public void setOrderTotalprice(String orderTotalprice) {
		this.orderTotalprice = orderTotalprice;
	}

	public String getOrderPaytype() {
		return orderPaytype;
	}

	public void setOrderPaytype(String orderPaytype) {
		this.orderPaytype = orderPaytype;
	}

	public String getOrderPaycustomerId() {
		return orderPaycustomerId;
	}

	public void setOrderPaycustomerId(String orderPaycustomerId) {
		this.orderPaycustomerId = orderPaycustomerId;
	}

	public Customer getOrderPaycustomer() {
		return orderPaycustomer;
	}

	public void setOrderPaycustomer(Customer orderPaycustomer) {
		this.orderPaycustomer = orderPaycustomer;
	}

	public List<Orderopt> getOrderoptList() {
		return orderoptList;
	}

	public void setOrderoptList(List<Orderopt> orderoptList) {
		this.orderoptList = orderoptList;
	}

	
}
