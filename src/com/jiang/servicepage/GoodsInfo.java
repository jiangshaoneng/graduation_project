package com.jiang.servicepage;

import org.springframework.stereotype.Repository;

import com.jiang.beans.Collection;
import com.jiang.beans.Goods;

@Repository
public class GoodsInfo {
	
	/**��Ʒ�Ļ�����Ϣ*/
	private Goods goods;
	
	/**��Ʒ�Ƿ񱻸��û��ղ�*/
	private Collection collection ;
	
	/**��Ʒ��������*/
	private Integer commentNum;

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
	public Collection getCollection() {
		return collection;
	}

	public void setCollection(Collection collection) {
		this.collection = collection;
	}

	public Integer getcommentNum() {
		return commentNum;
	}

	public void setcommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	@Override
	public String toString() {
		return "GoodsInfo [goods=" + goods + ", collection=" + collection + ", commentNum=" + commentNum + "]";
	}

}
