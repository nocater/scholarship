package com.scholarship.service.role.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scholarship.dao.role.RoleDao;
import com.scholarship.module.account.Account;
import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;
import com.scholarship.service.impl.BaseServiceImpl;
import com.scholarship.service.role.RoleService;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class RoleServiceImpl extends BaseServiceImpl implements RoleService {
	private RoleDao roleDao;
	
	@Override
	public int count(Role role){
		Map<String, String> map = new HashMap<String, String>();
		if (role != null) {
			map.put("name", role.getName());
		}
		return roleDao.count(map);
	}
	
	@Override
	public SearchResult<Role> query(Role role,String order,Page page) {
		// TODO Auto-generated method stub
		// 处理查询条件
		Map<String, String> map = new HashMap<String, String>();
		if (role != null) {
			map.put("name", role.getName());
		}
		if (StringUtil.isNotBlank(order)) {
			map.put("order", order);
		}
		// 查询数据
		int rowsCount = roleDao.count(map);
		page.setTotalCount(rowsCount);

		List<Role> list = roleDao.query(map, page.getStartIndex(), page
				.getPageSize());

		// 处理分页
		SearchResult<Role> sr = new SearchResult<Role>();
		sr.setList(list);
		sr.setPage(page);

		return sr;
	}
	
	@Override
	public List<Role> queryAll() {
		// TODO Auto-generated method stub
		return roleDao.queryAll();
	}
	
	@Override
	public Role query(Account account) {
		// TODO Auto-generated method stub
		return roleDao.queryById(account.getRole().getId());
	}

	@Override
	public Role queryById(int id) {
		// TODO Auto-generated method stub
		return roleDao.queryById(id);
	}
	
	@Override
	public List<Role> queryByName(String name) {
		// TODO Auto-generated method stub
		return roleDao.queryByName(name);
	}
	
	@Override
	public int insert(Role role) {
		// TODO Auto-generated method stub
		return roleDao.insert(role);
	}

	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		roleDao.update(role);
	}

	@Override
	public void delete(Role role) {
		// TODO Auto-generated method stub
		roleDao.delete(role);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		roleDao.deleteById(id);
	}
	
	@Override
	public List<College> queryColleges(Role role) {
		// TODO Auto-generated method stub
		return roleDao.queryColleges(role);
	}
	
	@Override
	public List<Grade> queryGrades(Role role) {
		// TODO Auto-generated method stub
		return roleDao.queryGrade(role);
	}

	@Override
	public int insertRelation(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return roleDao.insertRelation(map);
	}

	@Override
	public void deleteRelation(Role role) {
		// TODO Auto-generated method stub
		roleDao.deleteRelation(role);
	}
	
	
	
	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List<Role> queryOrderbyCreate() {
		// TODO Auto-generated method stub
		return roleDao.queryOrderbyCreate();
	}

	@Override
	public List<Role> queryOrderbyUpdate() {
		// TODO Auto-generated method stub
		return roleDao.queryOrderbyUpdate();
	}

}
