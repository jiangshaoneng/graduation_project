package com.jiang.dao;

import java.util.List;
import java.util.Map;

import com.jiang.beans.Collection;

public interface CollectionMapper {

	/**��ѯ�����û��ղص�������Ʒ��Ϣ*/
	public List<Collection> findCollectionByCusId(Map<String,Object> map);
	
	/**��ѯ���û����ղ���Ʒ������*/
	public Integer countCollectionByCusId(String cusId);
	
	/**�����û��ı�ź���Ʒ�ı�ţ���ѯ���Ƿ��ղ��˴���Ʒ*/
	public Collection findByCusIdAndGoodsId(String cusId,String goodsId);
	
	/**���һ���ղع�ϵ*/
	public boolean addCollection(Map<String,Object> map);	
	
	/**ȡ��һ���ղع�ϵ*/
	public boolean removeCollection(String cusId,String goodsId);
	
}
