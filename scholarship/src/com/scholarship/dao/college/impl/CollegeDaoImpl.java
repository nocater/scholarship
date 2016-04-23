package com.scholarship.dao.college.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.scholarship.dao.college.CollegeDao;
import com.scholarship.dao.mybatis.BaseDaoMyBatis;
import com.scholarship.module.college.College;
import com.scholarship.module.role.Role;

/***
 * 学院DaoImpl
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public class CollegeDaoImpl extends BaseDaoMyBatis implements CollegeDao {
	
	/***
	 * 统计
	 */
	@Override
	public int count(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("college_count", map);
	}
	
	/***
	 * 查询
	 */
	@Override
	public List<College> query(Map<?, ?> map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		RowBounds r = new RowBounds(startRow, pageSize);
		return super.sqlSession.selectList("college_query", map, r);
	}
	
	/***
	 * 查询所有
	 */
	@Override
	public List<College> queryAll() {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("college_queryAll");
	}
	
	/***
	 * 查询(ID)
	 */
	@Override
	public College queryById(int id) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("college_queryById",id);
	}
	
	/***
	 * 查询(NAME)
	 */
	@Override
	public List<College> queryByName(String name) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("college_queryByName",name);
	}
	
	/***
	 * 新增
	 */
	@Override
	public int insert(College college) {
		// TODO Auto-generated method stub
		return super.sqlSession.insert("college_insert",college);
	}
	
	/***
	 * 更新
	 */
	@Override
	public void update(College college) {
		// TODO Auto-generated method stub
		super.sqlSession.update("college_update",college);
	}
	
	/***
	 * 删除
	 */
	@Override
	public void delete(College college) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("college_delete",college);
	}
	
	/***
	 * 删除(ID)
	 */
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("college_deleteById", id);
	}

	/***
	 * 查询(ROLE)
	 */
	@Override
	public List<College> queryByRole(Role role) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("college_queryByRole",role);
	}
	
	/***
	 * 删除关联
	 */
	@Override
	public void deleteRelation(College college) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("college_deleteRelation", college);
	}

}
