package com.scholarship.service.role;

import java.util.List;
import java.util.Map;

import com.scholarship.module.account.Account;
import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;
import com.scholarship.service.BaseService;
import com.util.page.Page;
import com.util.page.SearchResult;

/***
 * 角色Service
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public interface RoleService extends BaseService{
	/***
	 * 统计
	 * @param role
	 * @return
	 */
	public int count(Role role);
	/***
	 * 查询(分页)
	 * @param role
	 * @param order
	 * @param page
	 * @return
	 */
	public SearchResult<?> query(Role role,String order,Page page);
	/***
	 * 查询所有
	 * @return
	 */
	public List<Role> queryAll();
	/***
	 * 查询
	 * @param account
	 * @return
	 */
	public Role query(Account account);
	/***
	 * 查询(ID)
	 * @param id
	 * @return
	 */
	public Role queryById(int id);
	/***
	 * 查询(NAME)
	 * @param name
	 * @return
	 */
	public List<Role> queryByName(String name);
	/***
	 * 新增
	 * @param role
	 * @return
	 */
	public int insert(Role role);
	/***
	 * 更新
	 * @param role
	 */
	public void update(Role role);
	/***
	 * 删除
	 * @param role
	 */
	public void delete(Role role);
	/***
	 * 删除
	 * @param id
	 */
	public void delete(int id);
	/***
	 * 查询角色下的学院列表
	 * @param role
	 * @return
	 */
	public List<College> queryColleges(Role role);
	/***
	 * 查询角色下的班级
	 * @param role
	 * @return
	 */
	public List<Grade> queryGrades(Role role);
	/***
	 * 新增关联
	 * @param map
	 * @return
	 */
	public int insertRelation(Map<?,?> map);
	/***
	 * 删除关联
	 * @param role
	 */
	public void deleteRelation(Role role);
	/***
	 * 排序查询
	 * @return
	 */
	public List<Role> queryOrderbyCreate();
	/***
	 * 排序查询
	 * @return
	 */
	public List<Role> queryOrderbyUpdate();
}
