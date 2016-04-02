package com.scholarship.service.account.impl;

import java.util.List;

import com.scholarship.dao.account.AccountDao;
import com.scholarship.module.account.Account;
import com.scholarship.service.account.LoginService;
import com.scholarship.service.impl.BaseServiceImpl;
import com.util.MD5;

public class LoginServiceImpl extends BaseServiceImpl implements LoginService {
	
	private AccountDao accountDao;
	private String actionMessage;
	@Override
	public Account check(String loginAccno,String password) {
		// TODO Auto-generated method stub
		Account account = null;
		loginAccno = loginAccno.trim();
		actionMessage="";
		List<Account> list = accountDao.queryByAccno(loginAccno);
		if(list.size()==0)actionMessage="该用户不存在";
		else{
			account = list.get(0);
			if(!MD5.getMD5Password(password).equals(account.getPassword())){
				actionMessage="账户密码错误";
				return null;
			}
		}
		return account;
	}

	@Override
	public Account changePassword() {
		// TODO Auto-generated method stub
		return null;
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}

}
