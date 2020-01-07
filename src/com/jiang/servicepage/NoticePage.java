package com.jiang.servicepage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jiang.beans.Notice;
import com.jiang.dao.NoticeMapper;
@Repository
public class NoticePage {

	@Autowired
	private Page page;
	@Autowired
	private NoticeMapper noticeMapper;
	
	public Page getPage(Integer currentPageNo,Integer currentPageNum) {
		
		//设置每页的数量
		page.setCurrentPageNum(currentPageNum);
		//设置当前页
		page.setCurrentPageNo(currentPageNo);
		
		//页面数据
		int currentPageStart = currentPageNum*(currentPageNo-1);
		List<Notice> noticeList = noticeMapper.findNoticeByPage(currentPageStart, currentPageNum);
		page.setList(noticeList);
		
		//设置总条数
		int totalCount = noticeMapper.findNoticeCounts();
		page.setTotalCount(totalCount);
		
		//设置总页数
		int totalPage;
		if(totalCount<currentPageNum) {
			totalPage = 1;
		}else if(totalCount%currentPageNum==0){
			totalPage = totalCount/currentPageNum;
		}else {
			totalPage = (totalCount/currentPageNum)+1;
		}
		page.setTotalPage(totalPage);
		
		return page;
	}
	
}
