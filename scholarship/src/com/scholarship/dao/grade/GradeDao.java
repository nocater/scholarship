package com.scholarship.dao.grade;

import java.util.List;
import java.util.Map;

import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;

/***
 * 班级DAO
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public interface GradeDao {
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
	public List<Grade> query(Map<?,?> map, int startRow, int pageSize);
	/***
	 * 查询
	 * @param map
	 * @return
	 */
	public List<Grade> query(Map<?,?> map);
	/***
	 * 查询所有
	 * @return
	 */
	public List<Grade> queryAll();
	/***
	 * 查询(role)
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
	 * 查询(name)
	 * @param name
	 * @return
	 */
	public List<Grade> queryByName(String name);
	/***
	 * 查询(college)
	 * @param college
	 * @return
	 */
	public List<Grade> queryByCollege(College college);
	/***
	 * 查询(年级Grade)
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
	 * 删除(ID)
	 * @param id
	 */
	public void deleteById(int id);
	/***
	 * 删除关联
	 * @param grade
	 */
	public void deleteRelation(Grade grade);
}
