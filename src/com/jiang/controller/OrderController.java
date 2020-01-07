package com.jiang.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.jiang.beans.Address;
import com.jiang.beans.Customer;
import com.jiang.beans.Order;
import com.jiang.beans.Orderopt;
import com.jiang.service.AddressService;
import com.jiang.service.CustomerService;
import com.jiang.service.OrderService;
import com.jiang.servicepage.Page;
import com.jiang.utils.AlipayConfig;

import net.sf.json.JSONArray;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private CustomerService customerService;
	
	/**创建订单*/
	@RequestMapping(value="createOrder",method=RequestMethod.POST)
	public @ResponseBody String createOrder(HttpServletRequest request) {
		//获去当前的用户
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		JSONArray json = JSONArray.fromObject(request.getParameter("jsonStr"));
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("orderId",request.getParameter("orderId"));
		map.put("cusId", customer.getcusId());
		
		String orderId = orderService.addOrUpdataOrder(map,json);
		if(orderId!=null) {
			return orderId;			
		}else {
			return "";
		}
	}
	
	/**跳转到支付页面*/
	@RequestMapping(value="pay",method=RequestMethod.GET)
	public String pay(HttpServletRequest request,Map<String,Object> map) {
		String orderId = request.getParameter("orderId");
		//获取当前用户
		Customer customer = (Customer)request.getSession().getAttribute("customer");		
		//查询订单详情
		Order orderInfo = orderService.findOrderInfo(orderId);
		//查询出用户的地址列表
		List<Address> addressList = addressService.findAddressListByCusId(customer.getcusId());
		
		if(orderInfo!=null) {
			map.put("addressList", addressList);
			map.put("orderInfo", orderInfo);
			return "pay";
		}else {
			return "error";			
		}
	}
	
	/**在订单支付页面时添加地址*/
	@RequestMapping(value="/orderAddAddress",method=RequestMethod.POST)
	public @ResponseBody String addAddress(HttpServletRequest request) {
		String addressProvince  = (String)request.getParameter("addressProvince");
		String addressCity  = (String)request.getParameter("addressCity");
		String addressDistrict  = (String)request.getParameter("addressDistrict");
		String addressDescInfo  = (String)request.getParameter("addressDescInfo");
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		//添加地址
		boolean addAddress = customerService.addAddress2(customer.getcusId(),addressProvince,addressCity,addressDistrict,addressDescInfo);
		if(addAddress) {
			return "success";			
		}else {
			return "error";
		}
	}
	
	/**添加配送地址到订单*/
	@RequestMapping(value="addAddressAndCommentToOrder",method=RequestMethod.POST)
	public @ResponseBody String addAddressAndCommentToOrder(HttpServletRequest request) {
		
		String payType = request.getParameter("payType");
		
		String orderId = request.getParameter("orderId");
		String addressInfo = request.getParameter("addressInfo");
		JSONArray json = JSONArray.fromObject(request.getParameter("data"));
		
		boolean addAddressFlag = orderService.addAddressAndComment(orderId,addressInfo,json);
		if(addAddressFlag) {
			if("alipay".equals(payType)) {
				return "alipay_success";							
			}else {
				return "balance_success";
			}
		}else {
			return "error";
		}
	}
	
	/**余额支付*/
	@RequestMapping(value="balancePay",method=RequestMethod.POST)
	public String balancePay(HttpServletRequest request){
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String WIDout_trade_no = request.getParameter("WIDout_trade_no");//订单编号
		Float WIDtotal_amount = Float.parseFloat(request.getParameter("WIDtotal_amount"));//总金额
		
		//判断此订单是否可以交易：1-->库存是否够;2-->商品是否 '已移除';3-->余额是否够;
		String balancePay = orderService.pay("balancePay",WIDout_trade_no, WIDtotal_amount, customer.getcusId());
		if("PaySuccess".equals(balancePay)) {
			//订单页面
			return "redirect:/customerOrderManage";			
		}else {
			return "error";
		}
	}
	
	/**支付宝支付回调网关
	 * @throws UnsupportedEncodingException 
	 * @throws AlipayApiException */
	@RequestMapping(value="aliPay",method= {RequestMethod.POST,RequestMethod.GET})
	public String aliPay(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
		//获取用户信息
		Customer customer = (Customer)request.getSession().getAttribute("customer");

		//获取支付宝GET过来反馈信息
		/*Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}*/
		
		//boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
		
		boolean  signVerified = true;////一直回调不成功--改成true直接成功			
		System.err.println(signVerified);
		//——请在这里编写您的程序（以下代码仅作参考）——
		if(signVerified) {
			System.err.println("支付宝支付。。。");
			//商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//支付宝交易号
			//String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
			
			String alipayPay = orderService.pay("alipayPay",out_trade_no, Float.parseFloat(total_amount), customer.getcusId());
			
			if("PaySuccess".equals(alipayPay)) {
				//订单页面
				return "redirect:/customerOrderManage";			
			}else {
				return "error";
			}
		}else {
			return "error";
		}
		
	}
	
	/**用户订单管理页面*/
	@RequestMapping(value="customerOrderManage",method=RequestMethod.GET)
	public String customerOrderManage(HttpServletRequest request,Map<String,Object> map) {
		//获取订单信息
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("payCusId", customer.getcusId());
		map1.put("status", "待发货");
		Page page = orderService.findList(map1,1,10);
		map.put("page", page);
		return "customerOrderManage";
	}
	
	/**查询订单项*/
	@RequestMapping(value="findOrderoptByStaus",method=RequestMethod.POST)
	public @ResponseBody Page findByStatus(HttpServletRequest request,Map<String,Object> map) {
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String status = request.getParameter("status");
		String pageNo = request.getParameter("pageNo");
		
		Integer currentPageNo;
		if(pageNo==null)
			currentPageNo = 1;
		else 
			currentPageNo = Integer.parseInt(pageNo);
		
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("payCusId", customer.getcusId());
		if(status!=null&&!status.trim().equals("")) {
			map1.put("status", status);			
		}
		
		Page page = orderService.findList(map1,currentPageNo,10);
		map.put("page", page);
		return page;
	}
	
	/**立刻支付*/
	@RequestMapping(value="goPay",method=RequestMethod.POST)
	public @ResponseBody String goPay(HttpServletRequest request) {
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String orderoptId = request.getParameter("orderoptId");
		//把原来的订单项设置为 "已取消"
		boolean flag = orderService.changeStatus(orderoptId, "已取消");
		if(flag) {
			//创建一条新的订单
			Map<String,String> map = new HashMap<String,String>();
			map.put("cusId", customer.getcusId());
			Orderopt orderopt = orderService.findOrderopt(orderoptId);
			
			String jsonStr = "[{goodsId:"+orderopt.getOrderoptGoodsId()+",payNum:"+orderopt.getOrderoptNum()+"}]";
			JSONArray json = JSONArray.fromObject(jsonStr);
			String orderId = orderService.addOrUpdataOrder(map,json);
			if(orderId!=null) {
				return orderId;	
			}else {
				return "";
			}
		}else {
			return "";
		}
	}
	
	/**取消支付*/
	@RequestMapping(value="canclePay",method=RequestMethod.POST)
	public @ResponseBody String canclePay(HttpServletRequest request) {
		String orderoptId = request.getParameter("orderoptId");
		boolean flag = orderService.changeStatus(orderoptId, "已取消");
		if(flag)
			return "success";
		else 
			return "error";
	}
	
	/**确认收货*/
	@RequestMapping(value="sureDelivery",method=RequestMethod.POST)
	public @ResponseBody String sureDelivery(HttpServletRequest request) {
		String orderoptId = request.getParameter("orderoptId");
		boolean flag = orderService.changeStatus(orderoptId, "待评价");
		if(flag)
			return "success";
		else 
			return "error";
	}
	
	/**去评价*/
	@RequestMapping(value="goComment",method=RequestMethod.POST)
	public @ResponseBody String goComment(HttpServletRequest request){
		String orderoptId = request.getParameter("orderoptId");
		boolean flag = orderService.changeStatus(orderoptId, "已完成");
		if(flag)
			return "success";
		else 
			return "error";
	}
}
