package com.jiang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.jiang.beans.Customer;
import com.jiang.service.LoginService;

@SessionAttributes("customer")
@Controller
public class LoginController {	
	
	@Autowired
	LoginService loginService;
	
	/**�û���¼����*/
	@RequestMapping(value="/nofilter_customerLogin",method=RequestMethod.POST)
	public String customerLogin(String cusName,String cusPassword,ModelMap map) {
		//ͨ���û��������û��Ƿ����
		Customer customer = loginService.customerLogin(cusName);
		if(customer==null) {//�û�������
			map.addAttribute("msg", "�û�������!");
			return "customerLogin";
		}else {
			//�û��Ƿ�����
			if("����".equals(customer.getcusStatus())) {
				map.addAttribute("msg", "�˻�������!");
				return "customerLogin";
			}
			//�Ƚ������Ƿ���ȷ
			if(customer.getcusPassword().equals(cusPassword)) {
				//���û���Ϣ����session��,��������Ϊ���ַ���
				customer.setcusPassword("");
				map.addAttribute("customer", customer);
				return "redirect:/showIndex";//������ȷ:��Ҫ������һҳ��.???�˴�������ҳ
			}else {
				//���������������Ϣ����ʾ�û�
				map.addAttribute("msg", "�������!");
				return "customerLogin";//�������
			}
		}
	}
	
	/**�û��˳�����*/
	@RequestMapping(value="/nofilter_customerLogout",method=RequestMethod.GET)
	public String customerLogout(SessionStatus sessionStatus) {
		//��session����
		sessionStatus.setComplete();
		return "redirect:/showIndex";//������ҳ
	}
}
