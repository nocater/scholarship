package com.scholarship.service.scholarship.impl;

import java.util.List;

import com.scholarship.dao.scholarship.ScholarshipDao;
import com.scholarship.module.scholarship.Scholarship;
import com.scholarship.service.impl.BaseServiceImpl;
import com.scholarship.service.scholarship.ScholarshipService;

/***
 * 奖学金ServiceImpl
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public class ScholarshipServiceImpl extends BaseServiceImpl implements ScholarshipService{
	public ScholarshipDao scholarshipDao;
	
	@Override
	public List<Scholarship> queryAll() {
		// TODO Auto-generated method stub
		return scholarshipDao.queryAll();
	}

	@Override
	public Scholarship queryById(int id) {
		// TODO Auto-generated method stub
		return scholarshipDao.querybyId(id);
	}

	@Override
	public List<String> queryCategories() {
		// TODO Auto-generated method stub
		return scholarshipDao.queryCategories();
	}

	@Override
	public int insert(Scholarship scholarship) {
		// TODO Auto-generated method stub
		return scholarshipDao.insert(scholarship);
	}

	@Override
	public int update(Scholarship scholarship) {
		// TODO Auto-generated method stub
		return scholarshipDao.update(scholarship);
	}

	@Override
	public void delete(Scholarship scholarship) {
		// TODO Auto-generated method stub
		scholarshipDao.delete(scholarship);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		scholarshipDao.deleteById(id);
	}

	public ScholarshipDao getScholarshipDao() {
		return scholarshipDao;
	}

	public void setScholarshipDao(ScholarshipDao scholarshipDao) {
		this.scholarshipDao = scholarshipDao;
	}

}
