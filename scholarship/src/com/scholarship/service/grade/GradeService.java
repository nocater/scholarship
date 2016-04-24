package com.scholarship.service.grade;

import java.util.List;
import java.util.Map;

import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;
import com.util.page.Page;
import com.util.page.SearchResult;

/***
 * 年级Service
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public interface GradeService {
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
	public SearchResult<Grade> query(Role role,Map<String,String> map,Page page);
	/***
	 * 查询
	 * @param map
	 * @return
	 */
	public List<Grade> query(Map<String,String> map);
	/***
	 * 查询所有
	 * @return
	 */
	public List<Grade> queryAll();
	/***
	 * 查询(角色)
	 * @param role
	 * @return
	 */
	public List<Grade> queryByRole(Role role);
	/***
	 * 查询(ID)
	 * @param id
	 * @return
	 */
	public Grade queryById(int id);
	/***
	 * 查询(NAME)
	 * @param name
	 * @return
	 */
	public List<Grade> queryByName(String name);
	/***
	 * 查询学院下班级
	 * @param college
	 * @return
	 */
	public List<Grade> queryByCollege(College college);
	/***
	 * 查询(年级)
	 * @param grade
	 * @return
	 */
	public List<Grade> queryByGrade(Grade grade);
	/***
	 * 新增
	 * @param grade
	 * @return
	 */
	public int insert(Grade grade);
	/***
	 * 更新
	 * @param grade
	 */
	public void update(Grade grade);
	/***
	 * 删除
	 * @param grade
	 */
	public void delete(Grade grade);
	/***
	 * 删除
	 * @param id
	 */
	public void deleteById(int id);
	/***
	 * 删除关联
	 * @param grade
	 */
	public void deleteRelation(Grade grade);
}
