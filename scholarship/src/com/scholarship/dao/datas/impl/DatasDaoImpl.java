package com.scholarship.dao.datas.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.scholarship.dao.datas.DatasDao;
import com.scholarship.dao.mybatis.BaseDaoMyBatis;
import com.scholarship.module.datas.Datas;

public class DatasDaoImpl extends BaseDaoMyBatis implements DatasDao {

	@Override
	public int count(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("datas_count", map);
	}

	@Override
	public List<Datas> query(Map<?, ?> map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		RowBounds r = new RowBounds(startRow, pageSize);
		return super.sqlSession.selectList("datas_query",map,r);
	}
	
	@Override
	public List<Datas> query(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("datas_query", map);
	}

	@Override
	public Datas queryById(int id) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectOne("datas_queryById", id);
	}
	
	@Override
	public int insert(Datas datas) {
		// TODO Auto-generated method stub
		return super.sqlSession.insert("datas_insert", datas);
	}

	@Override
	public int update(Datas datas) {
		// TODO Auto-generated method stub
		return super.sqlSession.update("datas_update", datas);
	}

	@Override
	public void delete(Datas datas) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("datas_delete", datas);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		super.sqlSession.delete("datas_deleteById", id);
	}

	@Override
	public void chengeType(Map<?,?> map) {
		// TODO Auto-generated method stub
		super.sqlSession.update("datas_chengeType", map);
	}

}
