package com.jiang.beans;

import java.io.Serializable;

/**����� */
public class Notice implements Serializable{
	private String noticeId;//������
	private String noticeTitle;//�������
	private String noticeInfo;//��������
	private String noticeType;//��������
	private String noticeAddress;//��������
	private String noticeAddtime;//����ʱ��
	private String noticeAdminId;//����Ա���
	
	/**�����Ĺ���Ա*/
	private Admin noticeAdmin;
	
	public Notice() {
		// TODO Auto-generated constructor stub
	}

	public Notice(String noticeId, String noticeTitle, String noticeInfo, String noticeType, String noticeAddress,
			String noticeAddtime, String noticeAdminId) {
		super();
		this.noticeId = noticeId;
		this.noticeTitle = noticeTitle;
		this.noticeInfo = noticeInfo;
		this.noticeType = noticeType;
		this.noticeAddress = noticeAddress;
		this.noticeAddtime = noticeAddtime;
		this.noticeAdminId = noticeAdminId;
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeInfo() {
		return noticeInfo;
	}

	public void setNoticeInfo(String noticeInfo) {
		this.noticeInfo = noticeInfo;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getNoticeAddress() {
		return noticeAddress;
	}

	public void setNoticeAddress(String noticeAddress) {
		this.noticeAddress = noticeAddress;
	}

	public String getNoticeAddtime() {
		return noticeAddtime;
	}

	public void setNoticeAddtime(String noticeAddtime) {
		this.noticeAddtime = noticeAddtime;
	}

	public String getNoticeAdminId() {
		return noticeAdminId;
	}

	public void setNoticeAdminId(String noticeAdminId) {
		this.noticeAdminId = noticeAdminId;
	}

	public Admin getNoticeAdmin() {
		return noticeAdmin;
	}

	public void setNoticeAdmin(Admin noticeAdmin) {
		this.noticeAdmin = noticeAdmin;
	}
	
}