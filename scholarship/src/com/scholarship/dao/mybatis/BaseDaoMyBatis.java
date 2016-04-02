package com.scholarship.dao.mybatis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.SqlSessionTemplate;

import com.scholarship.dao.BaseDao;


public class BaseDaoMyBatis implements BaseDao{
	protected transient Log log = LogFactory.getLog(getClass());
	public SqlSessionTemplate sqlSession;//spring 注入
	
	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
}
