package com.scholarship.dao.apply.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.scholarship.dao.apply.ApplyDao;
import com.scholarship.dao.mybatis.BaseDaoMyBatis;
import com.scholarship.module.account.Account;
import com.scholarship.module.apply.Apply;

public class ApplyDaoImpl extends BaseDaoMyBatis implements ApplyDao {

	@Override
	public int count(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("apply_count", map);
	}

	@Override
	public List<Apply> query(Map<?, ?> map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		RowBounds r = new RowBounds(startRow, pageSize);
		return super.sqlSession.selectList("apply_query", map, r);
	}
	

	@Override
	public List<Apply> query(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("apply_query", map);
	}
	
	@Override
	public Apply queryById(int id) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("apply_queryById", id);
	}
	
	@Override
	public int insert(Apply apply) {
		// TODO Auto-generated method stub
		return super.sqlSession.insert("apply_insert", apply);
	}

	@Override
	public int update(Apply apply) {
		// TODO Auto-generated method stub
		return super.sqlSession.update("apply_update", apply);
	}

	@Override
	public void delete(Apply apply) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("apply_delete", apply);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("apply_deleteById", id);
	}

	@Override
	public void deleteByAccount(Account account) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("deleteByAccount", account);
	}

}
