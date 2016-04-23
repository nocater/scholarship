package com.scholarship.dao.apply.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.scholarship.dao.apply.ApplyDao;
import com.scholarship.dao.mybatis.BaseDaoMyBatis;
import com.scholarship.module.account.Account;
import com.scholarship.module.apply.Apply;

/***
 * 审批DaoImpl
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public class ApplyDaoImpl extends BaseDaoMyBatis implements ApplyDao {
	
	/***
	 * 统计
	 */
	@Override
	public int count(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("apply_count", map);
	}
	
	/***
	 * 查询(分页)
	 */
	@Override
	public List<Apply> query(Map<?, ?> map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		RowBounds r = new RowBounds(startRow, pageSize);
		return super.sqlSession.selectList("apply_query", map, r);
	}
	
	/***
	 * 查询
	 */
	@Override
	public List<Apply> query(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("apply_query", map);
	}
	
	/***
	 * 查询账户列表
	 */
	@Override
	public List<Account> queryAccountList(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("apply_queryAccountList", map);
	}
	
	/***
	 * 查询(ID)
	 */
	@Override
	public Apply queryById(int id) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("apply_queryById", id);
	}
	
	/***
	 * 新增审批
	 */
	@Override
	public int insert(Apply apply) {
		// TODO Auto-generated method stub
		return super.sqlSession.insert("apply_insert", apply);
	}
	
	/***
	 * 更新审批
	 */
	@Override
	public int update(Apply apply) {
		// TODO Auto-generated method stub
		return super.sqlSession.update("apply_update", apply);
	}
	
	/***
	 * 删除
	 */
	@Override
	public void delete(Apply apply) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("apply_delete", apply);
	}
	
	/***
	 * 删除(ID)
	 */
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("apply_deleteById", id);
	}
	
	/***
	 * 删除(Account)
	 */
	@Override
	public void deleteByAccount(Account account) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("deleteByAccount", account);
	}

}
