package com.scholarship.service.account;

import java.util.List;
import java.util.Map;

import com.scholarship.module.account.Account;
import com.scholarship.module.role.Role;
import com.scholarship.service.BaseService;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface AccountService extends BaseService {
	public int count(Map<String,String> map);
	public SearchResult<Account> query(Role role,Map<String,String> map,Page page);
	public List<Account> queryAll();
	public Account queryById(int id);
	public List<Account> queryByName(String name);
	public List<Account> queryByAccno(String accno);
	public int insert(Account account);
	public int update(Account account);
	public int resetPWD(Role role,String pwd);
	public void delete(Account account);
	public void deleteById(int id);
}
