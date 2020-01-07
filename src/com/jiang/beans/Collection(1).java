package com.jiang.beans;

import java.io.Serializable;

/** 顾客收藏商品表 */
public class Collection implements Serializable{
	
	private String collectionId;//收藏单编号
	private String collectionAddtime;//收藏的时间
	
	/**收藏的商品*/
	private Goods collectionGoods;
	/**收藏者*/
	private Customer collectionCustomer;
	
	///////////////////构造器///////////////////////
	public Collection() {
		// TODO Auto-generated constructor stub
	}

	public Collection(String collectionId, String collectionAddtime) {
		super();
		this.collectionId = collectionId;
		this.collectionAddtime = collectionAddtime;
	}

	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public String getCollectionAddtime() {
		return collectionAddtime;
	}

	public void setCollectionAddtime(String collectionAddtime) {
		this.collectionAddtime = collectionAddtime;
	}

	public Goods getCollectionGoods() {
		return collectionGoods;
	}

	public void setCollectionGoods(Goods collectionGoods) {
		this.collectionGoods = collectionGoods;
	}

	public Customer getCollectionCustomer() {
		return collectionCustomer;
	}

	public void setCollectionCustomer(Customer collectionCustomer) {
		this.collectionCustomer = collectionCustomer;
	}

	@Override
	public String toString() {
		return "Collection [collectionId=" + collectionId + ", collectionAddtime=" + collectionAddtime + "]";
	}
	
	
}
