package com.jiang.dao;

import com.jiang.beans.Admin;

public interface AdminMapper {
	
	/**��ѯ������Ա��Ϣͨ������Ա����*/
	public Admin findAdminByName(String adminName);
	
}
