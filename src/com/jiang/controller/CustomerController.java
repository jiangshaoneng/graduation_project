package com.jiang.controller;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jiang.beans.Customer;
import com.jiang.service.CustomerService;
import com.jiang.utils.NewIdUtil;
import com.jiang.utils.SendJMail;
import com.jiang.utils.TimeUtil;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	/**����û����Ƿ����*/
	@RequestMapping(value="/nofilter_okCusName",method=RequestMethod.POST)
	public @ResponseBody String okCusName(HttpServletRequest request) {
		String cusNickname  = (String)request.getParameter("cus_nickname");
		boolean okCusName = customerService.okCusName(cusNickname);
		if(okCusName) {
			return "success";//�û�������
		}else {
			return "error";
		}
	}
	
	/**ע������û�*/
	@RequestMapping(value="/nofilter_addCustomerInfo",method=RequestMethod.POST)
	public @ResponseBody String addCustomerInfo(HttpServletRequest request) {
		//��ȡҳ���ύ����������
		String cusNickname  = (String)request.getParameter("cus_nickname");
		String cusRealname  = (String)request.getParameter("cus_realname");
		String cusPassword  = (String)request.getParameter("cus_password");
		String cusGender  = (String)request.getParameter("cus_gender");
		String cusPhone  = (String)request.getParameter("cus_phone");
		String cusBirthday  = (String)request.getParameter("cus_birthday");
		String cusAddress_1  = (String)request.getParameter("cus_address_1");
		String cusAddress_2  = (String)request.getParameter("cus_address_2");
		String cusAddress_3  = (String)request.getParameter("cus_address_3");
		String cusAddress_4  = (String)request.getParameter("cus_address_4");
		
		//�� ʡ+��+��ϸ��ַƴ�ӳ���
		boolean flagAddCus = customerService.addCustomerInfo(cusNickname,cusRealname,cusPassword,cusGender,cusPhone,cusBirthday);
		
		if(flagAddCus) {
			//���һ��Ĭ�ϵ�ַ
			boolean flagAddAddress = customerService.addAddress(cusNickname,cusAddress_1,cusAddress_2,cusAddress_3,cusAddress_4);
			if(flagAddAddress) {
				return "success";							
			}else {
				return "error";
			}
		}else {
			return "error";
		}
	}
	
	/**�����ʼ�*/
	@RequestMapping(value="/nofilter_sendEmail",method=RequestMethod.POST)
	public @ResponseBody String sendEmail(HttpServletRequest request) {
		String cusEmail  = (String)request.getParameter("cusEmail");

		String checkNumber = NewIdUtil.getCheckNumber();//�����ȡһ����֤��
		String checkInfo = checkNumber;
		
		boolean sendMail = SendJMail.sendMail(cusEmail,checkInfo);
		if(sendMail) {
			return checkNumber;			
		}else {
			return "error";
		}
	}
	
	/**������,���û�������֤*/
	@RequestMapping(value="/nofilter_addEmail",method=RequestMethod.POST)
	public @ResponseBody String addEmail(HttpServletRequest request) {
		String nickName  = (String)request.getParameter("nickName");
		String email  = (String)request.getParameter("emil");
		
		boolean addEmailFlag = customerService.addEmail(nickName, email);
		if(addEmailFlag) {
			return "success";			
		}else {
			return "error";
		}
	}
	
	/**��ʾ������Ϣҳ��*/
	@RequestMapping(value="/customerInfo",method= {RequestMethod.GET,RequestMethod.POST})
	public String customerInfo(HttpServletRequest request,Map<String,Object> map) {
		//��ȡsession�е�customer����
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		//���ڸ�ʽת��,������ҳ������ʾ��date��
		
		Customer cus = customerService.findCustomerByCusId(customer.getcusId());
		String birthday = TimeUtil.dateToString(cus.getcusBirthday());
		
		map.put("birthday", birthday);
		map.put("cus", cus);

		return "customerInfo";
	}
	
	/**�޸ĸ�����Ϣ*/
	@RequestMapping(value="/updateCustomer",method=RequestMethod.POST)
	public String updateCustomer(HttpServletRequest request,Map<String,Object> map) {
		String model  = (String)request.getParameter("model");
		String cusId  = (String)request.getParameter("cusId");
		String nickName  = (String)request.getParameter("nickName");
		String cusGender  = (String)request.getParameter("cusGender");
		String cusBirthday  = (String)request.getParameter("cusBirthday");
		String cusPhone  = (String)request.getParameter("cusPhone");
		
		boolean updateCustomer = customerService.updateCustomer(cusId,nickName,cusGender,cusBirthday,cusPhone);
		
		if(updateCustomer) {
			request.getSession().setAttribute("model", model);
			return "redirect:/customerInfo";			
		}else {
			return "errorPage";
		}
	}
	
	/**У�������Ƿ���ȷ*/
	@RequestMapping(value="/okOldpassword",method=RequestMethod.POST)
	public @ResponseBody String okOldpassword(HttpServletRequest request) {
		String oldPassword  = (String)request.getParameter("oldPassword");
		//��ȡsession�е�customer��Ϣ
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		//�ж������Ƿ���ȷ
		boolean okOldpassword = customerService.okOldpassword(customer.getcusId(), oldPassword);
		if(okOldpassword) {
			return "success";
		}else {
			return "error";
		}
	}
	
	/**ȷ���޸�����*/
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	public String updatePassword(HttpServletRequest request) {
		//��ȡsession�е�customer��Ϣ
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		String newPassword  = (String)request.getParameter("newPassword");
		
		boolean updatePwd = customerService.updatePwd(customer.getcusId(),newPassword);
		if(updatePwd) {//�����޸ĳɹ�,�û��˳���¼״̬
			return "redirect:/nofilter_customerLogout";//�޸�������˳���¼������ҳ
		}else {
			return "error";
		}
	}
	
	/**��ֵ*/
	@RequestMapping(value="/addMoney",method=RequestMethod.POST)
	public String addMoney(HttpServletRequest request,Map<String,Object> map){
		String model  = (String)request.getParameter("model");//������Ϣ��ģ���ʶ
		String money  = (String)request.getParameter("money");
		
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		boolean addMoney = customerService.addMoney(customer.getcusId(), money);
		
		if(addMoney) {
			Customer newCustomer = customerService.findCustomerByCusId(customer.getcusId());
			request.getSession().setAttribute("model", model);
			request.getSession().setAttribute("customer", newCustomer);//����session�е��û���Ϣ
			return "redirect:/customerInfo";
		}else {
			return "error";
		}
	}
	
	/**����Ĭ�ϵ�ַ*/
	@RequestMapping(value="/setDefAddress",method=RequestMethod.POST)
	public String setDefAddress(HttpServletRequest request) {
		String model  = (String)request.getParameter("model");
		String addressId  = (String)request.getParameter("addressId");
		
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		boolean setDefAddress = customerService.setDefAddress(customer.getcusId(),addressId);
		if(setDefAddress) {
			request.getSession().setAttribute("model", model);
			return "redirect:/customerInfo";
		}else {
			return "error";
		}
	}
	
	/**��ӵ�ַ*/
	@RequestMapping(value="/addAddress",method=RequestMethod.POST)
	public String addAddress(HttpServletRequest request) {
		
		String model  = (String)request.getParameter("model");
		String addressProvince  = (String)request.getParameter("addressProvince");
		String addressCity  = (String)request.getParameter("addressCity");
		String addressDistrict  = (String)request.getParameter("addressDistrict");
		String addressDescInfo  = (String)request.getParameter("addressDescInfo");
		
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		
		//��ӵ�ַ
		boolean addAddress = customerService.addAddress2(customer.getcusId(),addressProvince,addressCity,addressDistrict,addressDescInfo);
		if(addAddress) {
			request.getSession().setAttribute("model", model);
			return "redirect:/customerInfo";			
		}else {
			return "error";
		}
	}
	
	/**ɾ����ַ*/
	@RequestMapping(value="/delAddress",method=RequestMethod.POST)
	public String delAddress(HttpServletRequest request) {
		
		String model  = (String)request.getParameter("model");
		String addressId  = (String)request.getParameter("addressId");
		boolean delAddress = customerService.delAddress(addressId);
		
		if(delAddress) {
			request.getSession().setAttribute("model", model);
			return "redirect:/customerInfo";			
		}else {
			return "error";
		}
		
	}
	
	/**�޸ĵ�ַ*/
	@RequestMapping(value="/updateAddress",method=RequestMethod.POST)
	public String updateAddress(HttpServletRequest request) {
		String model  = (String)request.getParameter("model");
		String addressId  = (String)request.getParameter("addressId");
		String addressProvince  = (String)request.getParameter("addressProvince");
		String addressCity  = (String)request.getParameter("addressCity");
		String addressDistrict  = (String)request.getParameter("addressDistrict");
		String addressDescInfo  = (String)request.getParameter("addressDescInfo");
		
		boolean updateAddress = customerService.updateAddress(addressId,addressProvince,addressCity,addressDistrict,addressDescInfo);
		if(updateAddress) {
			request.getSession().setAttribute("model", model);
			return "redirect:/customerInfo";			
		}else {
			return "error";
		}
	}
	
	/**�޸�����*/
	@RequestMapping(value="/updateEmail",method=RequestMethod.POST)
	public String updateEmail(HttpServletRequest request) {
		String model  = (String)request.getParameter("model");
		String cusEmail  = (String)request.getParameter("cus_email");
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		boolean updateEmail = customerService.updateEmail(customer.getcusId(), cusEmail);
		
		if(updateEmail) {
			request.getSession().setAttribute("model", model);
			return "redirect:/customerInfo";			
		}else {
			return "error";
		}
	}
	
}
