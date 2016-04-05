package com.scholarship.dao.college.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.scholarship.dao.college.CollegeDao;
import com.scholarship.dao.mybatis.BaseDaoMyBatis;
import com.scholarship.module.college.College;
import com.scholarship.module.role.Role;

public class CollegeDaoImpl extends BaseDaoMyBatis implements CollegeDao {
	
	@Override
	public int count(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("college_count", map);
	}

	@Override
	public List<College> query(Map<?, ?> map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		RowBounds r = new RowBounds(startRow, pageSize);
		return super.sqlSession.selectList("college_query", map, r);
	}
	
	@Override
	public List<College> queryAll() {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("college_queryAll");
	}

	@Override
	public College queryById(int id) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("college_queryById",id);
	}

	@Override
	public List<College> queryByName(String name) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("college_queryByName",name);
	}

	@Override
	public int insert(College college) {
		// TODO Auto-generated method stub
		return super.sqlSession.insert("college_insert",college);
	}

	@Override
	public void update(College college) {
		// TODO Auto-generated method stub
		super.sqlSession.update("college_update",college);
	}

	@Override
	public void delete(College college) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("college_delete",college);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("college_deleteById", id);
	}

	@Override
	public List<College> queryByRole(Role role) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("college_queryByRole",role);
	}

	@Override
	public void deleteRelation(College college) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("college_deleteRelation", college);
	}

}
