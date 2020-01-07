package com.jiang.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.jiang.beans.Orderopt;

public interface OrderoptMapper {
	
	/**��������������*/
	public boolean batchInsert(@Param("list") List<Orderopt> orderoptList);
	
	/**��ѯ��ĳһ�����µ����ж�����*/
	public List<Orderopt> findListByOrderId(String orderId);
	
	/**��ѯĳ��������*/
	public Orderopt findPrimaryById(String orderoptId);
	
	/**����һ��������*/
	public boolean update(Orderopt orderopt);
	
	/**��������*/
	public boolean batchUpdata(@Param("orderoptList") List<Orderopt> orderoptList);
	
	/**��ѯĳһ�û�������*/
	public List<Orderopt> findList(Map<String,Object> map);
	
	/**��ѯĳһ�û�����������*/
	public int findCount(Map<String,Object> map);
	
	/**��ѯ���۷������д������Ķ�������Ϣ*/
	public List<Orderopt> findListBySellCusId(Map<String,Object> map);
	
	/**��ѯ���۷������д������Ķ�������Ϣ����*/
	public int findListBySellCusIdCount(Map<String,Object> map);
		
	/**����Ա��ѯ����������*/
	public int manageFindOrderoptCount(Map<String,Object> map);
	
	/**����Ա��ѯ������*/
	public List<Orderopt> manageFindOrderopt(Map<String,Object> map);
}
