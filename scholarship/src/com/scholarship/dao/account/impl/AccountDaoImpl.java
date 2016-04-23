package com.scholarship.dao.account.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.scholarship.dao.account.AccountDao;
import com.scholarship.dao.mybatis.BaseDaoMyBatis;
import com.scholarship.module.account.Account;

/***
 * 账户DaoImpl
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public class AccountDaoImpl extends BaseDaoMyBatis implements AccountDao{
	
	/***
	 * 统计
	 */
	@Override
	public int count(Map<?, ?> map) {
		// TODO Auto-generated method stub
		Object ob = null;
		try {
			ob = super.sqlSession.selectOne("account_count",map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int totalRows = 0;
		if (ob != null) {
			totalRows = ((Integer) ob).intValue();
		}
		return totalRows;
	}
	
	/***
	 * 查询(分页)
	 */
	@Override
	public List<Account> query(Map<?, ?> map, int offset, int limit) {
		// TODO Auto-generated method stub
		RowBounds r = new RowBounds(offset, limit);
		return super.sqlSession.selectList("account_query", map, r);
	}
	
	/***
	 * 查询
	 */
	@Override
	public List<Account> query(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("account_query", map);
	}
	
	/***
	 * 查询(所有)
	 */
	@Override
	public List<Account> queryAll() {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("account_queryAll");
	}
	
	/***
	 * 查询(ID)
	 */
	@Override
	public Account queryById(int id) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("account_queryById",id);
	}
	
	/***
	 * 查询(name)
	 */
	@Override
	public List<Account> queryByName(String name) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("account_queryByName", name);
	}
	
	/***
	 * 查询(学号)
	 */
	@Override
	public List<Account> queryByAccno(String accno) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("account_queryByAccno",accno);
	}
	
	/***
	 * 新增
	 */
	@Override
	public int insert(Account account) {
		// TODO Auto-generated method stub
		return super.sqlSession.insert("account_insert", account);
	}
	
	/***
	 * 重置密码
	 */
	@Override
	public int resetPWD(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.update("account_resetStudentPwd",map);
	}
	
	/***
	 * 更新
	 */
	@Override
	public int update(Account account) {
		// TODO Auto-generated method stub
		return super.sqlSession.update("account_update",account);
	}
	
	/***
	 * 删除
	 */
	@Override
	public void delete(Account account) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("account_delete",account);
	}
	
	/***
	 * 删除(ID)
	 */
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("account_deleteById", id);
	}

}
