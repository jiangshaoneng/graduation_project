package com.jiang.beans;

import java.io.Serializable;

/** 商品图片表 */
public class Images implements Serializable{
	
	private String imgId;//图片编号(每件商品编号最小的为  主图)
	private String imgUrl;//图片的地址
	private String imgGoodsId;//图片所属商品的Id
	
	/**图片所属的商品*/
	private Goods imgGoods;
	
	///=========== 构造器 ================
	
	public Images() {
		// TODO Auto-generated constructor stub
	}
	
	public Images(String imgId, String imgUrl,String imgGoodsId) {
		super();
		this.imgId = imgId;
		this.imgUrl = imgUrl;
		this.imgGoodsId = imgGoodsId;
	}
	
	////========== get/set 方法 ==================
	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgGoodsId() {
		return imgGoodsId;
	}

	public void setImgGoodsId(String imgGoodsId) {
		this.imgGoodsId = imgGoodsId;
	}

	public Goods getImgGoods() {
		return imgGoods;
	}

	public void setImgGoods(Goods imgGoods) {
		this.imgGoods = imgGoods;
	}
	
}