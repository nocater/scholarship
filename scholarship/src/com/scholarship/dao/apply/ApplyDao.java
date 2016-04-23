package com.scholarship.dao.apply;

import java.util.List;
import java.util.Map;

import com.scholarship.module.account.Account;
import com.scholarship.module.apply.Apply;

/***
 * 审批DAO
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public interface ApplyDao {
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
	public List<Apply> query(Map<?,?> map, int startRow, int pageSize);
	/***
	 * 查询
	 * @param map
	 * @return
	 */
	public List<Apply> query(Map<?,?> map);
	/***
	 * 查询账户列表
	 * @param map
	 * @return
	 */
	public List<Account> queryAccountList(Map<?,?> map);
	/***
	 * 查询(ID)
	 * @param id
	 * @return
	 */
	public Apply queryById(int id);
	/***
	 * 新增
	 * @param apply
	 * @return
	 */
	public int insert(Apply apply);
	/***
	 * 更新
	 * @param apply
	 * @return
	 */
	public int update(Apply apply);
	/***
	 * 删除
	 * @param apply
	 */
	public void delete(Apply apply);
	/***
	 * 删除(ID)
	 * @param id
	 */
	public void deleteById(int id);
	/***
	 * 删除(账户)
	 * @param account
	 */
	public void deleteByAccount(Account account);
}
