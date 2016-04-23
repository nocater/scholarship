package com.scholarship.dao.college;

import java.util.List;
import java.util.Map;

import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;

/***
 * 学院DAO
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public interface CollegeDao {
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
	public List<College> query(Map<?,?> map,int startRow, int pageSize);
	/***
	 * 查询所有
	 * @return
	 */
	public List<College> queryAll();
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
	 * 查询(role)
	 * @param role
	 * @return
	 */
	public List<College> queryByRole(Role role);
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
	 * 删除(ID)
	 * @param id
	 */
	public void deleteById(int id);
	/***
	 * 删除关联
	 * @param college
	 */
	public void deleteRelation(College college);
}
