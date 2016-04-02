package com.scholarship.dao.college;

import java.util.List;
import java.util.Map;

import com.scholarship.module.college.College;
import com.scholarship.module.role.Role;

public interface CollegeDao {
	public int count(Map<?,?> map);
	public List<College> query(Map<?,?> map,int startRow, int pageSize);
	public List<College> queryAll();
	public College queryById(int id);
	public List<College> queryByName(String name);
	public List<College> queryByRole(Role role);
	public int insert(College college);
	public void update(College college);
	public void delete(College college);
	public void deleteById(int id);
}
