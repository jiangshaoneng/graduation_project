package com.jiang.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiang.servicepage.NoticePage;
import com.jiang.servicepage.Page;

@Service
public class NoticeService {

	@Autowired
	private NoticePage noticePage;
	
	/**将公告信息显示在首页*/
	public Page showNotice(Integer currentPageNo,Integer currentPageNum){
		return noticePage.getPage(currentPageNo, currentPageNum);
	}
	
}
