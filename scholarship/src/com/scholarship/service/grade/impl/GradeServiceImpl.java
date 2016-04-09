package com.scholarship.service.grade.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scholarship.dao.grade.GradeDao;
import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;
import com.scholarship.service.grade.GradeService;
import com.scholarship.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

public class GradeServiceImpl extends BaseServiceImpl implements GradeService {
	private GradeDao gradeDao;
	
	@Override
	public int count(Map<String, String> map) {
		// TODO Auto-generated method stub
		return gradeDao.count(map);
	}

	@Override
	public SearchResult<Grade> query(Role role, Map<String, String> map, Page page) {
		// TODO Auto-generated method stub
		//角色处理
		if(role.getId()!=1)map.put("roleId", String.valueOf(role.getId()));
		//查询数据
		int rowsCount = gradeDao.count(map);
		page.setTotalCount(rowsCount);
		
		List<Grade> list = gradeDao.query(map, page.getStartIndex(), page.getPageSize());
		// 处理分页
		SearchResult<Grade> sr = new SearchResult<Grade>();
		sr.setList(list);
		sr.setPage(page);
		
		return sr;
	}
	
	@Override
	public List<Grade> query(Map<String, String> map) {
		// TODO Auto-generated method stub
		return gradeDao.query(map);
	}
	
	@Override
	public List<Grade> queryAll() {
		// TODO Auto-generated method stub
		return gradeDao.queryAll();
	}

	@Override
	public List<Grade> queryByRole(Role role) {
		// TODO Auto-generated method stub
		if(role!=null&&role.getId()==1) {
			//只显示激活的班级
			Map<String,String> map = new HashMap<String,String>();
			map.put("status", "1");
			return gradeDao.query(map);
		}
		return gradeDao.queryByRole(role);
	}
	
	@Override
	public Grade queryById(int id) {
		// TODO Auto-generated method stub
		return gradeDao.queryById(id);
	}

	@Override
	public List<Grade> queryByName(String name) {
		// TODO Auto-generated method stub
		return gradeDao.queryByName(name);
	}

	@Override
	public List<Grade> queryByCollege(College college) {
		// TODO Auto-generated method stub
		return gradeDao.queryByCollege(college);
	}

	@Override
	public List<Grade> queryByGrade(Grade grade) {
		// TODO Auto-generated method stub
		return gradeDao.queryByGrade(grade);
	}

	@Override
	public int insert(Grade grade) {
		// TODO Auto-generated method stub
		return gradeDao.insert(grade);
	}

	@Override
	public void update(Grade grade) {
		// TODO Auto-generated method stub
		gradeDao.update(grade);
	}

	@Override
	public void delete(Grade grade) {
		// TODO Auto-generated method stub
		gradeDao.delete(grade);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		gradeDao.deleteById(id);
	}
	
	@Override
	public void deleteRelation(Grade grade) {
		// TODO Auto-generated method stub
		gradeDao.deleteRelation(grade);
	}
	
	public GradeDao getGradeDao() {
		return gradeDao;
	}

	public void setGradeDao(GradeDao gradeDao) {
		this.gradeDao = gradeDao;
	}
}
