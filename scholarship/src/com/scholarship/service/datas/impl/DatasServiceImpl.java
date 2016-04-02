package com.scholarship.service.datas.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scholarship.dao.datas.DatasDao;
import com.scholarship.module.account.Account;
import com.scholarship.module.datas.Datas;
import com.scholarship.module.role.Role;
import com.scholarship.service.datas.DatasService;
import com.scholarship.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

public class DatasServiceImpl extends BaseServiceImpl implements DatasService {
	private DatasDao datasDao;
	
	@Override
	public int count(Map<String, String> map) {
		// TODO Auto-generated method stub
		return datasDao.count(map);
	}

	@Override
	public SearchResult<Datas> query(Role role, Map<String, String> map,
			Page page) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Datas queryByAccount(Account account, String type) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String, String>();
		map.put("accountId", Integer.toString(account.getId()));
		map.put("type", type);
		Datas datas=null;
		List<Datas> list = datasDao.query(map);
		if(list.size()>0) datas = list.get(0);
		return datas;
	}

	@Override
	public Datas queryById(int id) {
		// TODO Auto-generated method stub
		return datasDao.queryById(id);
	}
	
	@Override
	public int insert(Datas datas) {
		// TODO Auto-generated method stub
		return datasDao.insert(datas);
	}

	@Override
	public int update(Datas datas) {
		// TODO Auto-generated method stub
		return datasDao.update(datas);
	}

	@Override
	public void delete(Datas datas) {
		// TODO Auto-generated method stub
		datasDao.delete(datas);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		datasDao.deleteById(id);
	}

	public DatasDao getDatasDao() {
		return datasDao;
	}

	public void setDatasDao(DatasDao datasDao) {
		this.datasDao = datasDao;
	}

	@Override
	public void chengeType(int id,int type) {
		// TODO Auto-generated method stub
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("id", id);
		map.put("type", type);
		this.datasDao.chengeType(map);
	}

}
