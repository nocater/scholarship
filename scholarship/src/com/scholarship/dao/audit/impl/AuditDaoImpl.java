package com.scholarship.dao.audit.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.scholarship.dao.audit.AuditDao;
import com.scholarship.dao.mybatis.BaseDaoMyBatis;
import com.scholarship.module.audit.Audit;

/***
 * 审计DaoImpl
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public class AuditDaoImpl extends BaseDaoMyBatis implements AuditDao {
	/***
	 * 统计
	 */
	@Override
	public int count(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("audit_count", map);
	}
	
	/***
	 * 新增
	 */
	@Override
	public int insert(Audit audit) {
		// TODO Auto-generated method stub
		return this.sqlSession.insert("audit_insert", audit);
	}
	
	/***
	 * 查询
	 */
	@Override
	public List<Audit> query(Map<?,?> map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		RowBounds r = new RowBounds(startRow, pageSize);
		return this.sqlSession.selectList("audit_query", map, r);
	}
	
	/***
	 * 查询(ID)
	 */
	@Override
	public Audit queryById(int id) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne("audit_queryById", id);
	}
	
	/***
	 * 删除所有
	 */
	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return this.sqlSession.delete("audit_deleteAll");
	}
	
	/***
	 * 更新审计中账户名字
	 */
	@Override
	public void updateAccountName(Map<?, ?> map) {
		// TODO Auto-generated method stub
		this.sqlSession.update("audit_updateAccountName", map);
	}

}
