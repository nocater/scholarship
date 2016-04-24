package com.scholarship.webapp.action.init;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/***
 * 服务器启动自动执行 可用于扩展设置或其他功能
 * @author chenshuai
 *
 */
public class InitServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private transient Log log = LogFactory.getLog(getClass());
	
	public void init(ServletConfig config) {
		System.out.println("Project InitServlet is invoked!");
	}
	
}
