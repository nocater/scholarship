package com.scholarship.service.college;

import java.util.List;
import java.util.Map;

import com.scholarship.module.account.Account;
import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface CollegeService {
	public int count(Map<String,String> map);
	public SearchResult<College> query(Role role,Map<String,String> map,Page page);
	public List<College> queryAll();
	public List<College> queryByRole(Role role);
	public College queryById(int id);
	public List<College> queryByName(String name);
	public int insert(College college);
	public void update(College college);
	public void delete(College college);
	public void deleteById(int id);
	public void deleteRelation(College college);
}
