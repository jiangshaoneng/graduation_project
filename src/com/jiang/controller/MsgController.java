package com.jiang.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiang.beans.Customer;
import com.jiang.service.OrderService;
import com.jiang.servicepage.Page;

@Controller
public class MsgController {

	@Autowired
	OrderService orderService;
	
	/**用户消息管理*/
	@RequestMapping(value="/customerMsgManage")
	public String customerMsgManage(HttpServletRequest request,Map<String,Object> map) {
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String status = request.getParameter("status");
		if(status==null) {
			status = "待发货";
		}
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("sellCusId", customer.getcusId());
		map1.put("status", status);
		
		Page page = orderService.findListBySellCusId(map1, 1, 10);
		map.put("page", page);
		
		return "customerMsgManage";
	}
	
	@RequestMapping(value="/findMsgManage",method=RequestMethod.POST)
	public @ResponseBody Page findMsgManage(HttpServletRequest request){
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String status = request.getParameter("status");
		
		String pageNo = request.getParameter("pageNo");
		
		Integer currentPageNo;
		if(pageNo==null)
			currentPageNo = 1;
		else 
			currentPageNo = Integer.parseInt(pageNo);
		
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("sellCusId", customer.getcusId());
		if(status!=null&&!status.trim().equals("")) {
			map1.put("status", status);			
		}
		
		Page page = orderService.findListBySellCusId(map1, currentPageNo, 10);
		
		return page;
	}
	
	/**确认发货*/
	@RequestMapping(value="/deliverGoods",method=RequestMethod.POST)
	public @ResponseBody String deliverGoods(HttpServletRequest request){
		String orderoptId = request.getParameter("orderoptId");
		boolean flag = orderService.changeStatus(orderoptId, "待收货");
		if(flag)
			return "success";
		else 
			return "error";
	}
	
	/**关闭订单*/
	@RequestMapping(value="/overOrder",method=RequestMethod.POST)
	public @ResponseBody String overOrder(HttpServletRequest request){
		String orderoptId = request.getParameter("orderoptId");
		boolean flag = orderService.changeStatus(orderoptId, "已关闭");
		if(flag)
			return "success";
		else 
			return "error";
	}
	
	
}
