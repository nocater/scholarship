package com.scholarship.webapp.action.apply;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.scholarship.module.account.Account;
import com.scholarship.module.apply.Apply;
import com.scholarship.module.college.College;
import com.scholarship.module.datas.Datas;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;
import com.scholarship.module.scholarship.Scholarship;
import com.scholarship.service.account.AccountService;
import com.scholarship.service.apply.ApplyService;
import com.scholarship.service.audit.AuditService;
import com.scholarship.service.college.CollegeService;
import com.scholarship.service.datas.DatasService;
import com.scholarship.service.grade.GradeService;
import com.scholarship.service.role.RoleService;
import com.scholarship.service.scholarship.ScholarshipService;
import com.scholarship.webapp.action.BaseAction;
import com.util.StringUtil;

public class ApplyAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ApplyService applyService;
	private AccountService accountService;
	private DatasService datasService;
	private RoleService roleService;
	private AuditService auditService;
	private ScholarshipService scholarshipService;
	private CollegeService collegeService;
	private GradeService gradeService;
	
	private Role role;
	private Apply apply;
	private Datas datas;
	private Account account;
	private Scholarship scholarship;
	
	private List<Apply> applyList;
	private List<Datas>	datasList;
	private List<Account> accountList;
	private List<Scholarship> scholarshipLIst;
	
	private List<College> collegeList;
	private List<Grade> gradeList;
	private List<Scholarship> scholarshipList;
	
	private String ids;//IDs
	private String ss;//奖学金IDS
	private String method;//执行类型
	
	private List<String> appliedMessageList;
	
	public String query(){
		String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		role = (Role) getSession().getAttribute("LOGON_ROLE");
		//根据角色查询未审批
		apply = new Apply();
		apply.setYear(year);
		applyList = applyService.query(role, apply);
		accountList = new ArrayList<>();
		datasList = new ArrayList<>();
		appliedMessageList = new ArrayList<>();
		for(Apply a : applyList){
			accountList.add(a.getAccount());
			datasList.add(datasService.queryByAccount(a.getAccount(), "0"));//通过账户查询申请信息 0表示最新 1表示修改前备份
			
			//以往申请信息
			List<Apply> applyList = applyService.queryByAccount(a.getAccount());
			String allliedMessage="";
			for(Apply as:applyList){
				if(!as.getYear().equals(year)){
					switch(as.getStatus()){
						case -1:allliedMessage+=as.getYear()+"年申请未通过<br/>";break;
						case 0:allliedMessage+=as.getYear()+"年审核未审批<br/>";break;
						case 1:allliedMessage+=as.getYear()+"年通过班主任审批<br/>";break;
						case 2:allliedMessage+=as.getYear()+"年获得"+a.getScholarship().getCategory()+a.getScholarship().getLevel()+"<br/>";break;
					}
				}
			}
			appliedMessageList.add(allliedMessage);
		}
		
		//获取分配学院及班级
		collegeList = collegeService.queryByRole(role);
		gradeList = gradeService.queryByRole(role);
		//获取奖学金		
		scholarshipList = scholarshipService.queryAll();
		
		return SUCCESS;
	}
	
	public String execute(){
		Account a = (Account) getSession().getAttribute("LOGON_ACCOUNT");
		Role r = (Role) getSession().getAttribute("LOGON_ROLE");
		if(StringUtil.isNotBlank(method)){
			String[] idlist = null;
			String[] sslist = null;
			if(StringUtil.isNotBlank(ids)){
				idlist = ids.split(",");
			}
			if(StringUtil.isNotBlank(ss)){
				sslist = ss.split(",");
			}
			if(method.equals("1")){
				//保存并通过申请
				for(int i=0;i<idlist.length;i++){
					Apply apply = applyService.queryById(Integer.parseInt(idlist[i]));
					if(apply!=null){//更新奖学金信息
						Scholarship s = new Scholarship();
						s.setId(Integer.parseInt(sslist[i]));
						apply.setScholarship(s);
						
						//更新审批状态
						if(apply.getStatus()==-1)continue;
						//班主任只能审批为1
						if(collegeService.queryByRole(r).size()==0){
							apply.setStatus(1);
						}else{
							apply.setStatus(2);
						}
						//更新审批人
						apply.setApprove_Account(a);
						
						applyService.update(apply);
					}
				}
			}else if(method.equals("-1")){
				//废弃申请
				for(int i=0;i<idlist.length;i++){
					Apply apply = applyService.queryById(Integer.parseInt(idlist[i]));
					if(apply!=null){
						apply.setStatus(-1);
						apply.setApprove_Account(a);
						applyService.update(apply);
					}
				}
			}
		}
		return SUCCESS;
	}
	
	public AccountService getAccountService() {
		return accountService;
	}

	public DatasService getDatasService() {
		return datasService;
	}

	public Apply getApply() {
		return apply;
	}

	public Datas getDatas() {
		return datas;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public void setDatasService(DatasService datasService) {
		this.datasService = datasService;
	}

	public void setApply(Apply apply) {
		this.apply = apply;
	}

	public void setDatas(Datas datas) {
		this.datas = datas;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public Role getRole() {
		return role;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Apply> getApplyList() {
		return applyList;
	}

	public void setApplyList(List<Apply> applyList) {
		this.applyList = applyList;
	}


	public ApplyService getApplyService() {
		return applyService;
	}


	public void setApplyService(ApplyService applyService) {
		this.applyService = applyService;
	}

	public AuditService getAuditService() {
		return auditService;
	}

	public ScholarshipService getScholarshipService() {
		return scholarshipService;
	}

	public void setScholarshipService(ScholarshipService scholarshipService) {
		this.scholarshipService = scholarshipService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public Scholarship getScholarship() {
		return scholarship;
	}

	public List<Datas> getDatasList() {
		return datasList;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public List<Scholarship> getScholarshipLIst() {
		return scholarshipLIst;
	}

	public void setScholarship(Scholarship scholarship) {
		this.scholarship = scholarship;
	}

	public void setDatasList(List<Datas> datasList) {
		this.datasList = datasList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public void setScholarshipLIst(List<Scholarship> scholarshipLIst) {
		this.scholarshipLIst = scholarshipLIst;
	}

	public List<College> getCollegeList() {
		return collegeList;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setCollegeList(List<College> collegeList) {
		this.collegeList = collegeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public CollegeService getCollegeService() {
		return collegeService;
	}

	public GradeService getGradeService() {
		return gradeService;
	}

	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	public List<Scholarship> getScholarshipList() {
		return scholarshipList;
	}

	public void setScholarshipList(List<Scholarship> scholarshipList) {
		this.scholarshipList = scholarshipList;
	}

	public String getIds() {
		return ids;
	}

	public String getSs() {
		return ss;
	}

	public String getMethod() {
		return method;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public List<String> getAppliedMessageList() {
		return appliedMessageList;
	}

	public void setAppliedMessageList(List<String> appliedMessageList) {
		this.appliedMessageList = appliedMessageList;
	}

}
