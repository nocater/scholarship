package com.scholarship.service.account.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scholarship.dao.account.AccountDao;
import com.scholarship.module.account.Account;
import com.scholarship.module.role.Role;
import com.scholarship.service.account.AccountService;
import com.scholarship.service.impl.BaseServiceImpl;
import com.util.MD5;
import com.util.page.Page;
import com.util.page.SearchResult;

/***
 * 账户ServiceImpl
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public class AccountServiceImpl extends BaseServiceImpl implements AccountService {
	
	private AccountDao accountDao;
	
	/***
	 * 统计
	 */
	@Override
	public int count(Map<String, String> map) {
		// TODO Auto-generated method stub
		return accountDao.count(map);
	}

	/***
	 * 查询(分页)
	 */
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
	
	/***
	 * 查询
	 */
	@Override
	public List<Account> query(Map<String, String> map) {
		// TODO Auto-generated method stub
		return accountDao.query(map);
	}
	
	/***
	 * 查询所有
	 */
	@Override
	public List<Account> queryAll() {
		// TODO Auto-generated method stub
		return accountDao.queryAll();
	}

	/***
	 * 查询(ID)
	 */
	@Override
	public Account queryById(int id) {
		// TODO Auto-generated method stub
		return accountDao.queryById(id);
	}

	/***
	 * 查询(name)
	 */
	@Override
	public List<Account> queryByName(String name) {
		// TODO Auto-generated method stub
		return accountDao.queryByName(name);
	}

	/***
	 * 查询(学号)
	 */
	@Override
	public List<Account> queryByAccno(String accno) {
		// TODO Auto-generated method stub
		return accountDao.queryByAccno(accno);
	}
	
	/***
	 * 新增
	 */
	@Override
	public int insert(Account account) {
		// TODO Auto-generated method stub
		return accountDao.insert(account);
	}

	/***
	 * 更新
	 */
	@Override
	public int update(Account account) {
		// TODO Auto-generated method stub
		return accountDao.update(account);
	}

	/***
	 * 重置密码
	 */
	@Override
	public int resetPWD(Role role, String pwd) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		if(role.getId()!=1){
			map.put("roleId", String.valueOf(role.getId()));
		}
		map.put("password", MD5.getMD5Password(pwd));
		return accountDao.resetPWD(map);
	}

	/***
	 * 删除
	 */
	@Override
	public void delete(Account account) {
		// TODO Auto-generated method stub
		accountDao.delete(account);
	}

	/***
	 * 删除(ID)
	 */
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
