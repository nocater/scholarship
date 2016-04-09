package com.scholarship.webapp.action.scholarship;

import java.util.ArrayList;
import java.util.List;

import com.scholarship.module.account.Account;
import com.scholarship.module.scholarship.Scholarship;
import com.scholarship.service.audit.AuditService;
import com.scholarship.service.scholarship.ScholarshipService;
import com.scholarship.webapp.action.BaseAction;
import com.util.StringUtil;

public class ScholarshipAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ScholarshipService scholarshipService;
	private AuditService auditService;
	private List<Scholarship> scholarshipList;
	private Scholarship scholarship;
	private String scholarshipId;
	private String order;
	private String ids;
	private String method;
	
	public String query(){
		scholarshipList = scholarshipService.queryAll();
		return SUCCESS;
	}
	
	public String queryById(){
		if(StringUtil.isNotBlank(scholarshipId)){
			scholarship = scholarshipService.queryById(Integer.parseInt(scholarshipId));
		}else{
			scholarship = new Scholarship();
		}
		return SUCCESS;
	}
	
	public String update(){
		if(scholarship.getId()==0){
			this.insert(scholarship);
		}else{
			this.update(scholarship);
		}
		
		return SUCCESS;
	}
	
	public String execute(){
		if(StringUtil.isNotBlank(ids)){
			String[] arrays = ids.split(",");
			if(StringUtil.isNotBlank(method)){
				if(method.equals("-1")){
					//删除
					for(int i = 0;i< arrays.length;i++){
						scholarship = scholarshipService.queryById(Integer.parseInt(arrays[i]));
						this.delete(scholarship);
					}
				}
			}
		}
		return SUCCESS;
	}
	
	public int insert(Scholarship s){
		Account a = (Account) getSession().getAttribute("LOGON_ACCOUNT");
		List<String> list = new ArrayList<String>();
		list.add(s.getCategory()+"-"+s.getLevel()+"-"+s.getMoney());
		auditService.operator(a.getId(), "增加奖项", getRequest().getRemoteAddr(), list);
		return scholarshipService.insert(s);
	}
	
	public int update(Scholarship s){
		Account a = (Account) getSession().getAttribute("LOGON_ACCOUNT");
		List<String> list = new ArrayList<String>();
		list.add(s.getCategory()+"-"+s.getLevel()+"-"+s.getMoney());
		auditService.operator(a.getId(), "修改奖项", getRequest().getRemoteAddr(), list);
		return scholarshipService.update(s);
	}
	
	public void delete(Scholarship s){
		Account a = (Account) getSession().getAttribute("LOGON_ACCOUNT");
		List<String> list = new ArrayList<String>();
		list.add(s.getCategory()+"-"+s.getLevel()+"-"+s.getMoney());
		auditService.operator(a.getId(), "删除奖项", getRequest().getRemoteAddr(), list);
		scholarshipService.delete(s);
	}
	
	public ScholarshipService getScholarshipService() {
		return scholarshipService;
	}

	public void setScholarshipService(ScholarshipService scholarshipService) {
		this.scholarshipService = scholarshipService;
	}

	public AuditService getAuditServie() {
		return auditService;
	}

	public List<Scholarship> getScholarshipList() {
		return scholarshipList;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public void setScholarshipList(List<Scholarship> scholarshipList) {
		this.scholarshipList = scholarshipList;
	}

	public AuditService getAuditService() {
		return auditService;
	}

	public String getScholarshipId() {
		return scholarshipId;
	}

	public String getOrder() {
		return order;
	}

	public void setScholarshipId(String scholarshipId) {
		this.scholarshipId = scholarshipId;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Scholarship getScholarship() {
		return scholarship;
	}

	public void setScholarship(Scholarship scholarship) {
		this.scholarship = scholarship;
	}

	public String getIds() {
		return ids;
	}

	public String getMethod() {
		return method;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
