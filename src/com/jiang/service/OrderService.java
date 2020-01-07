package com.jiang.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiang.beans.Customer;
import com.jiang.beans.Goods;
import com.jiang.beans.Order;
import com.jiang.beans.Orderopt;
import com.jiang.dao.CustomerMapper;
import com.jiang.dao.GoodsMapper;
import com.jiang.dao.OrderMapper;
import com.jiang.dao.OrderoptMapper;
import com.jiang.servicepage.Page;
import com.jiang.utils.NewIdUtil;

import net.sf.json.JSONArray;

@Service
public class OrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderoptMapper orderoptMapper;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private CustomerMapper customerMapper;
	
	/**创建一条订单，以及所包含订单项*/
	public String addOrUpdataOrder(Map<String,String> map,JSONArray json) {
		String orderId = map.get("orderId");
		String cusId = map.get("cusId");
		if(orderId==null) {//新增
			//创建订单对象
			Order order = new Order();
			String newOrderId = NewIdUtil.getId();
			order.setOrderId(newOrderId);
			order.setOrderPaycustomerId(cusId);
			order.setOrderStatus("待付款");
			order.setOrderPaytype("线上支付");
			
			boolean insertOrderFlag = orderMapper.insterOrder(order);
			
			//创建订单项
			if(insertOrderFlag) {
				List<Orderopt> orderoptList = new ArrayList<Orderopt>();
				@SuppressWarnings("unchecked")
				List<Map<String,String>> list = (List<Map<String,String>>) json;
				Float orderTotalprice = 0f;
				for (Map<String, String> jsonInfo : list) {
					String goodsId = String.valueOf(jsonInfo.get("goodsId"));
					String payNum = String.valueOf(jsonInfo.get("payNum"));
					Goods goods = goodsMapper.findGoodsById(goodsId);
					Float orderoptPrice = Float.parseFloat(goods.getGoodsPrice())*Float.parseFloat(payNum);
					
					Orderopt orderopt = new Orderopt();
					orderopt.setOrderoptId(NewIdUtil.getId());
					orderopt.setOrderoptSellcustomerId(goods.getGoodsCustomer().getcusId());
					orderopt.setOrderoptGoodsId(goodsId);
					orderopt.setOrderoptOrderId(newOrderId);
					orderopt.setOrderoptNum(payNum);
					orderopt.setOrderoptPrice(orderoptPrice.toString());
					orderopt.setOrderoptStatus("待付款");
					orderopt.setOrderoptPaycustomerId(cusId);
					orderoptList.add(orderopt);
					orderTotalprice += orderoptPrice;
				}
				boolean batchInsert = orderoptMapper.batchInsert(orderoptList);
				order.setOrderTotalprice(orderTotalprice.toString());
				if(batchInsert) {
					orderMapper.updateOrder(order);
					return newOrderId;					
				} 
			}
			
		}else {//更新
			String status = map.get("status");
			//完善订单的信息
			Order order = orderMapper.findById(orderId);
			order.setOrderStatus(status);
			List<Orderopt> orderoptList = order.getOrderoptList();
			for (Orderopt orderopt : orderoptList) {
				orderopt.setOrderoptStatus("待发货");
			}
			boolean batchUpdata = orderoptMapper.batchUpdata(orderoptList);
			boolean updateOrder = orderMapper.updateOrder(order);
			if(updateOrder&&batchUpdata) {
				return orderId;
			}
		}
		
		return null;
	}
	
	/**查询订单详情*/
	public Order findOrderInfo(String orderId) {
		return orderMapper.findById(orderId);
	}
	
	/**在支付页面,添加送地址*/
	public boolean addAddressAndComment(String orderId,String addressInfo,JSONArray json) {
		//添加填写的留言信息
		@SuppressWarnings("unchecked")
		List<Map<String,String>> list = (List<Map<String,String>>) json;
		List<Orderopt> orderoptList = new ArrayList<Orderopt>();
		for (Map<String, String> jsonInfo : list) {
			//查询订单项
			Orderopt orderopt = orderoptMapper.findPrimaryById(jsonInfo.get("orderoptId"));
			orderopt.setOrderoptComment(jsonInfo.get("commentInfo"));
			orderoptList.add(orderopt);
		}
		orderoptMapper.batchUpdata(orderoptList);
		//在订单中添加地址
		Order order = orderMapper.findById(orderId);
		order.setOrderAddress(addressInfo);
		return orderMapper.updateOrder(order);
	}
	
	/**查询用户下的所有订单项*/
	public Page findList(Map<String,Object> map,Integer currentPageNo,Integer currentPageNum){
		Page page = new Page();
		
		int currentPageStart = currentPageNum*(currentPageNo-1);//mysql分页需要
		map.put("currentPageStart", currentPageStart);
		map.put("currentPageNum", currentPageNum);
		List<Orderopt> dataList = orderoptMapper.findList(map);
		int totalCount = orderoptMapper.findCount(map);
		
		int totalPage;
		if(totalCount<currentPageNum) {
			totalPage = 1;
		}else if(totalCount%currentPageNum==0){
			totalPage = totalCount/currentPageNum;
		}else {
			totalPage = (totalCount/currentPageNum)+1;
		}
		
		page.setCurrentPageNo(currentPageNo);
		page.setCurrentPageNum(currentPageNum);
		page.setTotalCount(totalCount);
		page.setTotalPage(totalPage);
		page.setList(dataList);
		
		return page;
	}
	
	/**查询卖家需要处理的订单项信息状态*/
	public Page findListBySellCusId(Map<String,Object> map,Integer currentPageNo,Integer currentPageNum){
		Page page = new Page();
		
		int currentPageStart = currentPageNum*(currentPageNo-1);//mysql分页需要
		map.put("currentPageStart", currentPageStart);
		map.put("currentPageNum", currentPageNum);
		List<Orderopt> dataList = orderoptMapper.findListBySellCusId(map);
		int totalCount = orderoptMapper.findListBySellCusIdCount(map);
		
		int totalPage;
		if(totalCount<currentPageNum) {
			totalPage = 1;
		}else if(totalCount%currentPageNum==0){
			totalPage = totalCount/currentPageNum;
		}else {
			totalPage = (totalCount/currentPageNum)+1;
		}
		
		page.setCurrentPageNo(currentPageNo);
		page.setCurrentPageNum(currentPageNum);
		page.setTotalCount(totalCount);
		page.setTotalPage(totalPage);
		page.setList(dataList);
		
		return page;
	}
	
	/**修改一条订单项的状态*/
	public boolean changeStatus(String orderoptId,String status) {
		Orderopt orderopt = orderoptMapper.findPrimaryById(orderoptId);
		if("待收货".equals(orderopt.getOrderoptStatus())) {
			orderopt.setOrderoptStatus(status);
			//卖家收款
			String money = orderopt.getOrderoptPrice();
			Customer customer = customerMapper.findCustomerById(orderopt.getOrderoptSellcustomerId());//获取商家信息
			customer.setcusBalance((Float.parseFloat(customer.getcusBalance())+Float.parseFloat(money))+"");
			customerMapper.updateCustomer(customer);
			return orderoptMapper.update(orderopt);
		}else if("待发货".equals(orderopt.getOrderoptStatus())&&"已关闭".equals(status)){
			orderopt.setOrderoptStatus(status);
			//买家收款
			String money = orderopt.getOrderoptPrice();
			Customer customer = customerMapper.findCustomerById(orderopt.getOrderoptPaycustomerId());
			customer.setcusBalance((Float.parseFloat(customer.getcusBalance())+Float.parseFloat(money))+"");
			customerMapper.updateCustomer(customer);
			return orderoptMapper.update(orderopt);	
		}else{
			orderopt.setOrderoptStatus(status);
			return orderoptMapper.update(orderopt);			
		}
	}
	
	/**查询订单项*/
	public Orderopt findOrderopt(String orderoptId) {
		return orderoptMapper.findPrimaryById(orderoptId);
	}
	
	/**判断时候能够进行交易*/
	public String pay(String payType,String orderId,Float price,String cusId) {
		Customer customer = customerMapper.findCustomerById(cusId);
		Boolean payFlag = false;
		if(Float.parseFloat(customer.getcusBalance())>=price) {//余额足够
			
			Order order = orderMapper.findById(orderId);
			List<Orderopt> orderoptList = order.getOrderoptList();
			for (Orderopt orderopt : orderoptList) {
				if("出售中".equals(orderopt.getOrderoptGoods().getGoodsStatus())&&
						Integer.parseInt(orderopt.getOrderoptNum())<=
						Integer.parseInt(orderopt.getOrderoptGoods().getGoodsTotal())) {//库存足够
					payFlag = true;
				}else {
					payFlag = false;
					return "GoodsNotFind";//商品不能交易
				}
			}
			
			if(payFlag) {//满足交易条件
				//1,更新订单记录
				order.setOrderStatus("已支付");
				for (Orderopt orderopt : orderoptList) {
					orderopt.setOrderoptStatus("待发货");
				}
				boolean batchUpdata = orderoptMapper.batchUpdata(orderoptList);
				boolean updateOrder = orderMapper.updateOrder(order);
				if(batchUpdata&&updateOrder) {
					//2,修改商品的数量
					List<Goods> goodsList = new ArrayList<Goods>();
					for (Orderopt orderopt : orderoptList) {
						Goods goods = orderopt.getOrderoptGoods();
						Integer total = Integer.parseInt(goods.getGoodsTotal()) - Integer.parseInt(orderopt.getOrderoptNum());
						goods.setGoodsTotal(total+"");
						if(total == 0) {
							goods.setGoodsStatus("已售出");
						}
						goodsList.add(goods);
					}
					boolean updateBatchFlag = goodsMapper.updateBatch(goodsList);
					
					//3,减少买的的余额
					boolean updateCustomerFlag = false;
					if("balancePay".equals(payType)) {
						String balance = (Float.parseFloat(customer.getcusBalance())-price)+"";
						customer.setcusBalance(balance);
						updateCustomerFlag = customerMapper.updateCustomer(customer);						
					}else {
						updateCustomerFlag = true;
					}
					
					if(updateCustomerFlag&&updateBatchFlag) {
						return "PaySuccess";					
					}else {
						return "PayError";
					}
				}
			}
			
		}else {
			return "NoBalance";//余额不足
		}
		return "PayError";
	}
	
	
}
