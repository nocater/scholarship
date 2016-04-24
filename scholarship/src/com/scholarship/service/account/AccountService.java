package com.scholarship.service.account;

import java.util.List;
import java.util.Map;

import com.scholarship.module.account.Account;
import com.scholarship.module.role.Role;
import com.scholarship.service.BaseService;
import com.util.page.Page;
import com.util.page.SearchResult;

/***
 * 账户Service
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public interface AccountService extends BaseService {
	/***
	 * 统计
	 * @param map
	 * @return
	 */
	public int count(Map<String,String> map);
	/***
	 * 查询(分页)
	 * @param role
	 * @param map
	 * @param page
	 * @return
	 */
	public SearchResult<Account> query(Role role,Map<String,String> map,Page page);
	/***
	 * 查询
	 * @param map
	 * @return
	 */
	public List<Account> query(Map<String,String> map);
	/***
	 * 查询所有
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
	 * @param name
	 * @return
	 */
	public List<Account> queryByName(String name);
	/***
	 * 查询(学号)
	 * @param accno
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
	 * 重置学生密码
	 * @param role
	 * @param pwd
	 * @return
	 */
	public int resetPWD(Role role,String pwd);
	/***
	 * 删除
	 * @param account
	 */
	public void delete(Account account);
	/***
	 * 删除
	 * @param id
	 */
	public void deleteById(int id);
}
