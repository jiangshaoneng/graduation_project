package com.jiang.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.jiang.beans.Orderopt;

public interface OrderoptMapper {
	
	/**批量创建订单项*/
	public boolean batchInsert(@Param("list") List<Orderopt> orderoptList);
	
	/**查询出某一订单下的所有订单项*/
	public List<Orderopt> findListByOrderId(String orderId);
	
	/**查询某条订单项*/
	public Orderopt findPrimaryById(String orderoptId);
	
	/**更新一条订单项*/
	public boolean update(Orderopt orderopt);
	
	/**批量更新*/
	public boolean batchUpdata(@Param("orderoptList") List<Orderopt> orderoptList);
	
	/**查询某一用户订单项*/
	public List<Orderopt> findList(Map<String,Object> map);
	
	/**查询某一用户订单项数量*/
	public int findCount(Map<String,Object> map);
	
	/**查询出售方的所有待发货的订单项信息*/
	public List<Orderopt> findListBySellCusId(Map<String,Object> map);
	
	/**查询出售方的所有待发货的订单项信息数量*/
	public int findListBySellCusIdCount(Map<String,Object> map);
		
	/**管理员查询订单项数量*/
	public int manageFindOrderoptCount(Map<String,Object> map);
	
	/**管理员查询订单项*/
	public List<Orderopt> manageFindOrderopt(Map<String,Object> map);
}
