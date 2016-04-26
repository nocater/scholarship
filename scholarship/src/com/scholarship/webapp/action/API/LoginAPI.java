package com.scholarship.webapp.action.API;

import com.scholarship.service.account.AccountService;
import com.scholarship.service.account.LoginService;
import com.scholarship.service.audit.AuditService;
import com.scholarship.webapp.action.BaseAction;

public class LoginAPI extends BaseAction {

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
	
	public void login(){
		
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public AuditService getAuditService() {
		return auditService;
	}

	public String getLoginAccno() {
		return loginAccno;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public String getImgCode() {
		return imgCode;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public void setLoginAccno(String loginAccno) {
		this.loginAccno = loginAccno;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}
}
