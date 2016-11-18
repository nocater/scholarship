package com.scholarship.webapp.action.datas;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.scholarship.module.account.Account;
import com.scholarship.module.apply.Apply;
import com.scholarship.module.college.College;
import com.scholarship.module.conf.AppConfig;
import com.scholarship.module.datas.Datas;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;
import com.scholarship.service.account.AccountService;
import com.scholarship.service.apply.ApplyService;
import com.scholarship.service.audit.AuditService;
import com.scholarship.service.college.CollegeService;
import com.scholarship.service.datas.DatasService;
import com.scholarship.service.grade.GradeService;
import com.scholarship.webapp.action.BaseAction;
import com.util.StringUtil;

public class DatasAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private	DatasService datasService;
	private AccountService accountService;
	private AuditService auditService;
	private CollegeService collegeService;
	private GradeService gradeService;
	private ApplyService applyService;
	private Apply apply;
	private Datas datas;
	private Datas datas_old;
	private Account account;
	private Role role;
	private List<College> collegeList;
	private List<Grade> gradeList;
	private String accountId;
	private String accountName;
	private String accountSex;
	private String accountPhone;
	private String accountQq;
	private String collegeId;
	private String gradeId;
	private String message;
	
	private String allpyMessage="";//当前申请信息
	private String allliedMessage="";//以往申请信息
	private String ALERT;
	
	
	public String query(){
		ALERT = AppConfig.ALERT;
		account = (Account) getSession().getAttribute("LOGON_ACCOUNT");
		role = (Role) getSession().getAttribute("LOGON_ROLE");
//		if(role.getId()!=2)return SUCCESS;//只有学生才能修改申请

		datas = datasService.queryByAccount(account,"0");
		datas_old = datasService.queryByAccount(account,"1");
		
		if(datas==null){
			//防止页面id为空 提交注入id出错
			datas = new Datas();
			datas.setId(0);
		}
		
		//获取分配学院及班级
		collegeList = collegeService.queryAll();
		gradeList = gradeService.queryAll();
		
		accountId = String.valueOf(account.getId());
		
		//申请状态
		List<Apply> applyList = applyService.queryByAccount(account);
		for(Apply a:applyList){
			if(!a.getYear().equals(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)))){
				switch(a.getStatus()){
					case -1:allliedMessage+=a.getYear()+"年申请未通过<br/>";break;
					case 0:allliedMessage+=a.getYear()+"年审核未审批<br/>";break;
					case 1:allliedMessage+=a.getYear()+"年通过班主任审批<br/>";break;
					case 2:
						if(a.getScholarship()!=null){
							allliedMessage+=a.getYear()+"年-";
							if(a.getScholarship().getCategory()!=null){
								allliedMessage+=a.getScholarship().getCategory();
								if(a.getScholarship().getLevel()!=null){
									allliedMessage+=a.getScholarship().getLevel();
								}
							}
						}
						allliedMessage+="<br/>";
						break;
				}
			}else{
				switch(a.getStatus()){
				case -1:allpyMessage="未通过";break;
				case 0:allpyMessage="未审批";break;
				case 1:allpyMessage="已通过班主任审批："+a.getScholarship().getCategory()+a.getScholarship().getLevel();break;
				case 2:
					if(a.getScholarship()!=null){
						allliedMessage+=a.getYear()+"年-";
						if(a.getScholarship().getCategory()!=null){
							allliedMessage+=a.getScholarship().getCategory();
							if(a.getScholarship().getLevel()!=null){
								allliedMessage+=a.getScholarship().getLevel();
							}
						}
					}
					allliedMessage+="<br/>";
					break;
				}
			}
		}
		if(StringUtil.isBlank(allpyMessage))allpyMessage = "未提交申请";
		
		
		return SUCCESS;
	}
	
	/***
	 * 通过账户查询申请信息
	 * @return
	 */
	public String queryByAccount(){
		account = new Account();
		if(StringUtil.isNotBlank(accountId)){
			account.setId(Integer.parseInt(accountId));
		}
		
		datas = datasService.queryByAccount(account,"0");
		datas_old = datasService.queryByAccount(account,"1");
		account = datas.getAccount();
		
		//获取分配学院及班级
		collegeList = collegeService.queryAll();
		gradeList = gradeService.queryAll();
		
		//申请状态
		List<Apply> applyList = applyService.queryByAccount(account);
		for(Apply a:applyList){
			if(!a.getYear().equals(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)))){
				switch(a.getStatus()){
					case -1:allliedMessage+=a.getYear()+"年申请未通过<br/>";break;
					case 0:allliedMessage+=a.getYear()+"年审核未审批<br/>";break;
					case 1:allliedMessage+=a.getYear()+"年通过班主任审批<br/>";break;
					case 2:allliedMessage+=a.getYear()+"年获得"+a.getScholarship().getCategory()+a.getScholarship().getLevel()+"<br/>";break;
				}
			}else{
				switch(a.getStatus()){
				case -1:allpyMessage="未通过";break;
				case 0:allpyMessage="未审批";break;
				case 1:allpyMessage="已通过班主任审批："+a.getScholarship().getCategory()+a.getScholarship().getLevel();break;
				case 2:allpyMessage="已审批为"+a.getScholarship().getCategory()+a.getScholarship().getLevel();break;
				}
			}
		}
		if(StringUtil.isBlank(allpyMessage))allpyMessage = "未提交申请";
		
		return SUCCESS;
	}
	
	/***
	 * 添加与修改
	 * @return
	 */
	public String update(){
		Account logon_acccount = (Account) getSession().getAttribute("LOGON_ACCOUNT");
		role = (Role) getSession().getAttribute("LOGON_ROLE");
//		if(role.getId()!=2) return INPUT;
		
		account = new Account();
		if(StringUtil.isNotBlank(accountId)){
			account.setId(Integer.parseInt(accountId));
		}
		datas_old = datasService.queryByAccount(account,"1");//修改前备份对象
		account = accountService.queryById(account.getId());
		datas.setAccount(account);
		
		//姓名
		if(StringUtil.isNotBlank(accountName)&&!accountName.equals(account.getName())){
			account.setName(accountName);
			datas.setName(accountName);
		}
		//性别
		if(StringUtil.isNotBlank(accountSex)&&!accountSex.equals(account.getSex())){
			account.setSex(accountSex);
			datas.setSex(accountSex);
		}
		//手机
		if(StringUtil.isNotBlank(accountPhone)&&!accountPhone.equals(account.getPhone())){
			account.setPhone(accountPhone);
		}
		//QQ
		if(StringUtil.isNotBlank(accountQq)&&!accountQq.equals(account.getQq())){
			account.setQq(accountQq);
		}
		
		College college = null;
		if(StringUtil.isNotBlank(collegeId))
			college = collegeService.queryById(Integer.parseInt(collegeId));
			//如果选择的学院和提交不一致，更新账户学院
			if(account.getCollege()==null||account.getCollege().getId()!=college.getId()){
				account.setCollege(college);
			}
		if(college!=null) {
			datas.setCollege(college.getName());
		}
		
		Grade grade = null;
		if(StringUtil.isNotBlank(gradeId))
			grade = gradeService.queryById(Integer.parseInt(gradeId));
			//如果选择的学院和提交不一致，更新账户学院
			if(account.getGrade()==null||account.getGrade().getId()!=grade.getId()){
				account.setGrade(grade);
			}
		if(grade!=null){
			datas.setGrade(grade.getName());
			datas.setMajor(grade.getMajor());
		}
		
		//更新账户信息
		accountService.update(account);
		if(logon_acccount.getId()==account.getId()){//自己修改自己的信息更新session
			getSession().removeAttribute("LOGON_ACCOUNT");
			getSession().setAttribute("LOGON_ACCOUNT", account);//更新session
		}
		
		if(datas==null){
			this.insert(datas);
		}else{
			this.updateDatas(datas,datas_old);
		}
		
		message = "信息已修改成功！";
		
		return SUCCESS;
	}
	
	/***
	 * 保存信息及提交申请
	 * @return
	 */
	public String apply(){
		//先保存信息
		if(this.update().equals(SUCCESS)&&AppConfig.APPLY==1){
			apply = new Apply();
			Account a = new Account();
			a.setId(datas.getAccount().getId());//使account name 为空  否则sql会以keyword查询
			apply.setAccount(a);
			apply.setStatus(0);
			apply.setYear(Integer.toString(Calendar.getInstance().get(Calendar.YEAR)));
			//时间在60天内大于一次不能提交
			if(applyService.query(null, apply,null,null,null,null,-60).size()>0) {
				message = "近期申请已经提交成功，请等待老师审批";
			}else {
				applyService.inert(apply);
				message = "申请已提交成功，请等待审批";
			}
		}
		if(AppConfig.APPLY==0)message = "审批未开启，请联系管理员";
		//查询数据 返回页面
		this.query();
		return SUCCESS;
	}
	
	public int insert(Datas d){
		return datasService.insert(d);
	}
	
	public int updateDatas(Datas d1, Datas d2){
		if(d1.getId()==0){
			datasService.insert(d1);
		}else{
			datasService.update(d1);
		}
//		if(d2==null){
//			//第一次修改 添加备份
//			if(d1.getId()==0){
//				datasService.insert(d1);
//			}else{
//				d2 = datasService.queryById(d1.getId());
//				d2.setType(1);
//				datasService.update(d2);
//				datasService.insert(d1);
//			}
//		}else{
//			//修改备份
//			datasService.chengeType(d1.getId(), 1);
//			d1.setId(d2.getId());
//			datasService.update(d1);
//		}
		return 1;
	}
	
	public DatasService getDatasService() {
		return datasService;
	}

	public void setDatasService(DatasService datasService) {
		this.datasService = datasService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public AuditService getAuditService() {
		return auditService;
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

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public void setDatas(Datas datas) {
		this.datas = datas;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Datas getDatas_old() {
		return datas_old;
	}

	public ApplyService getApplyService() {
		return applyService;
	}

	public Apply getApply() {
		return apply;
	}

	public void setApplyService(ApplyService applyService) {
		this.applyService = applyService;
	}

	public void setApply(Apply apply) {
		this.apply = apply;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getAccountSex() {
		return accountSex;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setAccountSex(String accountSex) {
		this.accountSex = accountSex;
	}

	public void setDatas_old(Datas datas_old) {
		this.datas_old = datas_old;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
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

	public String getCollegeId() {
		return collegeId;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAllpyMessage() {
		return allpyMessage;
	}

	public String getAllliedMessage() {
		return allliedMessage;
	}

	public void setAllpyMessage(String allpyMessage) {
		this.allpyMessage = allpyMessage;
	}

	public void setAllliedMessage(String allliedMessage) {
		this.allliedMessage = allliedMessage;
	}

	public String getALERT() {
		return ALERT;
	}

	public void setALERT(String aLERT) {
		ALERT = aLERT;
	}

	public String getAccountPhone() {
		return accountPhone;
	}

	public String getAccountQq() {
		return accountQq;
	}

	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}

	public void setAccountQq(String accountQq) {
		this.accountQq = accountQq;
	}

}
