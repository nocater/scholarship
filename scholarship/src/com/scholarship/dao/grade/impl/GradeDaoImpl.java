package com.scholarship.dao.grade.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.scholarship.dao.grade.GradeDao;
import com.scholarship.dao.mybatis.BaseDaoMyBatis;
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
public class GradeDaoImpl extends BaseDaoMyBatis implements GradeDao{
	/***
	 * 统计
	 */
	@Override
	public int count(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("grade_count", map);
	}

	/***
	 * 查询(分页)
	 */
	@Override
	public List<Grade> query(Map<?, ?> map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		RowBounds r = new RowBounds(startRow,pageSize);
		return super.sqlSession.selectList("grade_query", map, r);
	}
	
	/***
	 * 查询
	 */
	@Override
	public List<Grade> query(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("grade_query", map);
	}
	
	/***
	 * 查询所有
	 */
	@Override
	public List<Grade> queryAll() {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("grade_queryAll");
	}
	
	/***
	 * 查询(role)
	 */
	@Override
	public List<Grade> queryByRole(Role role) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("grade_queryByRole",role);
	}
	
	/***
	 * 查询(ID)
	 */
	@Override
	public Grade queryById(int id) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("grade_queryById",id);
	}
	
	/***
	 * 查询(name)
	 */
	@Override
	public List<Grade> queryByName(String name) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("grade_queryByName", name);
	}

	/***
	 * 查询(college)
	 */
	@Override
	public List<Grade> queryByCollege(College college) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("grade_queryByCollege", college);
	}
	
	/***
	 * 查询(年级Grade)
	 */
	@Override
	public List<Grade> queryByGrade(Grade grade) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("grade_queryByGrade", grade);
	}
	
	/***
	 * 新增
	 */
	@Override
	public int insert(Grade grade) {
		// TODO Auto-generated method stub
		return super.sqlSession.insert("grade_insert", grade);
	}
	
	/***
	 * 更新
	 */
	@Override
	public void update(Grade grade) {
		// TODO Auto-generated method stub
		super.sqlSession.update("grade_update", grade);
	}

	/***
	 * 删除
	 */
	@Override
	public void delete(Grade grade) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("grade_delete", grade);
	}

	/***
	 * 删除(ID)
	 */
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("grade_deleteById", id);
	}

	/***
	 * 删除关联
	 */
	@Override
	public void deleteRelation(Grade grade) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("grade_deleteRelation", grade);
	}
}
