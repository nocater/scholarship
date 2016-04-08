package com.scholarship.dao.datas;

import java.util.List;
import java.util.Map;

import com.scholarship.module.account.Account;
import com.scholarship.module.datas.Datas;

public interface DatasDao {
	public int count(Map<?,?> map);
	public List<Datas> query(Map<?,?> map, int startRow, int pageSize);
	public List<Datas> query(Map<?,?> map);
	public Datas queryById(int id);
	public int insert(Datas datas);
	public int update(Datas datas);
	public void delete(Datas datas);
	public void deleteById(int id);
	public void deleteByAccount(Account account);
	public void chengeType(Map<?,?> map);
}
