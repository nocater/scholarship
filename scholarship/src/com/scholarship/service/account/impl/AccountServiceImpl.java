package com.scholarship.service.account.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.scholarship.dao.account.AccountDao;
import com.scholarship.module.account.Account;
import com.scholarship.module.role.Role;
import com.scholarship.service.account.AccountService;
import com.scholarship.service.impl.BaseServiceImpl;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class AccountServiceImpl extends BaseServiceImpl implements AccountService {
	
	private AccountDao accountDao;
	
	@Override
	public int count(Map<String, String> map) {
		// TODO Auto-generated method stub
		return accountDao.count(map);
	}
	

	@Override
	public SearchResult<Account> query(Role role, Map<String, String> map, Page page) {
		// TODO Auto-generated method stub
		
		//角色分配账户
		if(role!=null&&role.getId()!=1){//过滤管理员
			map.put("roleId", Integer.toString(role.getId()));
		}
		if(role!=null&&role.getId()==2)return null;//非法访问
		
//		Set<Entry<String, String>> it = map.entrySet();
//		Iterator<Entry<String, String>> i = it.iterator();
//		while(i.hasNext()){
//			Entry<String, String> s = i.next();
//			System.out.println(s.getKey()+"/"+s.getValue());
//		}
		
		//查询数据
		int rowsCount = accountDao.count(map);
		page.setTotalCount(rowsCount);
		
		List<Account> list = accountDao.query(map, page.getStartIndex(), page.getPageSize());
		// 处理分页
		SearchResult<Account> sr = new SearchResult<Account>();
		sr.setList(list);
		sr.setPage(page);
		
		return sr;
	}
	
	@Override
	public List<Account> queryAll() {
		// TODO Auto-generated method stub
		return accountDao.queryAll();
	}

	@Override
	public Account queryById(int id) {
		// TODO Auto-generated method stub
		return accountDao.queryById(id);
	}

	@Override
	public List<Account> queryByName(String name) {
		// TODO Auto-generated method stub
		return accountDao.queryByName(name);
	}

	@Override
	public List<Account> queryByAccno(String accno) {
		// TODO Auto-generated method stub
		return accountDao.queryByAccno(accno);
	}
	
	@Override
	public int insert(Account account) {
		// TODO Auto-generated method stub
		return accountDao.insert(account);
	}

	@Override
	public int update(Account account) {
		// TODO Auto-generated method stub
		return accountDao.update(account);
	}

	@Override
	public void delete(Account account) {
		// TODO Auto-generated method stub
		accountDao.delete(account);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		accountDao.deleteById(id);
	}

	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

}
