package com.jiang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jiang.beans.Address;
import com.jiang.beans.Collection;
import com.jiang.dao.AddressMapper;
import com.jiang.dao.CollectionMapper;
import com.jiang.servicepage.CollectionPage;
import com.jiang.servicepage.Page;

@Service
public class CollectionService {
	
	@Autowired
	private CollectionPage collectionPage;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private CollectionMapper collectionMapper;
	
	/**分页查寻出用户收藏的商品信息*/
	public Page findCustomerCollection(String cusId,Integer currentPageNo,Integer currentPageNum) {
		return collectionPage.getPage(cusId, currentPageNo,currentPageNum);
	}
	
	public List<Address> findCusAddress(String cusId) {
		return addressMapper.findAddressByCusId(cusId);
	}
	
	/**查询出某一顾客和某一商品的收藏关系*/
	public Collection findCollection(String cusId,String goodsId) {
		return collectionMapper.findByCusIdAndGoodsId(cusId, goodsId);
	}
}
