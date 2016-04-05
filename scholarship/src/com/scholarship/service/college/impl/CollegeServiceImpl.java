package com.scholarship.service.college.impl;

import java.util.List;
import java.util.Map;

import com.scholarship.dao.college.CollegeDao;
import com.scholarship.module.account.Account;
import com.scholarship.module.college.College;
import com.scholarship.module.role.Role;
import com.scholarship.service.college.CollegeService;
import com.scholarship.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

public class CollegeServiceImpl extends BaseServiceImpl implements CollegeService{
	private CollegeDao collegeDao;
	
	@Override
	public int count(Map<String, String> map) {
		// TODO Auto-generated method stub
		return collegeDao.count(map);
	}
	
	@Override
	public SearchResult<College> query(Role role, Map<String, String> map, Page page) {
		// TODO Auto-generated method stub
		//角色处理
		//查询数据
		int rowsCount = collegeDao.count(map);
		page.setTotalCount(rowsCount);
		
		List<College> list = collegeDao.query(map, page.getStartIndex(), page.getPageSize());
		// 处理分页
		SearchResult<College> sr = new SearchResult<College>();
		sr.setList(list);
		sr.setPage(page);
		
		return sr;
	}

	
	@Override
	public List<College> queryAll() {
		// TODO Auto-generated method stub
		return collegeDao.queryAll();
	}
	
	@Override
	public List<College> queryByRole(Role role) {
		// TODO Auto-generated method stub
		if(role!=null&&role.getId()==1)return collegeDao.queryAll();
		return collegeDao.queryByRole(role);
	}
	
	@Override
	public College queryById(int id) {
		// TODO Auto-generated method stub
		return collegeDao.queryById(id);
	}

	@Override
	public List<College> queryByName(String name) {
		// TODO Auto-generated method stub
		return collegeDao.queryByName(name);
	}

	@Override
	public int insert(College college) {
		// TODO Auto-generated method stub
		return collegeDao.insert(college);
	}

	@Override
	public void update(College college) {
		// TODO Auto-generated method stub
		collegeDao.update(college);
	}

	@Override
	public void delete(College college) {
		// TODO Auto-generated method stub
		collegeDao.delete(college);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		collegeDao.deleteById(id);
	}
	
	@Override
	public void deleteRelation(College college) {
		// TODO Auto-generated method stub
		collegeDao.deleteRelation(college);
	}
	
	public CollegeDao getCollegeDao() {
		return collegeDao;
	}

	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}
}
