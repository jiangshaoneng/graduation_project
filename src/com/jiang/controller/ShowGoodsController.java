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
	
	/**�鿴��Ʒ��ϸ��Ϣ������*/
	@RequestMapping(value="/showGoodsDetail/{goodsId}",method=RequestMethod.GET)
	public String showGoodsDetail(HttpServletRequest request,@PathVariable String goodsId,Map<String,Object> map) {
		
		//��ȥ��ǰ���û�
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		//��ѯ���˼���Ʒ��������Ϣ
		String cusId = customer==null?null:customer.getcusId();
		Goods goods = goodsService.showGoodsDetail(goodsId,cusId);
		if(customer!=null) {//��ѯ�ղع�ϵ
			Collection collection = collectionService.findCollection(customer.getcusId(), goodsId);
			map.put("collection", collection);
		}
		
		//��ʽ�����ʱ��
		Date addTime = goods.getGoodsAddtime();
		String fromatTime = TimeUtil.fromatTime(addTime);//��ʱ����ʾ��xxСʱǰ
		Page commentPage= goodsService.showCommet(goodsId, 1, 5);
		
		map.put("commentPage", commentPage);
		map.put("fromatTime", fromatTime);
		map.put("goods", goods);
		
		return "goodsDetail";
	}
	
	/**�첽��ʾ���۷�ҳ����*/
	@RequestMapping(value="/nofilter_showCommet",method=RequestMethod.GET)
	public @ResponseBody Page showCommet(HttpServletRequest request) {
		
		//��ȡ����Ʒ�ı���Լ���ǰ��ҳ��
		String goodsId  = (String)request.getParameter("goodsId");
		String currentPageNo_str  = (String)request.getParameter("currentPageNo");
		
		if(currentPageNo_str!=null) {
			Integer currentPageNo = Integer.parseInt(currentPageNo_str);
			//��ҳ��ѯ����Ʒ��������Ϣ
			Page commentPage= goodsService.showCommet(goodsId, currentPageNo, 5);
			//System.out.println(commentPage);
			return commentPage;
		}else {
			return null;
		}
	}
	
	/**�첽�����������*/
	@RequestMapping(value="/addComment",method=RequestMethod.POST)
	public @ResponseBody String addComment(HttpServletRequest request) {
		//��ȡǰ̨����������
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
	
	/**ɾ������*/
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
	
	/**��������ѯ��Ʒ������*/
	@RequestMapping(value="/nofilter_customerSearch",method=RequestMethod.POST)
	public String customerSearch(HttpServletRequest request,Map<String,Object> map) {
		//��ò�ѯ����,��װ��map��
		String goodsType = request.getParameter("goodsType");
		String payType = request.getParameter("payType");
		String priceOrderBy = request.getParameter("priceOrderBy");
		String lowPrice = request.getParameter("lowPrice");
		String topPrice = request.getParameter("topPrice");
		String goodsAddress = request.getParameter("goodsAddress");
		String goodsName = request.getParameter("goodsName").trim();
		
		//����ѯ�����ŵ�request��
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
	
	/**��ʾ������Ʒ*/
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
	
	/**����ղ�*/
	@RequestMapping(value="/addCollection",method=RequestMethod.POST)
	public @ResponseBody String addCollection(HttpServletRequest request) {
		//��ȡ����Ʒ�ı��,�˿͵ı��
		String cusId = (String)request.getParameter("cusId");
		String goodsId = (String)request.getParameter("goodsId");
		//����ղ�
		boolean flag= goodsService.addCollection(cusId,goodsId);
		if(flag) {
			return cusId+","+goodsId;
		}else {
			return "error";
		}
	}
	
	/**ȡ���ղ�*/
	@RequestMapping(value="/removeCollection",method=RequestMethod.POST)
	public @ResponseBody String removeCollection(HttpServletRequest request) {
		//��ȡ����Ʒ�ı��,�˿͵ı��
		String cusId = (String)request.getParameter("cusId");
		String goodsId = (String)request.getParameter("goodsId");
		//ȡ���ղ�
		boolean flag= goodsService.removeCollection(cusId, goodsId);
		if(flag) {
			return cusId+","+goodsId;
		}else {
			return "error";
		}
	}
	
	/**showNextGoods��������ʾ��һҳ��Ϣ*/
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




