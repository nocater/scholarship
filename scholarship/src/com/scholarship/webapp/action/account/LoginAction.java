package com.scholarship.webapp.action.account;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.scholarship.module.account.Account;
import com.scholarship.module.account.OnlineUser;
import com.scholarship.module.role.Role;
import com.scholarship.service.account.AccountService;
import com.scholarship.service.account.LoginService;
import com.scholarship.service.audit.AuditService;
import com.scholarship.webapp.action.BaseAction;
import com.util.StringUtil;

public class LoginAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LoginService loginService; 
	private AccountService accountService;
	private AuditService auditService;
	private String loginAccno;
	private String loginPassword;
	private String imgCode;
	
	public void checkSingle(){
		List<Account> list = accountService.queryByAccno(loginAccno.trim());
		int userId = 0;
		if(list.size()>0){
			List<OnlineUser> userList = (List<OnlineUser>)super.getServletContext().getAttribute("ONLINE_ACCOUNTLIST");
			
			for(OnlineUser user : userList) {
				if(user.getUserId() == list.get(0).getId() && user.getFlag() == 1) {
					userId = user.getUserId();
					break;
				}
			}
		}
		
		try {
			getResponse().setContentType("text/html;charset=UTF-8");
			getResponse().setCharacterEncoding("UTF-8");
			getResponse().setHeader("Cache-Control", "no-cache");
			if(userId != 0) {
				getResponse().getWriter().write("true");
			}else{
				getResponse().getWriter().write("flase");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.info(e.getMessage());
		}
	}
	
	public String check(){
		HttpSession session = getSession();
		HttpServletRequest request = getRequest();
		if(StringUtil.isBlank(loginAccno)||StringUtil.isBlank(loginPassword)) {
			super.addActionMessage("账号密码不能为空！");
			return INPUT;
		}

		// 得到session中保存的验证码
		String relCode = "";
		Object ob = session.getAttribute("rand");
		if (ob != null) {
			relCode = ob.toString();
		}
		// 校验验证码
//		if (StringUtil.isEmpty(imgCode) || !imgCode.toUpperCase().equalsIgnoreCase(relCode)) {
//			super.addActionMessage("验证码不正确!");
//			return INPUT;
//		}
		
		Account account = loginService.check(loginAccno, loginPassword);
		List<String> fieldList = new ArrayList<String>();
		if(null!=account){
			session.setAttribute("LOGON_ACCOUNT", account);
			session.setAttribute("LOGON_ROLE", account.getRole());
			//审计
			fieldList.add(account.getName() + "("+ account.getAccno() + ")");
			auditService.insertByLoginOperator(true,account.getId(),this.getRequest().getRemoteAddr(),fieldList);
			
			// 单一登录判断 
			synchronized(this) {
				List<OnlineUser> userList = (List<OnlineUser>)super.getServletContext().getAttribute("ONLINE_ACCOUNTLIST");
				boolean flag = false;
				for(OnlineUser user : userList) {
					if(user.getUserId() == account.getId()) {
						if(user.getFlag() == 1)
							user.setFlag(0);
					}
				}
				
				OnlineUser newUser = new OnlineUser();
				newUser.setFlag(1);
				newUser.setUserId(account.getId());
				newUser.setUserName(account.getName());
				newUser.setUserIp(request.getRemoteAddr());
				newUser.setLoginTime(new Date());
				newUser.setSessionId(session.getId());
				userList.add(newUser);
			}
			
			//菜单  非学生默认审批模块
			Role role = account.getRole();
			if(role.getId()!=2) session.setAttribute("MENU", "1");
			
		}else{
			super.addActionMessage(loginService.getActionMessage());
			//审计
			fieldList.add(loginAccno + "登录失败原因:账号或密码错误");
			for(Account a:accountService.queryByAccno(loginAccno)){
				auditService.insertByLoginOperator(false,a.getId(),this.getRequest().getRemoteAddr(),fieldList);
			}
			return INPUT;
		}
		
		return SUCCESS;
	}

	public String logout(){
		HttpSession session = this.getRequest().getSession();
		session.invalidate();
		return SUCCESS;
	}
	
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public String getLoginAccno() {
		return loginAccno;
	}

	public void setLoginAccno(String loginAccno) {
		this.loginAccno = loginAccno;
	}


	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	
	public String getImgCode() {
		return imgCode;
	}

	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}

	public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}
}
