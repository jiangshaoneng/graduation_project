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
		//��ʾ��ҳ������

		//1,��������Ϣ
		Page noticePage = noticeService.showNotice(1,4);
		map.put("notices", noticePage);
		
		//2,����ϲ������Ʒ
		Page guessGoodsInfo = goodsSerice.guessYouLike(customer);
		map.put("guessGoodsInfo", guessGoodsInfo);
		
		//3,���з�����Ʒ��Ϣ
		Page booksGoodsInfo = goodsSerice.showGoodsInfo("�鼮",1,4);
		map.put("booksGoodsInfo", booksGoodsInfo);

		Page eleProductGoodsInfo = goodsSerice.showGoodsInfo("�����豸",1,4);
		map.put("eleProductGoodsInfo", eleProductGoodsInfo);
		
		Page clothesGoodsInfo = goodsSerice.showGoodsInfo("����",1,4);
		map.put("clothesGoodsInfo", clothesGoodsInfo);
		
		Page dailyGoodsInfo = goodsSerice.showGoodsInfo("����Ʒ",1,4);
		map.put("dailyGoodsInfo", dailyGoodsInfo);
		
		Page manGoodsInfo = goodsSerice.showGoodsInfo("����ר��",1,4);
		map.put("manGoodsInfo", manGoodsInfo);
		
		Page womanGoodsInfo = goodsSerice.showGoodsInfo("Ů��ר��",1,4);
		map.put("womanGoodsInfo", womanGoodsInfo);
		
		return "index";
	}
	
}
