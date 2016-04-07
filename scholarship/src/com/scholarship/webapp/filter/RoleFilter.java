package com.scholarship.webapp.filter;

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
 * Filter权限设置
 * 
 * @author zsa
 * 
 */
public class RoleFilter implements Filter {

	String ROLE_LIMIT;// 用户无权限页面

	public RoleFilter() {
		ROLE_LIMIT = "/scholarship/pages/commons/403.jsp";
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		String url = httpRequest.getRequestURI();
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		// 下面的页面直接通过
		if (url.contains("/login/check.action") ||
				url.contains("/login/checkSingle.action") ||
				url.contains("/login/logout.action")) {
			chain.doFilter(request, response);
			return;
		}
		
		// 待完成
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
