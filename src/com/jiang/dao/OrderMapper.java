package com.jiang.dao;

import com.jiang.beans.Order;

public interface OrderMapper {
	
	/**创建一条订单*/
	public boolean insterOrder(Order order);
	
	/**查询出一条订单*/
	public Order findById(String OrderId);
	
	/**更新一条订单*/
	public boolean updateOrder(Order order);
}
