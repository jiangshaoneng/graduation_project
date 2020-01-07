package com.jiang.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.beans.Admin;
import com.jiang.beans.Customer;
import com.jiang.beans.Goods;
import com.jiang.beans.Notice;
import com.jiang.beans.Orderopt;
import com.jiang.dao.AdminMapper;
import com.jiang.dao.CustomerMapper;
import com.jiang.dao.GoodsMapper;
import com.jiang.dao.NoticeMapper;
import com.jiang.dao.OrderoptMapper;
import com.jiang.servicepage.Page;
import com.jiang.utils.NewIdUtil;

@Service
public class ManageService {
	
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private OrderoptMapper orderoptMapper;
	
	/**����Ա��¼*/
	public Admin manageLogin(String adminName) {
		 return adminMapper.findAdminByName(adminName);
	}
	
	/**����Ա��ѯ����*/
	public Page managefindNotice(Map<String,Object> map,Integer currentPageNo,Integer currentPageNum){
		Integer currentPageStart = currentPageNum*(currentPageNo-1);
		map.put("currentPageStart", currentPageStart);
		map.put("currentPageNum", currentPageNum);
		Page page = new Page();
		
		Integer totalCount = noticeMapper.managefindNoticeCount(map);
		List<Notice> list = noticeMapper.managefindNotice(map);
		
		int totalPage;
		if(totalCount<currentPageNum) {
			totalPage = 1;
		}else if(totalCount%currentPageNum==0){
			totalPage = totalCount/currentPageNum;
		}else {
			totalPage = (totalCount/currentPageNum)+1;
		}
		
		page.setCurrentPageNo(currentPageNo);
		page.setTotalPage(totalPage);
		page.setCurrentPageNum(currentPageNum);
		page.setList(list);
		page.setTotalCount(totalCount);
		
		return page;
	}
	
	/**����һ������*/
	public boolean addNotice(String newNoticeTitle,String newNoticeInfo) {
		Notice notice = new Notice();
		notice.setNoticeAdminId("100");
		notice.setNoticeId(NewIdUtil.getId());
		notice.setNoticeTitle(newNoticeTitle);
		notice.setNoticeInfo(newNoticeInfo);
		return noticeMapper.addNotice(notice);
	}
	
	/**ɾ��һ������*/
	public boolean delNotice(String noticeId) {
		return noticeMapper.delNotice(noticeId);
	}
	
	/**����Ա��ѯ�û�*/
	public Page findCustomerList(Map<String,Object> map,Integer currentPageNo,Integer currentPageNum) {
		Integer currentPageStart = currentPageNum*(currentPageNo-1);
		map.put("currentPageStart", currentPageStart);
		map.put("currentPageNum", currentPageNum);
		Page page = new Page();
		
		Integer totalCount = customerMapper.manageFindCustomerCount(map);
		List<Customer> list = customerMapper.manageFindCustomer(map);
		
		int totalPage;
		if(totalCount<currentPageNum) {
			totalPage = 1;
		}else if(totalCount%currentPageNum==0){
			totalPage = totalCount/currentPageNum;
		}else {
			totalPage = (totalCount/currentPageNum)+1;
		}
		
		page.setCurrentPageNo(currentPageNo);
		page.setTotalPage(totalPage);
		page.setCurrentPageNum(currentPageNum);
		page.setList(list);
		page.setTotalCount(totalCount);
		
		return page;
	}
	
	/**������û�*/
	public boolean freezeCustomer(String status,String cusId) {
		return customerMapper.freezeCustomer(status,cusId);
	}
	
	/**��ѯ��Ʒ�б�*/
	public Page findGoodsList(Map<String,Object> map,Integer currentPageNo,Integer currentPageNum){
		Integer currentPageStart = currentPageNum*(currentPageNo-1);
		map.put("currentPageStart", currentPageStart);
		map.put("currentPageNum", currentPageNum);
		Page page = new Page();
		
		Integer totalCount = goodsMapper.managefindGoodsCount(map);
		List<Goods> list = goodsMapper.managefindGoods(map);
		
		int totalPage;
		if(totalCount<currentPageNum) {
			totalPage = 1;
		}else if(totalCount%currentPageNum==0){
			totalPage = totalCount/currentPageNum;
		}else {
			totalPage = (totalCount/currentPageNum)+1;
		}
		
		page.setCurrentPageNo(currentPageNo);
		page.setTotalPage(totalPage);
		page.setCurrentPageNum(currentPageNum);
		page.setList(list);
		page.setTotalCount(totalCount);
		
		return page;
	}
	
	/**�¼���Ʒ*/
	public boolean freezeGoods(String status,String goodsId) {
		return goodsMapper.manageUpdateStatus(status, goodsId);
	}
	
	/**��ѯ�������б�*/
	public Page findOrderoptList(Map<String,Object> map,Integer currentPageNo,Integer currentPageNum) {
		Integer currentPageStart = currentPageNum*(currentPageNo-1);
		map.put("currentPageStart", currentPageStart);
		map.put("currentPageNum", currentPageNum);
		Page page = new Page();
		
		Integer totalCount = orderoptMapper.manageFindOrderoptCount(map);
		List<Orderopt> list = orderoptMapper.manageFindOrderopt(map);
		
		int totalPage;
		if(totalCount<currentPageNum) {
			totalPage = 1;
		}else if(totalCount%currentPageNum==0){
			totalPage = totalCount/currentPageNum;
		}else {
			totalPage = (totalCount/currentPageNum)+1;
		}
		
		page.setCurrentPageNo(currentPageNo);
		page.setTotalPage(totalPage);
		page.setCurrentPageNum(currentPageNum);
		page.setList(list);
		page.setTotalCount(totalCount);
		
		return page;
	}
	
}
