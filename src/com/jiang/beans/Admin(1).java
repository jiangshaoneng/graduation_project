package com.jiang.beans;

import java.io.Serializable;
import java.util.List;

/**管理员表 */
public class Admin implements Serializable{
	private String adminId;//管理员编号
	private String adminName;//管理员名称
	private String adminPassword;//管理员密码
	private String adminType;//管理员类型
	
	private List<Notice> notice;
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public Admin(String adminId, String adminName, String adminPassword, String adminType) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
		this.adminType = adminType;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminType() {
		return adminType;
	}

	public void setAdminType(String adminType) {
		this.adminType = adminType;
	}

	public List<Notice> getNotice() {
		return notice;
	}

	public void setNotice(List<Notice> notice) {
		this.notice = notice;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminPassword=" + adminPassword
				+ ", adminType=" + adminType + ", notice=" + notice + "]";
	}

}
