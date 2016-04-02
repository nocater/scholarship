package com.scholarship.webapp.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.scholarship.service.BaseService;
import com.util.PageListData;


public class BaseAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected transient Log log = LogFactory.getLog(getClass());
	protected BaseService baseService;
	
	/**
	 * 分页工具类
	 */
	protected PageListData pageListData = new PageListData();
	
	protected int pageNeeded = 1;

	protected int pageSize = 20;
	
	/**
	 * @author 
	 * @return
	 */
	public String getRealPath() {
		return this.getRequest().getSession().getServletContext().getRealPath("/");
	}
	
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	public String getRealyPath(String path) {
		return getServletContext().getRealPath(path);
	}

	public PageListData getPageListData() {
		return pageListData;
	}

	public void setPageListData(PageListData pageListData) {
		this.pageListData = pageListData;
	}

	public int getPageNeeded() {
		return pageNeeded;
	}

	public void setPageNeeded(int pageNeeded) {
		this.pageNeeded = pageNeeded;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	protected void initPageData(int totalRows) {
		pageListData.setPageNeeded(pageNeeded);
		pageListData.setPageSize(pageSize);
		pageListData.setCount(totalRows);
		if (pageListData.getPageCount() < pageListData.getPageNeeded()) {
			pageListData.setPageNeeded(1);
		}
	}
	
	public BaseService getBaseService() {
		return baseService;
	}
	
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
}
