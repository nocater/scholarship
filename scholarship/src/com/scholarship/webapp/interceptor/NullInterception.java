package com.scholarship.webapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/***
 * 拦截器 等待功能扩充
 * @author chenshuai
 *
 */
public class NullInterception extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpSession session = request.getSession();
		
		//action名称
		String actionName = invocation.getProxy().getActionName();
		//方法名
		String methodName = invocation.getProxy().getMethod();
		
		System.out.println("Interception is invoked!");
		
		return invocation.invoke();
	}

}
