package com.scholarship.dao.account.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.scholarship.dao.account.AccountDao;
import com.scholarship.dao.mybatis.BaseDaoMyBatis;
import com.scholarship.module.account.Account;


public class AccountDaoImpl extends BaseDaoMyBatis implements AccountDao{

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

	@Override
	public List<Account> query(Map<?, ?> map, int offset, int limit) {
		// TODO Auto-generated method stub
		RowBounds r = new RowBounds(offset, limit);
		return super.sqlSession.selectList("account_query", map, r);
	}

	@Override
	public List<Account> query(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("account_query", map);
	}

	@Override
	public List<Account> queryAll() {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("account_queryAll");
	}

	@Override
	public Account queryById(int id) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("account_queryById",id);
	}

	@Override
	public List<Account> queryByName(String name) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("account_queryByName", name);
	}

	@Override
	public List<Account> queryByAccno(String accno) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("account_queryByAccno",accno);
	}

	@Override
	public int insert(Account account) {
		// TODO Auto-generated method stub
		return super.sqlSession.insert("account_insert", account);
	}
	
	@Override
	public int resetPWD(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.update("account_resetStudentPwd",map);
	}
	
	@Override
	public int update(Account account) {
		// TODO Auto-generated method stub
		return super.sqlSession.update("account_update",account);
	}

	@Override
	public void delete(Account account) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("account_delete",account);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("account_deleteById", id);
	}

}
