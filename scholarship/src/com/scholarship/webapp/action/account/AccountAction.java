package com.scholarship.webapp.action.account;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.scholarship.module.account.Account;
import com.scholarship.module.college.College;
import com.scholarship.module.datas.Datas;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;
import com.scholarship.service.account.AccountService;
import com.scholarship.service.audit.AuditService;
import com.scholarship.service.college.CollegeService;
import com.scholarship.service.datas.DatasService;
import com.scholarship.service.grade.GradeService;
import com.scholarship.service.role.RoleService;
import com.scholarship.webapp.action.BaseAction;
import com.util.MD5;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class AccountAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AccountService accountService;
	private RoleService roleService;
	private CollegeService collegeService;
	private GradeService gradeService;
	private AuditService auditService;
	private DatasService datasService;
	
	private List<Account> accountList;
	private List<Role>	roleList;
	private List<College> collegeList;
	private List<Grade> gradeList;
	private Role role;
	private College college;
	private Grade grade;
	private String onlyStudent;
	private String roleId;
	private String collegeId;
	private String gradeId;
	private Account account;
	
	private String keyword;
	private String order;
	
	private String accountId;
	private String accountAccno;
	private String accountPassword;
	private String accountPassword2;
	private String accountName;
	private String accountSex;
	private String accountPhone;
	private String accountEmail;
	
	private String ids;
	private String method;
	
	private String returnType;
	
	public String query(){
		HttpServletRequest request = super.getRequest();
		Page page = null;
		account = new Account();
		
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		
		//模糊查询 名字
		if(StringUtil.isNotBlank(keyword)){
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
				account.setName(keyword);
				account.setAccno(keyword);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//模糊/高级查询及排序
		Map<String,String> map = new HashMap<String,String>();
		if(StringUtil.isNotBlank(account.getAccno())){
			map.put("accno", account.getAccno());
		}
		if(StringUtil.isNotBlank(account.getName())){
			map.put("name", account.getName());
		}
		if(StringUtil.isNotBlank(order)){
			map.put("order", order);
		}
		if(StringUtil.isNotBlank(onlyStudent)&&!onlyStudent.trim().equals("0")){
			map.put("onlyStudent", onlyStudent);
		}
		if(StringUtil.isNotBlank(collegeId)&&!collegeId.trim().equals("0")){
			map.put("collegeId", collegeId);
		}
		if(StringUtil.isNotBlank(gradeId)&&!gradeId.trim().equals("0")){
			map.put("gradeId", gradeId);
		}
		
		//获取当前账户角色
		role = (Role)getSession().getAttribute("LOGON_ROLE");
		//查询账户
		SearchResult<Account> sr = (SearchResult<Account>) accountService.query(role, map, page);
		if(sr!=null) {
			accountList = (List<Account>) sr.getList();
			request.setAttribute("Page", sr.getPage());
		}
		
		//获取分配学院及班级
		collegeList = collegeService.queryByRole(role);
		gradeList = gradeService.queryByRole(role);
		
		return SUCCESS;
	}
	
	public String queryById(){
		roleList = roleService.queryAll();
		collegeList = collegeService.queryAll();
		gradeList = gradeService.queryAll();
		if(StringUtil.isBlank(accountId)) return SUCCESS;
		account = accountService.queryById(Integer.parseInt(accountId));
		role = account.getRole();
		college = account.getCollege();
		grade = account.getGrade();
		return SUCCESS;
	}
	
	public String queryMe(){
		account = (Account) getSession().getAttribute("LOGON_ACCOUNT");
		role = (Role) getSession().getAttribute("LOGON_ROLE");
		
		account = accountService.queryById(account.getId());
		role = roleService.queryById(role.getId());
		
		getSession().removeAttribute("LOGON_ACCOUNT");
		getSession().setAttribute("LOGON_ACCOUNT", account);
		
		college = account.getCollege();
		grade = account.getGrade();
		collegeList = collegeService.queryAll();
		gradeList = gradeService.queryAll();
		return SUCCESS;
	}
	
	public String update(){
		
		//可以不用判断 直接将input name 设置我对象.属性
		if(StringUtil.isNotBlank(accountAccno)) account.setAccno(accountAccno);
		if(StringUtil.isNotBlank(accountName)) account.setName(accountName);
		if(StringUtil.isNotBlank(accountSex)) account.setSex(accountSex);
		if(StringUtil.isNotBlank(accountPhone)) account.setPhone(accountPhone);
		if(StringUtil.isNotBlank(accountEmail)) account.setEmail(accountEmail);
//		if((StringUtil.isNotBlank(accountPassword)||StringUtil.isNotBlank(accountPassword2)) && !accountPassword.equals(accountPassword2)) return INPUT;
//		if(StringUtil.isNotBlank(accountPassword)) account.setPassword(MD5.getMD5Password(accountPassword));
		if(StringUtil.isNotBlank(accountSex)) account.setSex(accountSex);
		if(StringUtil.isNotBlank(roleId)) account.setRole(roleService.queryById(Integer.parseInt(roleId)));
		if(StringUtil.isNotBlank(collegeId)) account.setCollege(collegeService.queryById(Integer.parseInt(collegeId)));
		if(StringUtil.isNotBlank(gradeId)) account.setGrade(gradeService.queryById(Integer.parseInt(gradeId)));
		
		if(account.getId()==0){
			if(StringUtil.isBlank(accountPassword)) return INPUT;
			account.setPassword(MD5.getMD5Password(accountPassword));
			//新增账户
			insert(account);
		}else{
			if(StringUtil.isNotBlank(accountPassword)) 
				account.setPassword(MD5.getMD5Password(accountPassword));
			else
				account.setPassword(accountService.queryById(account.getId()).getPassword());
			this.updateAccount(account);
		}
		
		return SUCCESS;
	}
	
	public String infoset(){
		role = (Role) getSession().getAttribute("LOGON_ROLE");
		//角色和账户名不能被修改
		Account a = accountService.queryById(account.getId());
		account.setRole(a.getRole());
		account.setAccno(a.getAccno());
		
		if(StringUtil.isNotBlank(accountSex)) account.setSex(accountSex);
		if(StringUtil.isNotBlank(collegeId)) account.setCollege(collegeService.queryById(Integer.parseInt(collegeId)));
		if(StringUtil.isNotBlank(gradeId)) account.setGrade(gradeService.queryById(Integer.parseInt(gradeId)));
		
		if(StringUtil.isNotBlank(accountPassword)) 
			account.setPassword(MD5.getMD5Password(accountPassword));
		else
			account.setPassword(accountService.queryById(account.getId()).getPassword());
		this.updateAccount(account);
		
		if(role.getId()==2) return "student";
		else return "apply";
	}
	
	public String execute(){
		if(StringUtil.isNotBlank(method)){
			if(method.equals("-1")){
				//删除账户
				if(StringUtil.isNotBlank(ids)){
					String[] arrays = ids.split(",");
					for(int i = 0;i< arrays.length;i++){
						if(!arrays[i].equals("1")&&!arrays[i].equals("2")){//1 2为管理员和学生 内置角色
							account = accountService.queryById(Integer.parseInt(arrays[i]));
							this.delete(account);
						}
					}
				}
			} else if(method.equals("1")){
				if(StringUtil.isNotBlank(ids)){
					//复制账户
					String[] arrays = ids.split(",");
					for(int i=0;i<arrays.length;i++){
						if(!arrays[i].equals("1")&&!arrays[i].equals("2")){//1 2为管理员和学生 内置角色
							account = accountService.queryById(Integer.parseInt(arrays[i]));
							account.setId(0);
							String no="";
							int number =1;
							do{
								no=account.getAccno()+"-复制"+number;
								number++;
								if(number>100) no="你丫有病把这么测试";
							}while(accountService.queryByName(no).size()>0);
							account.setAccno(no);
							account.setName(account.getName()+"_副本");
							this.insert(account);
						}
					}
				}
			}
		}
		return SUCCESS;
	}
	
	public void checkAccountAccno(){
		String result = "false";
		if(StringUtil.isNotBlank(accountAccno)){
			
			try {
				accountAccno = java.net.URLDecoder.decode(accountAccno, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			List<Account> list = accountService.queryByAccno(accountAccno);
			if(list.size()>0){
				for(Account a:list){
					result = "true";
					if(StringUtil.isNotBlank(accountId)&&a.getId()==Integer.parseInt(accountId)) result="false";
				}
			}
		}
		try {
			getResponse().setContentType("text/html;charset=UTF-8");
			getResponse().setCharacterEncoding("UTF-8");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insert(Account a){
		accountService.insert(a);
		List<String> fieldList = new ArrayList<String>();
		Account login_account = (Account) super.getSession().getAttribute("LOGON_ACCOUNT");
		fieldList.add(login_account.getName()+"("+login_account.getAccno()+") 添加账户："+a.getName()+"("+a.getAccno()+")");
		auditService.operator(login_account.getId(), "新增账户", getRequest().getRemoteAddr(), fieldList);
		return a.getId();
	}
	
	public int updateAccount(Account a){
		List<String> fieldList = new ArrayList<String>();
		Account login_account = (Account) super.getSession().getAttribute("LOGON_ACCOUNT");
		fieldList.add(login_account.getName()+"("+login_account.getAccno()+") 修改账户："+a.getName()+"("+a.getAccno()+")");
		
		Account r_a = accountService.queryById(a.getId());
		if(!a.getName().equals(r_a.getName())){
			fieldList.add("姓名:"+r_a.getName()+" -> "+a.getName());
		}
		if(a.getRole().getId()!=r_a.getRole().getId()){
			fieldList.add("角色:"+(r_a.getRole()==null?"无":r_a.getRole().getName())
							+" -> "
							+(a.getRole()==null?"无":a.getRole().getName()));
		}
		
		fieldList.add("学院:"+(r_a.getCollege()==null?"无":r_a.getCollege().getName())
					+" -> "
					+(a.getCollege()==null?"无":a.getCollege().getName()));
		fieldList.add("班级:"+(r_a.getGrade()==null?"无":r_a.getGrade().getName())
						+" -> "
						+(a.getGrade()==null?"无":a.getGrade().getName()));
		
		if(StringUtil.isNotBlank(r_a.getEmail())&&StringUtil.isNotBlank(a.getEmail())&&!a.getEmail().equals(r_a.getEmail())){
			fieldList.add("邮箱:"+r_a.getEmail()+" 邮箱-> "+a.getEmail());
		}
		if(StringUtil.isNotBlank(r_a.getPhone())&&StringUtil.isNotBlank(a.getPhone())&&!a.getPhone().equals(r_a.getPhone())){
			fieldList.add("手机:"+r_a.getPhone()+" -> "+a.getPhone());
		}
		if(StringUtil.isNotBlank(r_a.getQq())&&StringUtil.isNotBlank(a.getQq())&&!a.getQq().equals(r_a.getQq())){
			fieldList.add("QQ:"+r_a.getQq()+" -> "+a.getQq());
		}
		if(!a.getSex().equals(r_a.getSex())){
			fieldList.add("性别:"+r_a.getSex()+" -> "+a.getSex());
		}
		if(!a.getPassword().equals(r_a.getPassword())){
			fieldList.add("密码:已修改");
		}
		auditService.operator(login_account.getId(), "修改账户", getRequest().getRemoteAddr(), fieldList);
		
		int i =accountService.update(a);
		updateDatas(a);
		return i;
	}
	
	public void delete(Account a){
		List<String> fieldList = new ArrayList<String>();
		Account login_account = (Account) super.getSession().getAttribute("LOGON_ACCOUNT");
		fieldList.add(login_account.getName()+"("+login_account.getAccno()+") 删除账户："+a.getName()+"("+a.getAccno()+")");
		auditService.operator(login_account.getId(), "删除账户", getRequest().getRemoteAddr(), fieldList);
		accountService.delete(a);
	}
	
	/***
	 * 更新申请信息数据
	 * @param a
	 */
	public void updateDatas(Account a){
		if(a.getRole().getId()==2){
			Datas d = datasService.queryByAccount(a, "0");
			if(d!=null){
				d.setName(a.getName());
				d.setSex(a.getSex());
				d.setCollege(a.getCollege().getName());
				d.setMajor(a.getGrade().getMajor());
				d.setGrade(a.getGrade().getName());
				datasService.update(d);
			}
		}
	}
	
	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public CollegeService getCollegeService() {
		return collegeService;
	}

	public GradeService getGradeService() {
		return gradeService;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public List<College> getCollegeList() {
		return collegeList;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public void setCollegeList(List<College> collegeList) {
		this.collegeList = collegeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public Role getRole() {
		return role;
	}

	public College getCollege() {
		return college;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public String getRoleId() {
		return roleId;
	}

	public String getCollegeId() {
		return collegeId;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getAccountAccno() {
		return accountAccno;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public String getAccountPassword2() {
		return accountPassword2;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getAccountSex() {
		return accountSex;
	}

	public String getAccountPhone() {
		return accountPhone;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountAccno(String accountAccno) {
		this.accountAccno = accountAccno;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public void setAccountPassword2(String accountPassword2) {
		this.accountPassword2 = accountPassword2;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setAccountSex(String accountSex) {
		this.accountSex = accountSex;
	}

	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getOnlyStudent() {
		return onlyStudent;
	}

	public void setOnlyStudent(String onlyStudent) {
		this.onlyStudent = onlyStudent;
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

	public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public DatasService getDatasService() {
		return datasService;
	}

	public void setDatasService(DatasService datasService) {
		this.datasService = datasService;
	}
	
}
