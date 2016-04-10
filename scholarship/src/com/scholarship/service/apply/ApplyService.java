package com.scholarship.service.apply;

import java.util.List;
import java.util.Map;

import com.scholarship.module.account.Account;
import com.scholarship.module.apply.Apply;
import com.scholarship.module.role.Role;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface ApplyService{
	public int count(Map<?,?> map);
	public SearchResult<Apply> query(Role role,Map<String,String> map,Page page);
	public List<Apply> query(Role role,Apply apply, Integer collegeId, Integer gradeId, Integer statusMax, Integer statusMin);
	public SearchResult<Apply> queryAllYears(Role role,Map<String,String> map,Page page);
	public List<Apply> query(Map<String,String> map);
	public List<Account> queryAccountList(Map<?,?> map);
	public List<Apply> queryByAccount(Account account);
	public Apply queryById(int id);
	public int inert(Apply apply);
	public int update(Apply apply);
	public void delete(Apply apply);
	public void deleteById(int id);
	public void deleteByAccount(Account account);
}
