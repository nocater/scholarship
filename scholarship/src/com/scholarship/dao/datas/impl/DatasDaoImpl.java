package com.scholarship.dao.datas.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.scholarship.dao.datas.DatasDao;
import com.scholarship.dao.mybatis.BaseDaoMyBatis;
import com.scholarship.module.account.Account;
import com.scholarship.module.datas.Datas;

/***
 * 信息DaoImpl
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public class DatasDaoImpl extends BaseDaoMyBatis implements DatasDao {
	/***
	 * 统计
	 */
	@Override
	public int count(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("datas_count", map);
	}
	
	/***
	 * 查询
	 */
	@Override
	public List<Datas> query(Map<?, ?> map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		RowBounds r = new RowBounds(startRow, pageSize);
		return super.sqlSession.selectList("datas_query",map,r);
	}
	
	/***
	 * 查询
	 */
	@Override
	public List<Datas> query(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("datas_query", map);
	}
	
	/***
	 * 查询(ID)
	 */
	@Override
	public Datas queryById(int id) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("datas_queryById", id);
	}
	
	/***
	 * 新增
	 */
	@Override
	public int insert(Datas datas) {
		// TODO Auto-generated method stub
		return super.sqlSession.insert("datas_insert", datas);
	}
	
	/***
	 * 更新
	 */
	@Override
	public int update(Datas datas) {
		// TODO Auto-generated method stub
		return super.sqlSession.update("datas_update", datas);
	}
	
	/***
	 * 删除
	 */
	@Override
	public void delete(Datas datas) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("datas_delete", datas);
	}
	
	/***
	 * 删除(ID)
	 */
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("datas_deleteById", id);
	}

	/***
	 * 更改状态
	 */
	@Override
	public void chengeType(Map<?,?> map) {
		// TODO Auto-generated method stub
		super.sqlSession.update("datas_chengeType", map);
	}

	/***
	 * 删除(账户)
	 */
	@Override
	public void deleteByAccount(Account account) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("datas_deleteByAccount", account);
	}

}
