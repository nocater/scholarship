package com.scholarship.webapp.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.scholarship.module.account.Account;
import com.scholarship.module.account.OnlineUser;
import com.scholarship.service.account.AccountService;



/**
 * Description: 登录过滤器			
 * @author 郭煜玺                         
 * @Version 1.0                            
 * @Created at 2010-12-31               
 * @Modified by         
 */
public class LoginFilter implements Filter {

	private final String LOGIN_PAGE;
	private final String LOGOUT_PAGE;

	protected FilterConfig filterConfig;

	public LoginFilter() {
		LOGIN_PAGE = "/scholarship/pages/login.jsp";
		LOGOUT_PAGE = "/scholarship/login/logout.action";
	}

	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void init(FilterConfig config) throws ServletException {
		filterConfig = config;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) request;
		HttpServletResponse hResponse = (HttpServletResponse) response;
		////////
		HttpSession session = hRequest.getSession();
		WebApplicationContext wac = WebApplicationContextUtils
						.getRequiredWebApplicationContext(session.getServletContext());
		AccountService  accountService = (AccountService)wac.getBean("accountService");
		///////////
		String url = hRequest.getRequestURI();
		//过滤参数非法字符
		/*
		String param = "";
		String paramValue = "";
		
		@SuppressWarnings("rawtypes")
		java.util.Enumeration params = hRequest.getParameterNames();
		while(params.hasMoreElements()){
			param = (String) params.nextElement();
			String[] values = hRequest.getParameterValues(param);
			int i;
			for(i = 0; i < values.length; i++){
				paramValue = values[i]; 
			    paramValue = paramValue.replaceAll("<", "&lt"); 
			    paramValue = paramValue.replaceAll(">", "&gt");
			    values[i] = paramValue;
			}
			request.setAttribute(param, paramValue);
		}
		*/
		// 如果是登录请求则直接通过
		if(url.contains("/login/check.action") ||
				url.contains("/login/checkSingle.action") ||
				url.contains("/login/logout.action")) {
			chain.doFilter(request, response);
			return;
		}
		
		// 此外的请求均判断是否登录
		//Object isLog = hRequest.getSession().getAttribute("FORT_LOGON_USER");
		Account isLog = (Account)hRequest.getSession().getAttribute("LOGON_ACCOUNT");
		
		if (isLog != null) {
			// 判断是否需要被单用户在线机制强制退出
			List<OnlineUser> userList = (List<OnlineUser>)hRequest.getSession().getServletContext().getAttribute("ONLINE_ACCOUNTLIST");
			for(OnlineUser user : userList) {
				if(hRequest.getSession().getId().equals(user.getSessionId()) && user.getFlag() != 1) {
					hResponse.sendRedirect(LOGOUT_PAGE);
					return;
				}
			}
			
			if(accountService.queryById(isLog.getId())==null){		
				hResponse.sendRedirect(LOGOUT_PAGE);
				return;
			}
			
			chain.doFilter(request, response);
			return;
		} else {
			//hResponse.sendRedirect(LOGIN_PAGE);
			  hResponse.sendRedirect(LOGOUT_PAGE);
			return;
			
			
		}
	}

	public void destroy() {
		filterConfig = null;
	}
	
}
