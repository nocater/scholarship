package com.scholarship.service.scholarship;

import java.util.List;

import com.scholarship.module.scholarship.Scholarship;

/***
 * 账户Service
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public interface ScholarshipService {
	public List<Scholarship> queryAll();
	public Scholarship queryById(int id);
	public List<String> queryCategories();
	public int insert(Scholarship scholarship);
	public int update(Scholarship scholarship);
	public void delete(Scholarship scholarship);
	public void deleteById(int id);
}
