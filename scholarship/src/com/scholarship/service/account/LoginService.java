package com.scholarship.service.account;

import com.scholarship.module.account.Account;
import com.scholarship.service.BaseService;

public interface LoginService extends BaseService{
	public Account check(String loginName,String password);
	public Account changePassword();
	public String getActionMessage();
}
