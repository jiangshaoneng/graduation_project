package com.jiang.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.beans.Customer;

/**������*/
public class JiangInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		// TODO Auto-generated method stub
		String url = request.getRequestURI(); 
		System.out.println("�û����ʵ�ַ��"+url);
		Customer customer = (Customer)request.getSession().getAttribute("customer");
		if(customer!=null) {//�û���¼����������
			return true;			
		}else {
			request.getRequestDispatcher("/pages/customerLogin.jsp").forward(request, response);
			return false;
		}
	}

}
