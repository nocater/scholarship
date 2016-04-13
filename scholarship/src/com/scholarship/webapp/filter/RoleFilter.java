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

import com.scholarship.module.account.Account;
import com.scholarship.module.role.Role;

/**
 * Filter权限设置
 * 
 * @author cs
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
				url.contains("/login/logout.action") ||
				url.contains("/role-selectCollege/queryAllCollegeAjax") ||
				url.contains("/role-selectGrade/queryAllGradeAjax") ||
				url.contains("/account/queryMe.action") ||
				url.contains("/account/infoset.action")) {
			chain.doFilter(request, response);
			return;
		}
		
		Account a = (Account) session.getAttribute("LOGON_ACCOUNT");
		Role r = (Role) session.getAttribute("LOGON_ROLE");
		String method = httpRequest.getMethod();
		
		
		if(r.getId()==2){
			if( url.contains("/account/") ||
				url.contains("/apply/") ||
				url.contains("/college/") ||
				url.contains("/grade/") ||
				url.contains("/role/update") ||
				url.contains("/audit/")){
				httpResponse.sendRedirect(ROLE_LIMIT);
				return;
			}
		}
		
		if(method.equalsIgnoreCase("GET")){
			if(url.contains("update.action")||url.contains("insert")){
				httpResponse.sendRedirect(ROLE_LIMIT);
				return;
			}
		}
		
		//账户模块
		if(url.contains("/account/resetPWD.action")){
			if(r.getId()==2){
				httpResponse.sendRedirect(ROLE_LIMIT);
				return;
			}
		}
		//审批申请模块
		
		//学院模块
		if(url.contains("/college/update")){
			if(r.getCollegeList().size()<0){
				httpResponse.sendRedirect(ROLE_LIMIT);
				return;
			}
		}
		//学院模块
		if(url.contains("/grade/update")){
			if(r.getGradeList().size()<0){
				httpResponse.sendRedirect(ROLE_LIMIT);
				return;
			}
		}
		//设置模块
		if(url.contains("/config/")&&r.getId()!=1){
			httpResponse.sendRedirect(ROLE_LIMIT);
			return;
		}
		
		
		// 待完成
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
