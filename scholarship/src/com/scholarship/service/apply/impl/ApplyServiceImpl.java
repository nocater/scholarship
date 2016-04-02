package com.scholarship.service.apply.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scholarship.dao.apply.ApplyDao;
import com.scholarship.module.account.Account;
import com.scholarship.module.apply.Apply;
import com.scholarship.module.role.Role;
import com.scholarship.service.apply.ApplyService;
import com.scholarship.service.impl.BaseServiceImpl;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class ApplyServiceImpl extends BaseServiceImpl implements ApplyService {
	private ApplyDao applyDao;
	
	@Override
	public int count(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return applyDao.count(map);
	}

	@Override
	public SearchResult<Apply> query(Role role, Map<String, String> map,
			Page page) {
		// TODO Auto-generated method stub
		//查询数据
		int rowsCount = applyDao.count(map);
		page.setTotalCount(rowsCount);
		
		List<Apply> list = applyDao.query(map, page.getStartIndex(), page.getPageSize());
		// 处理分页
		SearchResult<Apply> sr = new SearchResult<Apply>();
		sr.setList(list);
		sr.setPage(page);
		
		return sr;
		
	}
	
	@Override
	public List<Apply> query(Role role, Apply apply) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String, String>();
		if(role!=null&&role.getId()!=1){
			map.put("roleId", Integer.toString(role.getId()));
		}
		if(apply!=null){
			if(apply.getAccount()!=null){
				map.put("accountId", Integer.toString(apply.getAccount().getId()));
			}
			if(StringUtil.isNotBlank(apply.getYear())){
				map.put("year", apply.getYear());
			}
			if(apply.getStatus()!=0){
				map.put("status", Integer.toString(apply.getStatus()));
			}
		}
		return applyDao.query(map);
	}
	
	@Override
	public List<Apply> queryByAccount(Account account) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String, String>();
		map.put("accountId", Integer.toString(account.getId()));
		return applyDao.query(map);
	}
	
	@Override
	public Apply queryById(int id) {
		// TODO Auto-generated method stub
		return applyDao.queryById(id);
	}
	
	@Override
	public int inert(Apply apply) {
		// TODO Auto-generated method stub
		return applyDao.insert(apply);
	}

	@Override
	public int update(Apply apply) {
		// TODO Auto-generated method stub
		return applyDao.update(apply);
	}

	@Override
	public void delete(Apply apply) {
		// TODO Auto-generated method stub
		applyDao.delete(apply);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		applyDao.deleteById(id);
	}

	public ApplyDao getApplyDao() {
		return applyDao;
	}

	public void setApplyDao(ApplyDao applyDao) {
		this.applyDao = applyDao;
	}

}
