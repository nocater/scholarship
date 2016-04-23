package com.scholarship.dao.scholarship.impl;

import java.util.List;

import com.scholarship.dao.mybatis.BaseDaoMyBatis;
import com.scholarship.dao.scholarship.ScholarshipDao;
import com.scholarship.module.scholarship.Scholarship;

/***
 * 奖学金DaoImpl
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public class ScholarshipDaoImpl extends BaseDaoMyBatis implements ScholarshipDao {
	/***
	 * 查询所有
	 */
	@Override
	public List<Scholarship> queryAll() {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("scholarship_queryAll");
	}
	
	/***
	 * 查询(ID)
	 */
	@Override
	public Scholarship querybyId(int id) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("scholarship_queryById", id);
	}
	
	/***
	 * 查询类别
	 */
	@Override
	public List<String> queryCategories() {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("scholarship_queryCategories");
	}
	
	/***
	 * 新增
	 */
	@Override
	public int insert(Scholarship shcolarship) {
		// TODO Auto-generated method stub
		return super.sqlSession.insert("scholarship_insert",shcolarship);
	}

	/***
	 * 更新
	 */
	@Override
	public int update(Scholarship shcolarship) {
		// TODO Auto-generated method stub
		return super.sqlSession.update("scholarship_update", shcolarship);
	}

	/***
	 * 删除
	 */
	@Override
	public void delete(Scholarship shcolarship) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("scholarship_delete",shcolarship);
	}

	/***
	 * 删除(ID)
	 */
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("scholarship_deleteById",id);
	}

}
