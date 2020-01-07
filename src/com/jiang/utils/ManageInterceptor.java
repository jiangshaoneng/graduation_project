package com.jiang.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jiang.beans.Admin;

public class ManageInterceptor implements HandlerInterceptor{
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("4");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("5");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("6");
		String url = request.getRequestURI(); 
		
		System.out.println(url);
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin!=null) {//用户登录不进行拦截
			return true;			
		}else {
			//response.sendRedirect("/graduation_project/pages/manageLogin.jsp");
			request.getRequestDispatcher("/pages/manageLogin.jsp").forward(request, response);
			return false;
		}
	}
	
}
