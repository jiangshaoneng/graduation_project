package com.jiang.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jiang.beans.Address;
import com.jiang.beans.Collection;
import com.jiang.beans.Customer;
import com.jiang.beans.Goods;
import com.jiang.service.CollectionService;
import com.jiang.service.GoodsService;
import com.jiang.servicepage.GoodsInfo;
import com.jiang.servicepage.Page;
import com.jiang.utils.TimeUtil;


@Controller
public class ShowGoodsController {
	
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private CollectionService collectionService;
	
	/**查看商品详细信息的请求*/
	@RequestMapping(value="/showGoodsDetail/{goodsId}",method=RequestMethod.GET)
	public String showGoodsDetail(HttpServletRequest request,@PathVariable String goodsId,Map<String,Object> map) {
		
		//获去当前的用户
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		//查询出此件商品的所有信息
		String cusId = customer==null?null:customer.getcusId();
		Goods goods = goodsService.showGoodsDetail(goodsId,cusId);
		if(customer!=null) {//查询收藏关系
			Collection collection = collectionService.findCollection(customer.getcusId(), goodsId);
			map.put("collection", collection);
		}
		
		//格式化添加时间
		Date addTime = goods.getGoodsAddtime();
		String fromatTime = TimeUtil.fromatTime(addTime);//将时间显示成xx小时前
		Page commentPage= goodsService.showCommet(goodsId, 1, 5);
		
		map.put("commentPage", commentPage);
		map.put("fromatTime", fromatTime);
		map.put("goods", goods);
		
		return "goodsDetail";
	}
	
	/**异步显示评论分页请求*/
	@RequestMapping(value="/nofilter_showCommet",method=RequestMethod.GET)
	public @ResponseBody Page showCommet(HttpServletRequest request) {
		
		//获取到商品的编号以及当前的页码
		String goodsId  = (String)request.getParameter("goodsId");
		String currentPageNo_str  = (String)request.getParameter("currentPageNo");
		
		if(currentPageNo_str!=null) {
			Integer currentPageNo = Integer.parseInt(currentPageNo_str);
			//分页查询出商品的评论信息
			Page commentPage= goodsService.showCommet(goodsId, currentPageNo, 5);
			//System.out.println(commentPage);
			return commentPage;
		}else {
			return null;
		}
	}
	
	/**异步添加评论请求*/
	@RequestMapping(value="/addComment",method=RequestMethod.POST)
	public @ResponseBody String addComment(HttpServletRequest request) {
		//获取前台传来的数据
		String cusId = (String)request.getParameter("cusId");
		String goodsId = (String)request.getParameter("goodsId");
		String addCommentInfo = (String)request.getParameter("addCommentInfo");
		
		boolean addComment = goodsService.addComment(cusId, goodsId, addCommentInfo);
		
		if(addComment) {
			return "success";			
		}else {
			return "error";
		}
	}
	
	/**删除评论*/
	@RequestMapping(value="/delComment",method=RequestMethod.GET)
	public @ResponseBody String delComment(HttpServletRequest request) {
		String commentId = (String)request.getParameter("commentId");
		//System.out.println(commentId);
		boolean delComment = goodsService.delComment(commentId);
		//System.out.println(delComment);
		if(delComment) {
			return "success";
		}else {
			return "error";
		}
	}
	
	/**按条件查询商品的请求*/
	@RequestMapping(value="/nofilter_customerSearch",method=RequestMethod.POST)
	public String customerSearch(HttpServletRequest request,Map<String,Object> map) {
		//获得查询条件,封装在map中
		String goodsType = request.getParameter("goodsType");
		String payType = request.getParameter("payType");
		String priceOrderBy = request.getParameter("priceOrderBy");
		String lowPrice = request.getParameter("lowPrice");
		String topPrice = request.getParameter("topPrice");
		String goodsAddress = request.getParameter("goodsAddress");
		String goodsName = request.getParameter("goodsName").trim();
		
		//将查询条件放到request中
		String myCondition = goodsType+"||"+payType+"||"+priceOrderBy+"||"+lowPrice+"||"+topPrice+"||"+goodsAddress+"||"+goodsName;
		map.put("myCondition", myCondition);
		
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String cusId = null;
		if(customer!=null) {
			cusId = customer.getcusId();
		}
		
		Page searchGoodsPage = goodsService.customerSearch(goodsType, payType, priceOrderBy, lowPrice, topPrice, goodsName, goodsAddress, 1, 5,cusId);
		map.put("searchGoodsPage", searchGoodsPage);
		return "customerSearch";
	}
	
