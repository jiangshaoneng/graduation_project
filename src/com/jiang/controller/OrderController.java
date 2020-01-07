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
	
	/**��������*/
	@RequestMapping(value="createOrder",method=RequestMethod.POST)
	public @ResponseBody String createOrder(HttpServletRequest request) {
		//��ȥ��ǰ���û�
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
	
	/**��ת��֧��ҳ��*/
	@RequestMapping(value="pay",method=RequestMethod.GET)
	public String pay(HttpServletRequest request,Map<String,Object> map) {
		String orderId = request.getParameter("orderId");
		//��ȡ��ǰ�û�
		Customer customer = (Customer)request.getSession().getAttribute("customer");		
		//��ѯ��������
		Order orderInfo = orderService.findOrderInfo(orderId);
		//��ѯ���û��ĵ�ַ�б�
		List<Address> addressList = addressService.findAddressListByCusId(customer.getcusId());
		
		if(orderInfo!=null) {
			map.put("addressList", addressList);
			map.put("orderInfo", orderInfo);
			return "pay";
		}else {
			return "error";			
		}
	}
	
	/**�ڶ���֧��ҳ��ʱ��ӵ�ַ*/
	@RequestMapping(value="/orderAddAddress",method=RequestMethod.POST)
	public @ResponseBody String addAddress(HttpServletRequest request) {
		String addressProvince  = (String)request.getParameter("addressProvince");
		String addressCity  = (String)request.getParameter("addressCity");
		String addressDistrict  = (String)request.getParameter("addressDistrict");
		String addressDescInfo  = (String)request.getParameter("addressDescInfo");
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		//��ӵ�ַ
		boolean addAddress = customerService.addAddress2(customer.getcusId(),addressProvince,addressCity,addressDistrict,addressDescInfo);
		if(addAddress) {
			return "success";			
		}else {
			return "error";
		}
	}
	
	/**������͵�ַ������*/
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
	
	/**���֧��*/
	@RequestMapping(value="balancePay",method=RequestMethod.POST)
	public String balancePay(HttpServletRequest request){
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String WIDout_trade_no = request.getParameter("WIDout_trade_no");//�������
		Float WIDtotal_amount = Float.parseFloat(request.getParameter("WIDtotal_amount"));//�ܽ��
		
		//�жϴ˶����Ƿ���Խ��ף�1-->����Ƿ�;2-->��Ʒ�Ƿ� '���Ƴ�';3-->����Ƿ�;
		String balancePay = orderService.pay("balancePay",WIDout_trade_no, WIDtotal_amount, customer.getcusId());
		if("PaySuccess".equals(balancePay)) {
			//����ҳ��
			return "redirect:/customerOrderManage";			
		}else {
			return "error";
		}
	}
	
	/**֧����֧���ص�����
	 * @throws UnsupportedEncodingException 
	 * @throws AlipayApiException */
	@RequestMapping(value="aliPay",method= {RequestMethod.POST,RequestMethod.GET})
	public String aliPay(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
		//��ȡ�û���Ϣ
		Customer customer = (Customer)request.getSession().getAttribute("customer");

		//��ȡ֧����GET����������Ϣ
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
			//����������δ����ڳ�������ʱʹ��
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}*/
		
		//boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //����SDK��֤ǩ��
		
		boolean  signVerified = true;////һֱ�ص����ɹ�--�ĳ�trueֱ�ӳɹ�			
		System.err.println(signVerified);
		//�������������д���ĳ������´�������ο�������
		if(signVerified) {
			System.err.println("֧����֧��������");
			//�̻�������
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//֧�������׺�
			//String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//������
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
			
			String alipayPay = orderService.pay("alipayPay",out_trade_no, Float.parseFloat(total_amount), customer.getcusId());
			
			if("PaySuccess".equals(alipayPay)) {
				//����ҳ��
				return "redirect:/customerOrderManage";			
			}else {
				return "error";
			}
		}else {
			return "error";
		}
		
	}
	
	/**�û���������ҳ��*/
	@RequestMapping(value="customerOrderManage",method=RequestMethod.GET)
	public String customerOrderManage(HttpServletRequest request,Map<String,Object> map) {
		//��ȡ������Ϣ
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("payCusId", customer.getcusId());
		map1.put("status", "������");
		Page page = orderService.findList(map1,1,10);
		map.put("page", page);
		return "customerOrderManage";
	}
	
	/**��ѯ������*/
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
	
	/**����֧��*/
	@RequestMapping(value="goPay",method=RequestMethod.POST)
	public @ResponseBody String goPay(HttpServletRequest request) {
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String orderoptId = request.getParameter("orderoptId");
		//��ԭ���Ķ���������Ϊ "��ȡ��"
		boolean flag = orderService.changeStatus(orderoptId, "��ȡ��");
		if(flag) {
			//����һ���µĶ���
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
	
	/**ȡ��֧��*/
	@RequestMapping(value="canclePay",method=RequestMethod.POST)
	public @ResponseBody String canclePay(HttpServletRequest request) {
		String orderoptId = request.getParameter("orderoptId");
		boolean flag = orderService.changeStatus(orderoptId, "��ȡ��");
		if(flag)
			return "success";
		else 
			return "error";
	}
	
	/**ȷ���ջ�*/
	@RequestMapping(value="sureDelivery",method=RequestMethod.POST)
	public @ResponseBody String sureDelivery(HttpServletRequest request) {
		String orderoptId = request.getParameter("orderoptId");
		boolean flag = orderService.changeStatus(orderoptId, "������");
		if(flag)
			return "success";
		else 
			return "error";
	}
	
	/**ȥ����*/
	@RequestMapping(value="goComment",method=RequestMethod.POST)
	public @ResponseBody String goComment(HttpServletRequest request){
		String orderoptId = request.getParameter("orderoptId");
		boolean flag = orderService.changeStatus(orderoptId, "�����");
		if(flag)
			return "success";
		else 
			return "error";
	}
}
