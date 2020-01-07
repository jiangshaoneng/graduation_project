package com.jiang.dao;

import com.jiang.beans.Admin;

public interface AdminMapper {
	
	/**查询出管理员信息通过管理员名字*/
	public Admin findAdminByName(String adminName);
	
}
