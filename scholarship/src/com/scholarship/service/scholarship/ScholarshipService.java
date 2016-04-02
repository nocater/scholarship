package com.scholarship.service.scholarship;

import java.util.List;

import com.scholarship.module.scholarship.Scholarship;

public interface ScholarshipService {
	public List<Scholarship> queryAll();
	public Scholarship queryById(int id);
	public List<String> queryCategories();
	public int insert(Scholarship scholarship);
	public int update(Scholarship scholarship);
	public void delete(Scholarship scholarship);
	public void deleteById(int id);
}
