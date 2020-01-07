package com.jiang.dao;

import java.util.List;
import java.util.Map;

import com.jiang.beans.Notice;

public interface NoticeMapper {
	
	/**��ѯ���������*/
	public Integer findNoticeCounts();
	
	/**��ҳ��ѯ������Ϣ:��ҳ�Ŀ�ʼ��,����*/
	public List<Notice> findNoticeByPage(Integer currentPageStart,Integer currentPageNum);
	
	/**����Ա��ѯ��������*/
	public Integer managefindNoticeCount(Map<String,Object> map);
	
	/**����Ա��ѯ����*/
	public List<Notice> managefindNotice(Map<String,Object> map);
	
	/**����Ա����һ������*/
	public boolean addNotice(Notice notice);
	
	/**ɾ��һ������*/
	public boolean delNotice(String noticeId);
}
