package com.jiang.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.jiang.beans.Admin;
import com.jiang.beans.Customer;
import com.jiang.service.ManageService;
import com.jiang.service.NoticeService;
import com.jiang.servicepage.Page;

@SessionAttributes("admin")
@Controller
public class ManageController {

	@Autowired
	private ManageService manageService;
	
	/**����Ա��¼*/
	@RequestMapping(value="/manage_login",method=RequestMethod.POST)
	public String manageLogin(String adminName,String adminPassword,ModelMap map) {
		Admin admin = manageService.manageLogin(adminName);
		if(admin==null) {//������
			map.addAttribute("errorMsg", "�û�������!");
			return "manageLogin";
		}else {
			if(admin.getAdminPassword().equals(adminPassword)) {//��¼�ɹ�
				map.addAttribute("admin", admin);
				return "redirect:/pages/manage/manageNotice.html";				
			}else {//�������
				map.addAttribute("errorMsg", "�������!");
				return "manageLogin";
			}
		}
	}
	
	/**�û��˳�����*/
	@RequestMapping(value="/manage_logout",method=RequestMethod.GET)
	public String manageLogout(SessionStatus sessionStatus) {
		//��session����
		sessionStatus.setComplete();
		return "redirect:/pages/manageLogin.jsp";//������ҳ
	}
	
