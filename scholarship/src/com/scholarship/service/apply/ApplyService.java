package com.scholarship.service.apply;

import java.util.List;
import java.util.Map;

import com.scholarship.module.account.Account;
import com.scholarship.module.apply.Apply;
import com.scholarship.module.role.Role;
import com.util.page.Page;
import com.util.page.SearchResult;

/***
 * 审批Service
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public interface ApplyService{
	/***
	 * 统计
	 * @param map
	 * @return
	 */
	public int count(Map<?,?> map);
	/***
	 * 查询(分页)
	 * @param role
	 * @param map
	 * @param page
	 * @return
	 */
	public SearchResult<Apply> query(Role role,Map<String,String> map,Page page);
	/***
	 * 查询(角色、审批参数，学院、班级、状态范围值)
	 * @param role
	 * @param apply
	 * @param collegeId
	 * @param gradeId
	 * @param statusMax
	 * @param statusMin
	 * @return
	 */
	public List<Apply> query(Role role,Apply apply, Integer collegeId, Integer gradeId, Integer statusMax, Integer statusMin);
	/***
	 * 查询本年未处理(分页)(角色、审批参数，学院、班级、状态范围值)
	 * @param role
	 * @param apply
	 * @param collegeId
	 * @param gradeId
	 * @param statusMax
	 * @param statusMin
	 * @return
	 */
	public SearchResult<Apply> queryCurrent(Page page, Role role,Apply apply, Integer collegeId, Integer gradeId, Integer statusMax, Integer statusMin);
	
	/***
	 * 查询所有审批记录(分页)
	 * @param role
	 * @param map
	 * @param page
	 * @return
	 */
	public SearchResult<Apply> queryAllYears(Role role,Map<String,String> map,Page page);
	/***
	 * 查询
	 * @param map
	 * @return
	 */
	public List<Apply> query(Map<String,String> map);
	/***
	 * 查询账户列表
	 * @param map
	 * @return
	 */
	public List<Account> queryAccountList(Map<?,?> map);
	/***
	 * 查询(账户)
	 * @param account
	 * @return
	 */
	public List<Apply> queryByAccount(Account account);
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
	public int inert(Apply apply);
	/***
	 * 更新
	 * @param apply
	 * @return
	 */
	public int update(Apply apply);
	/***
	 * 
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
