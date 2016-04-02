package com.scholarship.dao.audit.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.scholarship.dao.audit.AuditDao;
import com.scholarship.dao.mybatis.BaseDaoMyBatis;
import com.scholarship.module.audit.Audit;

public class AuditDaoImpl extends BaseDaoMyBatis implements AuditDao {

	@Override
	public int count(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("audit_count", map);
	}

	@Override
	public int insert(Audit audit) {
		// TODO Auto-generated method stub
		return this.sqlSession.insert("audit_insert", audit);
	}

	@Override
	public List<Audit> query(Map<?,?> map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		RowBounds r = new RowBounds(startRow, pageSize);
		return this.sqlSession.selectList("audit_query", map, r);
	}

	@Override
	public Audit queryById(int id) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("audit_queryById", id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return this.sqlSession.delete("audit_deleteAll");
	}

	@Override
	public void updateAccountName(Map<?, ?> map) {
		// TODO Auto-generated method stub
		this.sqlSession.update("audit_updateAccountName", map);
	}

}
