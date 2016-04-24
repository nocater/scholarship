package com.scholarship.service.account;

import com.scholarship.module.account.Account;
import com.scholarship.service.BaseService;

/***
 * 账户Service
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public interface LoginService extends BaseService{
	/***
	 * 登录验证
	 * @param loginName
	 * @param password
	 * @return
	 */
	public Account check(String loginName,String password);
	/***
	 * 更改密码
	 * @return
	 */
	public Account changePassword();
	public String getActionMessage();
}
