package com.scholarship.dao.role.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.scholarship.dao.mybatis.BaseDaoMyBatis;
import com.scholarship.dao.role.RoleDao;
import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;

public class RoleDaoImpl extends BaseDaoMyBatis implements RoleDao{
	
	@Override
	public int count(Map map) {
		// TODO Auto-generated method stub
		Object ob = null;
		try{
			ob = super.sqlSession.selectOne("role_count",map);
		}catch(Exception e){
			e.printStackTrace();
		}
		int totalRows = 0;
		if(ob != null){
			totalRows = ((Integer)ob).intValue();
		}
		return totalRows;
	}
	
	@Override
	public List<Role> query(Map<?, ?> map,int offset, int limit) {
		// TODO Auto-generated method stub
		RowBounds r = new RowBounds(offset, limit);
		return sqlSession.selectList("role_query",map,r);
	}
	
	@Override
	public List<Role> queryAll() {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("role_queryAll");
	}

	@Override
	public Role queryById(int id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("role_queryById", id);
	}

	@Override
	public int insert(Role role) {
		// TODO Auto-generated method stub
		int id = 0;
		Object obj = sqlSession.insert("role_insert", role);
		if(obj!=null){
			id=Integer.parseInt(obj.toString());
		}
		return id;
	}

	@Override
	public void delete(Role role) {
		// TODO Auto-generated method stub
		sqlSession.delete("role_delete", role);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		sqlSession.delete("role_deleteById", id);
	}
	
	@Override
	public List<Role> queryByName(String name) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("role_queryByName",name);
	}
	
	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		sqlSession.update("role_update", role);
	}

	@Override
	public List<College> queryColleges(Role role) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("role_queryColleges", role);
	}
	
	@Override
	public List<Grade> queryGrade(Role role) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("role_queryGrades", role);
	}

	@Override
	public int insertRelation(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return sqlSession.insert("role_insertRelation", map);
	}

	@Override
	public void deleteRelation(Role role) {
		// TODO Auto-generated method stub
		sqlSession.delete("role_deleteRelation", role);
	}


	@Override
	public List<Role> queryOrderbyCreate() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("role_queryOrderbyCreate");
	}

	@Override
	public List<Role> queryOrderbyUpdate() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("role_queryOrderbyUpdate");
	}

}
