package com.scholarship.dao.grade;

import java.util.List;
import java.util.Map;

import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;

public interface GradeDao {
	public int count(Map<?,?> map);
	public List<Grade> query(Map<?,?> map, int startRow, int pageSize);
	public List<Grade> query(Map<?,?> map);
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
