package com.scholarship.dao.grade.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.scholarship.dao.grade.GradeDao;
import com.scholarship.dao.mybatis.BaseDaoMyBatis;
import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;

public class GradeDaoImpl extends BaseDaoMyBatis implements GradeDao{
	
	@Override
	public int count(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("grade_count", map);
	}

	@Override
	public List<Grade> query(Map<?, ?> map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		RowBounds r = new RowBounds(startRow,pageSize);
		return super.sqlSession.selectList("grade_query", map, r);
	}
	
	@Override
	public List<Grade> query(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("grade_query", map);
	}
	
	@Override
	public List<Grade> queryAll() {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("grade_queryAll");
	}
	
	@Override
	public List<Grade> queryByRole(Role role) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("grade_queryByRole",role);
	}
	
	@Override
	public Grade queryById(int id) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("grade_queryById",id);
	}

	@Override
	public List<Grade> queryByName(String name) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("grade_queryByName", name);
	}

	@Override
	public List<Grade> queryByCollege(College college) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("grade_queryByCollege", college);
	}
	
	@Override
	public List<Grade> queryByGrade(Grade grade) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("grade_queryByGrade", grade);
	}
	
	@Override
	public int insert(Grade grade) {
		// TODO Auto-generated method stub
		return super.sqlSession.insert("grade_insert", grade);
	}

	@Override
	public void update(Grade grade) {
		// TODO Auto-generated method stub
		super.sqlSession.update("grade_update", grade);
	}

	@Override
	public void delete(Grade grade) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("grade_delete", grade);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("grade_deleteById", id);
	}

	@Override
	public void deleteRelation(Grade grade) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("grade_deleteRelation", grade);
	}
}
