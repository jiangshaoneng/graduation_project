package com.jiang.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jiang.beans.Customer;
import com.jiang.service.GoodsService;
import com.jiang.service.NoticeService;
import com.jiang.servicepage.Page;

@Controller
public class ShowIndexController {

	@Autowired
	NoticeService noticeService;
	@Autowired
	GoodsService goodsSerice;
	
	@RequestMapping("/showIndex")
	public String showIndex(Map<String,Object> map,HttpServletRequest request) {
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		//显示首页的数据

		//1,公告栏信息
		Page noticePage = noticeService.showNotice(1,4);
		map.put("notices", noticePage);
		
		//2,猜你喜欢的商品
		Page guessGoodsInfo = goodsSerice.guessYouLike(customer);
		map.put("guessGoodsInfo", guessGoodsInfo);
		
		//3,六中分类商品信息
		Page booksGoodsInfo = goodsSerice.showGoodsInfo("书籍",1,4);
		map.put("booksGoodsInfo", booksGoodsInfo);

		Page eleProductGoodsInfo = goodsSerice.showGoodsInfo("电子设备",1,4);
		map.put("eleProductGoodsInfo", eleProductGoodsInfo);
		
		Page clothesGoodsInfo = goodsSerice.showGoodsInfo("衣物",1,4);
		map.put("clothesGoodsInfo", clothesGoodsInfo);
		
		Page dailyGoodsInfo = goodsSerice.showGoodsInfo("日用品",1,4);
		map.put("dailyGoodsInfo", dailyGoodsInfo);
		
		Page manGoodsInfo = goodsSerice.showGoodsInfo("男生专区",1,4);
		map.put("manGoodsInfo", manGoodsInfo);
		
		Page womanGoodsInfo = goodsSerice.showGoodsInfo("女生专区",1,4);
		map.put("womanGoodsInfo", womanGoodsInfo);
		
		return "index";
	}
	
}
