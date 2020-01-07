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
		
		//����ÿҳ������
		page.setCurrentPageNum(currentPageNum);
		//���õ�ǰҳ
		page.setCurrentPageNo(currentPageNo);
		
		//ҳ������
		int currentPageStart = currentPageNum*(currentPageNo-1);
		List<Notice> noticeList = noticeMapper.findNoticeByPage(currentPageStart, currentPageNum);
		page.setList(noticeList);
		
		//����������
		int totalCount = noticeMapper.findNoticeCounts();
		page.setTotalCount(totalCount);
		
		//������ҳ��
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
