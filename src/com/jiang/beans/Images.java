package com.jiang.beans;

import java.io.Serializable;

/** ��ƷͼƬ�� */
public class Images implements Serializable{
	
	private String imgId;//ͼƬ���(ÿ����Ʒ�����С��Ϊ  ��ͼ)
	private String imgUrl;//ͼƬ�ĵ�ַ
	private String imgGoodsId;//ͼƬ������Ʒ��Id
	
	/**ͼƬ��������Ʒ*/
	private Goods imgGoods;
	
	///=========== ������ ================
	
	public Images() {
		// TODO Auto-generated constructor stub
	}
	
	public Images(String imgId, String imgUrl,String imgGoodsId) {
		super();
		this.imgId = imgId;
		this.imgUrl = imgUrl;
		this.imgGoodsId = imgGoodsId;
	}
	
	////========== get/set ���� ==================
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