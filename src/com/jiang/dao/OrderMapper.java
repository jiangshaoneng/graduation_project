package com.jiang.dao;

import com.jiang.beans.Order;

public interface OrderMapper {
	
	/**����һ������*/
	public boolean insterOrder(Order order);
	
	/**��ѯ��һ������*/
	public Order findById(String OrderId);
	
	/**����һ������*/
	public boolean updateOrder(Order order);
}
