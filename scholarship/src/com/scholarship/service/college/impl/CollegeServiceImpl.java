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

/***
 * 学院ServiceImpl
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public class CollegeServiceImpl extends BaseServiceImpl implements CollegeService{
	private CollegeDao collegeDao;
	
	/***
	 * 统计
	 */
	@Override
	public int count(Map<String, String> map) {
		// TODO Auto-generated method stub
		return collegeDao.count(map);
	}
	
	/***
	 * 查询(分页)
	 */
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

	/***
	 * 查询所有
	 */
	@Override
	public List<College> queryAll() {
		// TODO Auto-generated method stub
		return collegeDao.queryAll();
	}
	
	/***
	 * 查询(ROLE)
	 */
	@Override
	public List<College> queryByRole(Role role) {
		// TODO Auto-generated method stub
		if(role!=null&&role.getId()==1)return collegeDao.queryAll();
		return collegeDao.queryByRole(role);
	}
	
	/***
	 * 查询(ID)
	 */
	@Override
	public College queryById(int id) {
		// TODO Auto-generated method stub
		return collegeDao.queryById(id);
	}

	/***
	 * 查询(ID)
	 */
	@Override
	public List<College> queryByName(String name) {
		// TODO Auto-generated method stub
		return collegeDao.queryByName(name);
	}

	/***
	 * 新增
	 */
	@Override
	public int insert(College college) {
		// TODO Auto-generated method stub
		return collegeDao.insert(college);
	}

	/***
	 * 更新
	 */
	@Override
	public void update(College college) {
		// TODO Auto-generated method stub
		collegeDao.update(college);
	}

	/***
	 * 删除
	 */
	@Override
	public void delete(College college) {
		// TODO Auto-generated method stub
		collegeDao.delete(college);
	}

	/***
	 * 删除(ID)
	 */
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		collegeDao.deleteById(id);
	}
	
	/***
	 * 删除关联
	 */
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