	@RequestMapping(value="/manage_getSession",method=RequestMethod.POST)
	public @ResponseBody Admin managegetSession(HttpServletRequest request) {
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin!=null) 
			return admin;			
		else
			return null;
	}
	
	/**�������*/
	@RequestMapping(value="/manage_findNotice")
	public @ResponseBody Page managefindNotice(HttpServletRequest request) {
		String pageNo = request.getParameter("pageNo");
		String startTime = request.getParameter("startTime");
		String endtTime = request.getParameter("endTime");
		String noticeInfo = request.getParameter("noticeInfo");
		
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("startTime", startTime);
		map1.put("endtTime", endtTime);
		if(noticeInfo!=null)
			map1.put("noticeInfo", "%"+noticeInfo.trim()+"%");
		
		Integer currentPageNo;
		if(pageNo==null)
			currentPageNo = 1;
		else 
			currentPageNo = Integer.parseInt(pageNo);
		
		//��ѯ������
		Page page = manageService.managefindNotice(map1,currentPageNo,5);
		return page;
	}
	
	/**��������*/
	@RequestMapping(value="/manage_addNotice")
	public @ResponseBody String manageAddNotice(HttpServletRequest request) {
		String newNoticeTitle = request.getParameter("newNoticeTitle");
		String newNoticeInfo = request.getParameter("newNoticeInfo");
		boolean addFlag = manageService.addNotice(newNoticeTitle,newNoticeInfo);
		if(addFlag) {
			return "success";			
		}else {
			return "error";
		}
	}
	
	/**ɾ������*/
	@RequestMapping(value="/manage_delNotice")
	public @ResponseBody String manageDelNotice(HttpServletRequest request) {
		String noticeId = request.getParameter("noticeId");
		boolean delFlag = manageService.delNotice(noticeId);
		
		if(delFlag) {
			return "success";			
		}else {
			return "error";
		}
	}
	
	/**��ѯ�û��б�*/
	@RequestMapping(value="/manage_findCustomer")
	public @ResponseBody Page manageFindCustomer(HttpServletRequest request) {
		String pageNo = request.getParameter("pageNo");
		String cusId = request.getParameter("cusId");
		String cusRealname = request.getParameter("cusRealname");
		String cusType = request.getParameter("cusType");
		String cusStatus = request.getParameter("cusStatus");
		String startTime = request.getParameter("startTime");
		String endtTime = request.getParameter("endTime");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(cusId!=null&&!"".equals(cusId.trim()))
			map.put("cusId", "%"+cusId+"%");
		if(cusRealname!=null&&!"".equals(cusRealname.trim()))
			map.put("cusRealname", "%"+cusRealname+"%");
		if(cusType!=null&&!"".equals(cusType))
			map.put("cusType", cusType);
		if(cusStatus!=null&&!"".equals(cusStatus))
			map.put("cusStatus", cusStatus);
		if(startTime!=null&&!"".equals(startTime))
			map.put("startTime", startTime);
		if(endtTime!=null&&!"".equals(endtTime))
			map.put("endtTime", endtTime);
		
		Integer currentPageNo;
		if(pageNo==null)
			currentPageNo = 1;
		else 
			currentPageNo = Integer.parseInt(pageNo);
		
		//��ѯ���û�
		Page page = manageService.findCustomerList(map, currentPageNo, 10);
		return page;
	}
	
	/**������û�*/
	@RequestMapping(value="/manage_freezeCustomer")
	public @ResponseBody String manageFreezeCustomer(HttpServletRequest request){
		String cusId = request.getParameter("cusId");
		String status = request.getParameter("status");
		boolean flag = manageService.freezeCustomer(status, cusId);
		if(flag)
			return "success";
		else
			return "error";
	}
	
	/**��ѯ��Ʒ�б�*/
	@RequestMapping(value="/manage_findGoods")
	public @ResponseBody Page manageFindGoods(HttpServletRequest request) {
		String pageNo = request.getParameter("pageNo");
		String goodsId = request.getParameter("goodsId");
		String goodsName = request.getParameter("goodsName");
		String goodsCustomerId = request.getParameter("goodsCustomerId");
		String goodsType = request.getParameter("goodsType");
		String goodsStatus = request.getParameter("goodsStatus");
		String startTime = request.getParameter("startTime");
		String endtTime = request.getParameter("endTime");
		
		Map<String,Object> map = new HashMap<String,Object>();
		if(goodsId!=null&&!"".equals(goodsId.trim()))
			map.put("goodsId", "%"+goodsId+"%");
		if(goodsName!=null&&!"".equals(goodsName.trim()))
			map.put("goodsName", "%"+goodsName+"%");
		if(goodsCustomerId!=null&&!"".equals(goodsCustomerId.trim()))
			map.put("sellCusId", goodsCustomerId);
		if(goodsType!=null&&!"".equals(goodsType))
			map.put("goodsType", goodsType);
		if(goodsStatus!=null&&!"".equals(goodsStatus))
			map.put("goodsStatus", goodsStatus);
		if(startTime!=null&&!"".equals(startTime))
			map.put("startTime", startTime);
		if(endtTime!=null&&!"".equals(endtTime))
			map.put("endtTime", endtTime);
		
		Integer currentPageNo;
		if(pageNo==null)
			currentPageNo = 1;
		else 
			currentPageNo = Integer.parseInt(pageNo);
		
		//��ѯ��Ʒ
		Page page = manageService.findGoodsList(map, currentPageNo, 10);
		return page;
	}
	
	/**������Ʒ*/
	@RequestMapping(value="/manage_freezeGoods")
	public @ResponseBody String manageFreezeGoods(HttpServletRequest request) {
		String status = request.getParameter("status");
		String goodsId = request.getParameter("goodsId");
		boolean flag = manageService.freezeGoods(status, goodsId);
		if(flag)
			return "success";
		else
			return "error";
	}
	
	/**������ѯ*/
	@RequestMapping(value="/manage_findOrderopt")
	public @ResponseBody Page manageFindOrderopt(HttpServletRequest request) {
		String pageNo = request.getParameter("pageNo");
		String orderoptId = request.getParameter("orderoptId");
		String orderoptStatus = request.getParameter("orderoptStatus");
		String startTime = request.getParameter("startTime");
		String endtTime = request.getParameter("endTime");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		if(orderoptId!=null&&!"".equals(orderoptId.trim()))
			map.put("orderoptId", "%"+orderoptId+"%");
		if(orderoptStatus!=null&&!"".equals(orderoptStatus))
			map.put("orderoptStatus", orderoptStatus);
		if(startTime!=null&&!"".equals(startTime))
			map.put("startTime", startTime);
		if(endtTime!=null&&!"".equals(endtTime))
			map.put("endtTime", endtTime);
		
		Integer currentPageNo;
		if(pageNo==null)
			currentPageNo = 1;
		else 
			currentPageNo = Integer.parseInt(pageNo);
		
		Page page = manageService.findOrderoptList(map,currentPageNo,10);
		return page;
	}
	
	
}
