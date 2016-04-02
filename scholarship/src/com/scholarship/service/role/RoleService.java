package com.scholarship.service.role;

import java.util.List;
import java.util.Map;

import com.scholarship.module.account.Account;
import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;
import com.scholarship.service.BaseService;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface RoleService extends BaseService{
	public int count(Role role);
	public SearchResult<?> query(Role role,String order,Page page);
	public List<Role> queryAll();
	public Role query(Account account);
	public Role queryById(int id);
	public List<Role> queryByName(String name);
	public int insert(Role role);
	public void update(Role role);
	public void delete(Role role);
	public void delete(int id);
	public List<College> queryColleges(Role role);
	public List<Grade> queryGrades(Role role);
	public int insertRelation(Map<?,?> map);
	public void deleteRelation(Role role);
	public List<Role> queryOrderbyCreate();
	public List<Role> queryOrderbyUpdate();
}
