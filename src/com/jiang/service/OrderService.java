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
	
	/**����һ���������Լ�������������*/
	public String addOrUpdataOrder(Map<String,String> map,JSONArray json) {
		String orderId = map.get("orderId");
		String cusId = map.get("cusId");
		if(orderId==null) {//����
			//������������
			Order order = new Order();
			String newOrderId = NewIdUtil.getId();
			order.setOrderId(newOrderId);
			order.setOrderPaycustomerId(cusId);
			order.setOrderStatus("������");
			order.setOrderPaytype("����֧��");
			
			boolean insertOrderFlag = orderMapper.insterOrder(order);
			
			//����������
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
					orderopt.setOrderoptStatus("������");
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
			
		}else {//����
			String status = map.get("status");
			//���ƶ�������Ϣ
			Order order = orderMapper.findById(orderId);
			order.setOrderStatus(status);
			List<Orderopt> orderoptList = order.getOrderoptList();
			for (Orderopt orderopt : orderoptList) {
				orderopt.setOrderoptStatus("������");
			}
			boolean batchUpdata = orderoptMapper.batchUpdata(orderoptList);
			boolean updateOrder = orderMapper.updateOrder(order);
			if(updateOrder&&batchUpdata) {
				return orderId;
			}
		}
		
		return null;
	}
	
	/**��ѯ��������*/
	public Order findOrderInfo(String orderId) {
		return orderMapper.findById(orderId);
	}
	
	/**��֧��ҳ��,����͵�ַ*/
	public boolean addAddressAndComment(String orderId,String addressInfo,JSONArray json) {
		//�����д��������Ϣ
		@SuppressWarnings("unchecked")
		List<Map<String,String>> list = (List<Map<String,String>>) json;
		List<Orderopt> orderoptList = new ArrayList<Orderopt>();
		for (Map<String, String> jsonInfo : list) {
			//��ѯ������
			Orderopt orderopt = orderoptMapper.findPrimaryById(jsonInfo.get("orderoptId"));
			orderopt.setOrderoptComment(jsonInfo.get("commentInfo"));
			orderoptList.add(orderopt);
		}
		orderoptMapper.batchUpdata(orderoptList);
		//�ڶ�������ӵ�ַ
		Order order = orderMapper.findById(orderId);
		order.setOrderAddress(addressInfo);
		return orderMapper.updateOrder(order);
	}
	
	/**��ѯ�û��µ����ж�����*/
	public Page findList(Map<String,Object> map,Integer currentPageNo,Integer currentPageNum){
		Page page = new Page();
		
		int currentPageStart = currentPageNum*(currentPageNo-1);//mysql��ҳ��Ҫ
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
	
	/**��ѯ������Ҫ����Ķ�������Ϣ״̬*/
	public Page findListBySellCusId(Map<String,Object> map,Integer currentPageNo,Integer currentPageNum){
		Page page = new Page();
		
		int currentPageStart = currentPageNum*(currentPageNo-1);//mysql��ҳ��Ҫ
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
	
	/**�޸�һ���������״̬*/
	public boolean changeStatus(String orderoptId,String status) {
		Orderopt orderopt = orderoptMapper.findPrimaryById(orderoptId);
		if("���ջ�".equals(orderopt.getOrderoptStatus())) {
			orderopt.setOrderoptStatus(status);
			//�����տ�
			String money = orderopt.getOrderoptPrice();
			Customer customer = customerMapper.findCustomerById(orderopt.getOrderoptSellcustomerId());//��ȡ�̼���Ϣ
			customer.setcusBalance((Float.parseFloat(customer.getcusBalance())+Float.parseFloat(money))+"");
			customerMapper.updateCustomer(customer);
			return orderoptMapper.update(orderopt);
		}else if("������".equals(orderopt.getOrderoptStatus())&&"�ѹر�".equals(status)){
			orderopt.setOrderoptStatus(status);
			//����տ�
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
	
	/**��ѯ������*/
	public Orderopt findOrderopt(String orderoptId) {
		return orderoptMapper.findPrimaryById(orderoptId);
	}
	
	/**�ж�ʱ���ܹ����н���*/
	public String pay(String payType,String orderId,Float price,String cusId) {
		Customer customer = customerMapper.findCustomerById(cusId);
		Boolean payFlag = false;
		if(Float.parseFloat(customer.getcusBalance())>=price) {//����㹻
			
			Order order = orderMapper.findById(orderId);
			List<Orderopt> orderoptList = order.getOrderoptList();
			for (Orderopt orderopt : orderoptList) {
				if("������".equals(orderopt.getOrderoptGoods().getGoodsStatus())&&
						Integer.parseInt(orderopt.getOrderoptNum())<=
						Integer.parseInt(orderopt.getOrderoptGoods().getGoodsTotal())) {//����㹻
					payFlag = true;
				}else {
					payFlag = false;
					return "GoodsNotFind";//��Ʒ���ܽ���
				}
			}
			
			if(payFlag) {//���㽻������
				//1,���¶�����¼
				order.setOrderStatus("��֧��");
				for (Orderopt orderopt : orderoptList) {
					orderopt.setOrderoptStatus("������");
				}
				boolean batchUpdata = orderoptMapper.batchUpdata(orderoptList);
				boolean updateOrder = orderMapper.updateOrder(order);
				if(batchUpdata&&updateOrder) {
					//2,�޸���Ʒ������
					List<Goods> goodsList = new ArrayList<Goods>();
					for (Orderopt orderopt : orderoptList) {
						Goods goods = orderopt.getOrderoptGoods();
						Integer total = Integer.parseInt(goods.getGoodsTotal()) - Integer.parseInt(orderopt.getOrderoptNum());
						goods.setGoodsTotal(total+"");
						if(total == 0) {
							goods.setGoodsStatus("���۳�");
						}
						goodsList.add(goods);
					}
					boolean updateBatchFlag = goodsMapper.updateBatch(goodsList);
					
					//3,������ĵ����
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
			return "NoBalance";//����
		}
		return "PayError";
	}
	
	
}
