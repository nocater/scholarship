package com.scholarship.service.college;

import java.util.List;
import java.util.Map;

import com.scholarship.module.account.Account;
import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;
import com.util.page.Page;
import com.util.page.SearchResult;

/***
 * 学院Service
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public interface CollegeService {
	/***
	 * 统计
	 * @param map
	 * @return
	 */
	public int count(Map<String,String> map);
	/***
	 * 插叙(分页)
	 * @param role
	 * @param map
	 * @param page
	 * @return
	 */
	public SearchResult<College> query(Role role,Map<String,String> map,Page page);
	/***
	 * 查询所有
	 * @return
	 */
	public List<College> queryAll();
	/***
	 * 查询(ROLE)
	 * @param role
	 * @return
	 */
	public List<College> queryByRole(Role role);
	/***
	 * 查询(ID)
	 * @param id
	 * @return
	 */
	public College queryById(int id);
	/***
	 * 查询(NAME)
	 * @param name
	 * @return
	 */
	public List<College> queryByName(String name);
	/***
	 * 新增
	 * @param college
	 * @return
	 */
	public int insert(College college);
	/***
	 * 更新
	 * @param college
	 */
	public void update(College college);
	/***
	 * 删除
	 * @param college
	 */
	public void delete(College college);
	/***
	 * 删除
	 * @param id
	 */
	public void deleteById(int id);
	/***
	 * 删除关联
	 * @param college
	 */
	public void deleteRelation(College college);
}
