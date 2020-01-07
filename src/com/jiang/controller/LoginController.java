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
	
	/**用户登录请求*/
	@RequestMapping(value="/nofilter_customerLogin",method=RequestMethod.POST)
	public String customerLogin(String cusName,String cusPassword,ModelMap map) {
		//通过用户名查找用户是否存在
		Customer customer = loginService.customerLogin(cusName);
		if(customer==null) {//用户不存在
			map.addAttribute("msg", "用户不存在!");
			return "customerLogin";
		}else {
			//用户是否启用
			if("冻结".equals(customer.getcusStatus())) {
				map.addAttribute("msg", "账户被冻结!");
				return "customerLogin";
			}
			//比较密码是否正确
			if(customer.getcusPassword().equals(cusPassword)) {
				//将用户信息放在session中,将密码设为空字符串
				customer.setcusPassword("");
				map.addAttribute("customer", customer);
				return "redirect:/showIndex";//密码正确:需要返回上一页面.???此处返回首页
			}else {
				//密码错误放入错误信息，提示用户
				map.addAttribute("msg", "密码错误!");
				return "customerLogin";//密码错误
			}
		}
	}
	
	/**用户退出请求*/
	@RequestMapping(value="/nofilter_customerLogout",method=RequestMethod.GET)
	public String customerLogout(SessionStatus sessionStatus) {
		//将session销毁
		sessionStatus.setComplete();
		return "redirect:/showIndex";//返回首页
	}
}
