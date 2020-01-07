package com.jiang.dao;

import java.util.List;
import java.util.Map;

import com.jiang.beans.Notice;

public interface NoticeMapper {
	
	/**查询公告的数量*/
	public Integer findNoticeCounts();
	
	/**分页查询公告信息:分页的开始处,数量*/
	public List<Notice> findNoticeByPage(Integer currentPageStart,Integer currentPageNum);
	
	/**管理员查询公告数量*/
	public Integer managefindNoticeCount(Map<String,Object> map);
	
	/**管理员查询公告*/
	public List<Notice> managefindNotice(Map<String,Object> map);
	
	/**管理员新增一条公告*/
	public boolean addNotice(Notice notice);
	
	/**删除一条公告*/
	public boolean delNotice(String noticeId);
}
