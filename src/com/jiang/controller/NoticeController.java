package com.jiang.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiang.service.NoticeService;
import com.jiang.servicepage.Page;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService noticeService;
	
	/**加载公告页面*/
	@RequestMapping(value="nofilter_moreNotice",method=RequestMethod.GET)
	public String moreNotice(HttpServletRequest request) {
		return "moreNotice";
	}
	
	/**公告分页*/
	@RequestMapping(value="nofilter_chooseMoreNotice",method=RequestMethod.GET)
	public @ResponseBody Page chooseMoreNotice(HttpServletRequest request,
			@RequestParam(value="currentPageNo",required=false,defaultValue="1") Integer currentPageNo) {
		return noticeService.showNotice(currentPageNo, 10);
	}
	
}
