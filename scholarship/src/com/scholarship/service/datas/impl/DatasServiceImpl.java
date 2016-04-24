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

/***
 * 信息ServiceImpl
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public class DatasServiceImpl extends BaseServiceImpl implements DatasService {
	private DatasDao datasDao;
	
	/***
	 * 统计
	 */
	@Override
	public int count(Map<String, String> map) {
		// TODO Auto-generated method stub
		return datasDao.count(map);
	}

	/***
	 * 查询(分页)
	 */
	@Override
	public SearchResult<Datas> query(Role role, Map<String, String> map,
			Page page) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/***
	 * 查询(账户  默认状态0  1为修改备份未开启)
	 */
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

	/***
	 * 查询(ID)
	 */
	@Override
	public Datas queryById(int id) {
		// TODO Auto-generated method stub
		return datasDao.queryById(id);
	}
	
	/***
	 * 新增
	 */
	@Override
	public int insert(Datas datas) {
		// TODO Auto-generated method stub
		return datasDao.insert(datas);
	}

	/***
	 * 更新
	 */
	@Override
	public int update(Datas datas) {
		// TODO Auto-generated method stub
		return datasDao.update(datas);
	}

	/***
	 * 删除
	 */
	@Override
	public void delete(Datas datas) {
		// TODO Auto-generated method stub
		datasDao.delete(datas);
	}

	/***
	 * 删除(ID)
	 */
	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		datasDao.deleteById(id);
	}

	/***
	 * 更改状态
	 */
	@Override
	public void chengeType(int id,int type) {
		// TODO Auto-generated method stub
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("id", id);
		map.put("type", type);
		this.datasDao.chengeType(map);
	}

	/***
	 * 删除
	 */
	@Override
	public void deleteByAccount(Account account) {
		// TODO Auto-generated method stub
		datasDao.deleteByAccount(account);
	}

	public DatasDao getDatasDao() {
		return datasDao;
	}

	public void setDatasDao(DatasDao datasDao) {
		this.datasDao = datasDao;
	}
}
