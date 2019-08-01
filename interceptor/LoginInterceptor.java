package com.gmt.scada.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{
	@Override
	public void afterCompletion(HttpServletRequest arg0,HttpServletResponse arg1,
			Object arg2,Exception arg3) throws Exception {
		// 	执行完毕，返回拦截
	}
	
	@Override
	public void postHandle (HttpServletRequest arg0,HttpServletResponse arg1,
			Object arg2,ModelAndView arg3) throws Exception {
		//  在处理过程中，执行拦截		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,
			Object arg2) throws Exception{
		
		HttpSession session = request.getSession();
		if(session.getAttribute("username")!= null) {
			// 登录成功，不拦截
			return true;
		}else {
			response.sendRedirect(request.getContextPath()+"/xx/login");
			return false;
		}
		
	}
}
