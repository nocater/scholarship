package com.scholarship.dao.scholarship.impl;

import java.util.List;

import com.scholarship.dao.mybatis.BaseDaoMyBatis;
import com.scholarship.dao.scholarship.ScholarshipDao;
import com.scholarship.module.scholarship.Scholarship;

public class ScholarshipDaoImpl extends BaseDaoMyBatis implements ScholarshipDao {

	@Override
	public List<Scholarship> queryAll() {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("scholarship_queryAll");
	}

	@Override
	public Scholarship querybyId(int id) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("scholarship_queryById", id);
	}

	@Override
	public List<String> queryCategories() {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("scholarship_queryCategories");
	}
	
	@Override
	public int insert(Scholarship shcolarship) {
		// TODO Auto-generated method stub
		return super.sqlSession.insert("scholarship_insert",shcolarship);
	}

	@Override
	public int update(Scholarship shcolarship) {
		// TODO Auto-generated method stub
		return super.sqlSession.update("scholarship_update", shcolarship);
	}

	@Override
	public void delete(Scholarship shcolarship) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("scholarship_delete",shcolarship);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("scholarship_deleteById",id);
	}

}