	/**显示更多商品*/
	@RequestMapping(value="/nofilter_moreGoods",method= {RequestMethod.GET,RequestMethod.POST})
	public String moreGoods(HttpServletRequest request,Map<String,Object> map) {
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String goodsType = request.getParameter("goodsType");
		String payType = request.getParameter("payType");
		String priceOrderBy = request.getParameter("priceOrderBy");
		String lowPrice = request.getParameter("lowPrice");
		String topPrice = request.getParameter("topPrice");
		String goodsAddress = request.getParameter("goodsAddress");
		String goodsName = request.getParameter("goodsName");
		
		String cusId = null;
		if(customer!=null) {
			cusId = customer.getcusId();
		}
		Page moreGoodsPage = goodsService.customerSearch(goodsType, payType, priceOrderBy, lowPrice, topPrice, goodsName, goodsAddress, 1, 10,cusId);
		
		map.put("moreGoodsPage", moreGoodsPage);
		
		map.put("goodsType", goodsType);
		map.put("payType", payType);
		map.put("priceOrderBy", priceOrderBy);
		map.put("lowPrice", lowPrice);
		map.put("topPrice", topPrice);
		map.put("goodsAddress", goodsAddress);
		map.put("goodsName", goodsName);
		
		return "moreGoods";
	}
	
	/**添加收藏*/
	@RequestMapping(value="/addCollection",method=RequestMethod.POST)
	public @ResponseBody String addCollection(HttpServletRequest request) {
		//获取到商品的编号,顾客的编号
		String cusId = (String)request.getParameter("cusId");
		String goodsId = (String)request.getParameter("goodsId");
		//添加收藏
		boolean flag= goodsService.addCollection(cusId,goodsId);
		if(flag) {
			return cusId+","+goodsId;
		}else {
			return "error";
		}
	}
	
	/**取消收藏*/
	@RequestMapping(value="/removeCollection",method=RequestMethod.POST)
	public @ResponseBody String removeCollection(HttpServletRequest request) {
		//获取到商品的编号,顾客的编号
		String cusId = (String)request.getParameter("cusId");
		String goodsId = (String)request.getParameter("goodsId");
		//取消收藏
		boolean flag= goodsService.removeCollection(cusId, goodsId);
		if(flag) {
			return cusId+","+goodsId;
		}else {
			return "error";
		}
	}
	
	/**showNextGoods滑到底显示下一页信息*/
	@RequestMapping(value="/nofilter_showNextGoods",method=RequestMethod.GET)
	public @ResponseBody Page showNextGoods(HttpServletRequest request) {

		String goodsType = request.getParameter("goodsType");
		String payType = request.getParameter("payType");
		String priceOrderBy = request.getParameter("priceOrderBy");
		String lowPrice = request.getParameter("lowPrice");
		String topPrice = request.getParameter("topPrice");
		String goodsAddress = request.getParameter("goodsAddress");
		String goodsName = request.getParameter("goodsName");
		String currentPageNo = request.getParameter("currentPageNo");
		
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String cusId = null;
		if(customer!=null) {
			cusId = customer.getcusId();
		}
		
		Page searchGoodsPage = goodsService.customerSearch(goodsType, payType, priceOrderBy, lowPrice, topPrice, goodsName, goodsAddress, Integer.parseInt(currentPageNo), 10,cusId);
		return searchGoodsPage;
	}
	
}




