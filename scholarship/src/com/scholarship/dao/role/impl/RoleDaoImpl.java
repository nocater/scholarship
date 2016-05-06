package com.scholarship.dao.role.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.scholarship.dao.mybatis.BaseDaoMyBatis;
import com.scholarship.dao.role.RoleDao;
import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;

/***
 * 角色DaoImpl
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public class RoleDaoImpl extends BaseDaoMyBatis implements RoleDao{
	/***
	 * 统计
	 */
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
	
	/***
	 * 查询(分页)
	 */
	@Override
	public List<Role> query(Map<?, ?> map,int offset, int limit) {
		// TODO Auto-generated method stub
		RowBounds r = new RowBounds(offset, limit);
		return sqlSession.selectList("role_query",map,r);
	}
	
	/***
	 * 查询所有
	 */
	@Override
	public List<Role> queryAll() {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("role_queryAll");
	}

	/***
	 * 查询角色可分配角色列表
	 */
	@Override
	public List<Role> queryRoleList(Map<?,?> map) {
		// TODO Auto-generated method stub
		return super.sqlSession.selectList("role_queryRoleList",map);
	}
	
	/***
	 * 查询(ID)
	 */
	@Override
	public Role queryById(int id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("role_queryById", id);
	}

	/***
	 * 新增
	 */
	@Override
	public int insert(Role role) {
		// TODO Auto-generated method stub
//		int id = 0;
//		Object obj = sqlSession.insert("role_insert", role);
//		if(obj!=null){
//			id=Integer.parseInt(obj.toString());
//		}
		return sqlSession.insert("role_insert", role);
	}

	/***
	 * 删除
	 */
	@Override
	public void delete(Role role) {
		// TODO Auto-generated method stub
		sqlSession.delete("role_delete", role);
	}

	/***
	 * 删除(ID)
	 */
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		sqlSession.delete("role_deleteById", id);
	}
	
	/***
	 * 查询(name)
	 */
	@Override
	public List<Role> queryByName(String name) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("role_queryByName",name);
	}
	
	/***
	 * 更新
	 */
	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		sqlSession.update("role_update", role);
	}
	
	/***
	 * 查询角色下学院
	 */
	@Override
	public List<College> queryColleges(Role role) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("role_queryColleges", role);
	}
	
	/***
	 * 查询角色下年级
	 */
	@Override
	public List<Grade> queryGrade(Role role) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("role_queryGrades", role);
	}

	/***
	 * 新增关联
	 */
	@Override
	public int insertRelation(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return sqlSession.insert("role_insertRelation", map);
	}
	
	/***
	 * 删除关联
	 */
	@Override
	public void deleteRelation(Role role) {
		// TODO Auto-generated method stub
		sqlSession.delete("role_deleteRelation", role);
	}

	/***
	 * 排序
	 */
	@Override
	public List<Role> queryOrderbyCreate() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("role_queryOrderbyCreate");
	}
	
	/***
	 * 排序
	 */
	@Override
	public List<Role> queryOrderbyUpdate() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("role_queryOrderbyUpdate");
	}

}
