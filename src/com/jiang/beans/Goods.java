package com.jiang.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**商品表*/
public class Goods implements Serializable{

	private String goodsId;//商品编号
	private String goodsName;//商品名称
	private String goodsPrice;//商品价格
	private String goodsInfo;//商品描述信息
	private Date goodsAddtime;//添加时间
	private String goodsAddtimeStr;//----时间字符串
	private String goodsType;//商品类型
	private String goodsStatus;//商品状态(出售中,已售出,已移除)
	private String goodsTotal;//商品数量
	private String goodsPaytype;//交易方式(线上交易,线下交易)
	private String goodsAddress;//商品的地址
	private String goodsCustomerId;
	
	/** 商品所属的顾客 */
	private Customer goodsCustomer;
	/** 商品的图片 */
	private List<Images> images;
	/**商品收藏*/
	private List<Collection> collection;
	/**商品的评论*/
	private List<Comment> Comment;
	/**商品的评论*/
	private List<Orderopt> orderopt;
	
	///////////////////构造器///////////////////////////
	public Goods() {
		// TODO Auto-generated constructor stub
	}
	

	public Goods(String goodsId, String goodsName, String goodsPrice, String goodsInfo, Date goodsAddtime,
			String goodsAddtimeStr, String goodsType, String goodsStatus, String goodsTotal, String goodsPaytype,
			String goodsAddress, String goodsCustomerId) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsInfo = goodsInfo;
		this.goodsAddtime = goodsAddtime;
		this.goodsAddtimeStr = goodsAddtimeStr;
		this.goodsType = goodsType;
		this.goodsStatus = goodsStatus;
		this.goodsTotal = goodsTotal;
		this.goodsPaytype = goodsPaytype;
		this.goodsAddress = goodsAddress;
		this.goodsCustomerId = goodsCustomerId;
	}



	///////////////////get/set方法///////////////////////
	public String getGoodsCustomerId() {
		return goodsCustomerId;
	}

	public void setGoodsCustomerId(String goodsCustomerId) {
		this.goodsCustomerId = goodsCustomerId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public Date getGoodsAddtime() {
		return goodsAddtime;
	}

	public void setGoodsAddtime(Date goodsAddtime) {
		this.goodsAddtime = goodsAddtime;
	}
	
	public String getGoodsAddtimeStr() {
		return goodsAddtimeStr;
	}

	public void setGoodsAddtimeStr(String goodsAddtimeStr) {
		this.goodsAddtimeStr = goodsAddtimeStr;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getGoodsStatus() {
		return goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public String getGoodsTotal() {
		return goodsTotal;
	}

	public void setGoodsTotal(String goodsTotal) {
		this.goodsTotal = goodsTotal;
	}

	public String getGoodsPaytype() {
		return goodsPaytype;
	}

	public void setGoodsPaytype(String goodsPaytype) {
		this.goodsPaytype = goodsPaytype;
	}

	public String getGoodsAddress() {
		return goodsAddress;
	}

	public void setGoodsAddress(String goodsAddress) {
		this.goodsAddress = goodsAddress;
	}

	public Customer getGoodsCustomer() {
		return goodsCustomer;
	}

	public void setGoodsCustomer(Customer goodsCustomer) {
		this.goodsCustomer = goodsCustomer;
	}

	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
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

	public List<Orderopt> getOrderopt() {
		return orderopt;
	}

	public void setOrderopt(List<Orderopt> orderopt) {
		this.orderopt = orderopt;
	}


	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", goodsName=" + goodsName + ", goodsPrice=" + goodsPrice + ", goodsInfo="
				+ goodsInfo + ", goodsAddtime=" + goodsAddtime + ", goodsAddtimeStr=" + goodsAddtimeStr + ", goodsType="
				+ goodsType + ", goodsStatus=" + goodsStatus + ", goodsTotal=" + goodsTotal + ", goodsPaytype="
				+ goodsPaytype + ", goodsAddress=" + goodsAddress + ", goodsCustomerId=" + goodsCustomerId + "]";
	}

}
