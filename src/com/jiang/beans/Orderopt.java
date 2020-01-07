package com.jiang.beans;

import java.io.Serializable;
import java.util.Date;

/**��������*/
public class Orderopt implements Serializable{

	private static final long serialVersionUID = 1L;

	private String orderoptId;//��������
	private String orderoptPaycustomerId;//���id
	private String orderoptSellcustomerId;//����id
	private String orderoptGoodsId;//��ƷId
	private String orderoptOrderId;//����Id
	private String orderoptNum;//������Ŀ
	private String orderoptStatus;//״̬
	private String orderoptComment;//��ע
	private String orderoptPrice;//������۸�
	private String orderoptEdittime;//������ʱ��
	
	/**���*/
	private Customer orderoptPaycustomer;//���id
	/**����*/
	private Customer orderoptSellcustomer;
	/**������Ʒ*/
	private Goods orderoptGoods;
	/**��������*/
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
