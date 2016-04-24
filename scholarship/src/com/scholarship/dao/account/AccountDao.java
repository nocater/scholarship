package com.scholarship.dao.account;

import java.util.List;
import java.util.Map;

import com.scholarship.module.account.Account;

/***
 * 账户DAO
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public interface AccountDao {
	/***
	 * 统计
	 * @param map
	 * @return
	 */
	public int count(Map<?,?> map);
	/***
	 * 查询(分页)
	 * @param map
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	public List<Account> query(Map<?,?> map, int startRow, int pageSize);
	/***
	 * 查询
	 * @param map
	 * @return
	 */
	public List<Account> query(Map<?,?> map);
	/***
	 * 查询(所有)
	 * @return
	 */
	public List<Account> queryAll();
	/***
	 * 查询(ID)
	 * @param id
	 * @return
	 */
	public Account queryById(int id);
	/***
	 * 查询(name)
	 * @param id
	 * @return
	 */
	public List<Account> queryByName(String name);
	/***
	 * 查询(学号)
	 * @param id
	 * @return
	 */
	public List<Account> queryByAccno(String accno);
	/***
	 * 新增
	 * @param account
	 * @return
	 */
	public int insert(Account account);
	/***
	 * 更新
	 * @param account
	 * @return
	 */
	public int update(Account account);
	/***
	 * 充值密码
	 * @param map
	 * @return
	 */
	public int resetPWD(Map<?,?> map);
	/***
	 * 删除
	 * @param account
	 */
	public void delete(Account account);
	/***
	 * 删除(ID)
	 * @param id
	 */
	public void deleteById(int id);
}
