package com.scholarship.webapp.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class XSSFilter implements Filter {
	private final String ERROR_PAGE = "/scholarship/pages/commons/error.jsp";
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse hResponse = (HttpServletResponse) response;
		Enumeration<String> pn = request.getParameterNames();
		while(pn.hasMoreElements()){
			String name = pn.nextElement();
			if("employee.userFingertemplate".equals(name) || "feature".equals(name))
				continue;
			String value = request.getParameter(name);
			if( Pattern.compile("<{1,}|>{1,}").matcher(value).find()){
				hResponse.sendRedirect(ERROR_PAGE);
				return;
			}
		}
		chain.doFilter(request, response);
		return;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
