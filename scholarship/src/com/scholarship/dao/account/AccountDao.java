package com.scholarship.dao.account;

import java.util.List;
import java.util.Map;

import com.scholarship.module.account.Account;

/***
 * 账户DAO
 * @author chenshuai
 *
 */
public interface AccountDao {
	public int count(Map<?,?> map);
	public List<Account> query(Map<?,?> map, int startRow, int pageSize);
	public List<Account> query(Map<?,?> map);
	public List<Account> queryAll();
	public Account queryById(int id);
	public List<Account> queryByName(String name);
	public List<Account> queryByAccno(String accno);
	public int insert(Account account);
	public int update(Account account);
	public int resetPWD(Map<?,?> map);
	public void delete(Account account);
	public void deleteById(int id);
}
