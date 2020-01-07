package com.jiang.beans;

import java.io.Serializable;
import java.util.Date;

/**订单项类*/
public class Orderopt implements Serializable{

	private static final long serialVersionUID = 1L;

	private String orderoptId;//订单项编号
	private String orderoptPaycustomerId;//买家id
	private String orderoptSellcustomerId;//卖家id
	private String orderoptGoodsId;//商品Id
	private String orderoptOrderId;//订单Id
	private String orderoptNum;//订单数目
	private String orderoptStatus;//状态
	private String orderoptComment;//备注
	private String orderoptPrice;//订单项价格
	private String orderoptEdittime;//最后操作时间
	
	/**买家*/
	private Customer orderoptPaycustomer;//买家id
	/**卖家*/
	private Customer orderoptSellcustomer;
	/**订单商品*/
	private Goods orderoptGoods;
	/**所属订单*/
	private Order orderoptOrder;
	
	public Orderopt() {
		// TODO Auto-generated constructor stub
	}

	public Orderopt(String orderoptId, String orderoptPaycustomerId, String orderoptSellcustomerId,
			String orderoptGoodsId, String orderoptOrderId, String orderoptNum, String orderoptStatus,
			String orderoptComment, String orderoptPrice, String orderoptEdittime) {
		super();
		this.orderoptId = orderoptId;
		this.orderoptPaycustomerId = orderoptPaycustomerId;
		this.orderoptSellcustomerId = orderoptSellcustomerId;
		this.orderoptGoodsId = orderoptGoodsId;
		this.orderoptOrderId = orderoptOrderId;
		this.orderoptNum = orderoptNum;
		this.orderoptStatus = orderoptStatus;
		this.orderoptComment = orderoptComment;
		this.orderoptPrice = orderoptPrice;
		this.orderoptEdittime = orderoptEdittime;
	}

	public String getOrderoptId() {
		return orderoptId;
	}

	public void setOrderoptId(String orderoptId) {
		this.orderoptId = orderoptId;
	}
	
	public String getOrderoptPaycustomerId() {
		return orderoptPaycustomerId;
	}

	public void setOrderoptPaycustomerId(String orderoptPaycustomerId) {
		this.orderoptPaycustomerId = orderoptPaycustomerId;
	}

	public Customer getOrderoptPaycustomer() {
		return orderoptPaycustomer;
	}

	public void setOrderoptPaycustomer(Customer orderoptPaycustomer) {
		this.orderoptPaycustomer = orderoptPaycustomer;
	}

	public String getOrderoptSellcustomerId() {
		return orderoptSellcustomerId;
	}

	public void setOrderoptSellcustomerId(String orderoptSellcustomerId) {
		this.orderoptSellcustomerId = orderoptSellcustomerId;
	}

	public String getOrderoptGoodsId() {
		return orderoptGoodsId;
	}

	public void setOrderoptGoodsId(String orderoptGoodsId) {
		this.orderoptGoodsId = orderoptGoodsId;
	}

	public String getOrderoptOrderId() {
		return orderoptOrderId;
	}

	public void setOrderoptOrderId(String orderoptOrderId) {
		this.orderoptOrderId = orderoptOrderId;
	}

	public String getOrderoptNum() {
		return orderoptNum;
	}

	public void setOrderoptNum(String orderoptNum) {
		this.orderoptNum = orderoptNum;
	}

	public String getOrderoptStatus() {
		return orderoptStatus;
	}

	public void setOrderoptStatus(String orderoptStatus) {
		this.orderoptStatus = orderoptStatus;
	}

	public String getOrderoptComment() {
		return orderoptComment;
	}

	public void setOrderoptComment(String orderoptComment) {
		this.orderoptComment = orderoptComment;
	}

	public String getOrderoptPrice() {
		return orderoptPrice;
	}

	public void setOrderoptPrice(String orderoptPrice) {
		this.orderoptPrice = orderoptPrice;
	}

	public Customer getOrderoptSellcustomer() {
		return orderoptSellcustomer;
	}

	public void setOrderoptSellcustomer(Customer orderoptSellcustomer) {
		this.orderoptSellcustomer = orderoptSellcustomer;
	}

	public Goods getOrderoptGoods() {
		return orderoptGoods;
	}

	public void setOrderoptGoods(Goods orderoptGoods) {
		this.orderoptGoods = orderoptGoods;
	}

	public Order getOrderoptOrder() {
		return orderoptOrder;
	}

	public void setOrderoptOrder(Order orderoptOrder) {
		this.orderoptOrder = orderoptOrder;
	}

	public String getOrderoptEdittime() {
		return orderoptEdittime;
	}

	public void setOrderoptEdittime(String orderoptEdittime) {
		this.orderoptEdittime = orderoptEdittime;
	}
	
	
}
