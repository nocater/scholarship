package com.scholarship.service.grade;

import java.util.List;
import java.util.Map;

import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface GradeService {
	public int count(Map<String,String> map);
	public SearchResult<Grade> query(Role role,Map<String,String> map,Page page);
	public List<Grade> query(Map<String,String> map);
	public List<Grade> queryAll();
	public List<Grade> queryByRole(Role role);
	public Grade queryById(int id);
	public List<Grade> queryByName(String name);
	public List<Grade> queryByCollege(College college);
	public List<Grade> queryByGrade(Grade grade);
	public int insert(Grade grade);
	public void update(Grade grade);
	public void delete(Grade grade);
	public void deleteById(int id);
	public void deleteRelation(Grade grade);
}
