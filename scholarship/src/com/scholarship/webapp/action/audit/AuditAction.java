package com.scholarship.webapp.action.audit;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.scholarship.module.audit.Audit;
import com.scholarship.module.role.Role;
import com.scholarship.service.account.AccountService;
import com.scholarship.service.audit.AuditService;
import com.scholarship.webapp.action.BaseAction;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class AuditAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AuditService auditService;
	private AccountService accountService;
	private Audit audit;
	private List<Audit> auditList;
	
	private String ids;
	private String keyword;
	//高级条件查询
	private String selNote;
	private String selAccountName;
	private String selOperation;
	private String selSourceIP;

	private String startDate;
	private String endDate;
	
	
	public String query(){
		HttpServletRequest request = super.getRequest();

		Page page = null;
		audit = new Audit();
		
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		
		// 接受查询条件
		Map<String, String> map = new HashMap<String, String>();

		if (StringUtil.isNotBlank(keyword)) {
			try {
				keyword=java.net.URLDecoder.decode(keyword,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			map.put("keyword", keyword);
		}
		
		if(StringUtil.isNotBlank(startDate)){
			map.put("startDate", startDate);
		}
		if(StringUtil.isNotBlank(endDate)){
			map.put("endDate", endDate);
		}
		
		//高级条件
		if(StringUtil.isNotBlank(selAccountName)){
			map.put("accountName", selAccountName);
		}
		if(StringUtil.isNotBlank(selOperation)){
			map.put("operation", selOperation);
		}
		if(StringUtil.isNotBlank(selNote)){
			map.put("note", selNote);
		}
		if(StringUtil.isNotBlank(selSourceIP)){
			map.put("sourceIP", selSourceIP);
		}
		Role role = (Role)this.getSession().getAttribute("LOGON_ROLE");
		if(role.getId()==1){
			SearchResult<Audit> sr = auditService.query(role, map, page);
			auditList = sr.getList();
			request.setAttribute("Page", sr.getPage());
		}
		return SUCCESS;
	}
	
	public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public Audit getAudit() {
		return audit;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public void setAudit(Audit audit) {
		this.audit = audit;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getIds() {
		return ids;
	}

	public String getSelNote() {
		return selNote;
	}

	public String getSelAccountName() {
		return selAccountName;
	}

	public String getSelOperation() {
		return selOperation;
	}

	public String getSelSourceIP() {
		return selSourceIP;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setSelNote(String selNote) {
		this.selNote = selNote;
	}

	public void setSelAccountName(String selAccountName) {
		this.selAccountName = selAccountName;
	}

	public void setSelOperation(String selOperation) {
		this.selOperation = selOperation;
	}

	public void setSelSourceIP(String selSourceIP) {
		this.selSourceIP = selSourceIP;
	}

	public List<Audit> getAuditList() {
		return auditList;
	}

	public void setAuditList(List<Audit> auditList) {
		this.auditList = auditList;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
