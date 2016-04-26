package com.scholarship.service.role.impl;

import java.util.ArrayList;
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

/***
 * 账户ServiceImpl
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public class RoleServiceImpl extends BaseServiceImpl implements RoleService {
	private RoleDao roleDao;
	
	/***
	 * 统计
	 */
	@Override
	public int count(Role role){
		Map<String, String> map = new HashMap<String, String>();
		if (role != null) {
			map.put("name", role.getName());
		}
		return roleDao.count(map);
	}
	
	/***
	 * 查询(分页)
	 */
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
	
	/***
	 * 查询所有
	 */
	@Override
	public List<Role> queryAll() {
		// TODO Auto-generated method stub
		return roleDao.queryAll();
	}
	
	/***
	 * 查询所有
	 */
	@Override
	public List<Role> queryRoleList(Role role) {
		// TODO Auto-generated method stub
		List<Role> list = new ArrayList<Role>();
		if(role.getId()==1){
			return roleDao.queryAll();
		}else if(role.getId()==2){
			return null;
		}else if(role.getCollegeList()==null|role.getCollegeList().size()==0){
			list.add(roleDao.queryById(2));
			return list;//
		}else if(role.getCollegeList()!=null&&role.getCollegeList().size()>0){
			list.add(roleDao.queryById(2));
			Map<String,String> map = new HashMap<>();
			for(College c : role.getCollegeList()){
				map.put("collegeId", String.valueOf(c.getId()));
				List<Role> l = new ArrayList<Role>();
				l = roleDao.queryRoleList(map);
				for(Role r:l){
					list.add(r);
				}
			}
		}
		return list;
	}
	
	/***
	 * 查询
	 */
	@Override
	public Role query(Account account) {
		// TODO Auto-generated method stub
		return roleDao.queryById(account.getRole().getId());
	}

	/***
	 * 查询(ID)
	 */
	@Override
	public Role queryById(int id) {
		// TODO Auto-generated method stub
		return roleDao.queryById(id);
	}
	
	/***
	 * 查询(NAME)
	 */
	@Override
	public List<Role> queryByName(String name) {
		// TODO Auto-generated method stub
		return roleDao.queryByName(name);
	}
	
	/***
	 * 新增
	 */
	@Override
	public int insert(Role role) {
		// TODO Auto-generated method stub
		return roleDao.insert(role);
	}

	/***
	 * 更新
	 */
	@Override
	public void update(Role role) {
		// TODO Auto-generated method stub
		roleDao.update(role);
	}

	/***
	 * 删除
	 */
	@Override
	public void delete(Role role) {
		// TODO Auto-generated method stub
		roleDao.delete(role);
	}

	/***
	 * 删除
	 */
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		roleDao.deleteById(id);
	}
	
	/***
	 * 查询角色下学院列表
	 */
	@Override
	public List<College> queryColleges(Role role) {
		// TODO Auto-generated method stub
		return roleDao.queryColleges(role);
	}
	
	/***
	 * 查询角色下班级列表
	 */
	@Override
	public List<Grade> queryGrades(Role role) {
		// TODO Auto-generated method stub
		return roleDao.queryGrade(role);
	}

	/***
	 * 新增关联
	 */
	@Override
	public int insertRelation(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return roleDao.insertRelation(map);
	}

	/***
	 * 删除关联
	 */
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
