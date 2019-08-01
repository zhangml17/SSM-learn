package com.thpower.scada.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 该类实现对未登录情况下对 指定页面的登录过滤
 * 即未登录，不可以直接访问某些页面
 */
public class LoginFilter implements Filter{
	@Override
	public void destroy() {
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		HttpSession session = request.getSession();
		
		String uri = request.getRequestURI();
		
		if("/THPBuilder/index.html".equals(uri)) {
			uri += "?login=toeditor";
		}
		
		System.out.println(uri+"--->uri");
		if(session.getAttribute("username") == null && uri.indexOf("toeditor") < 0){
//			System.out.println(uri.indexOf("toeditor")+"index");
//			//	没有登录，且访问的不是登录页面	
//			// 重定向到登录页面
			response.sendRedirect("index.html?login=toeditor");
		}else {
			// 登录了,继续访问
			chain.doFilter(arg0, arg1);
		}
	}
}
