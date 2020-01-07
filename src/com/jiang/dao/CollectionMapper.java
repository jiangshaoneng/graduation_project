package com.jiang.dao;

import java.util.List;
import java.util.Map;

import com.jiang.beans.Collection;

public interface CollectionMapper {

	/**查询出该用户收藏的所有商品信息*/
	public List<Collection> findCollectionByCusId(Map<String,Object> map);
	
	/**查询出用户所收藏商品的数量*/
	public Integer countCollectionByCusId(String cusId);
	
	/**根据用户的编号和商品的编号：查询出是否收藏了此商品*/
	public Collection findByCusIdAndGoodsId(String cusId,String goodsId);
	
	/**添加一对收藏关系*/
	public boolean addCollection(Map<String,Object> map);	
	
	/**取消一对收藏关系*/
	public boolean removeCollection(String cusId,String goodsId);
	
}
