package com.scholarship.dao.apply;

import java.util.List;
import java.util.Map;

import com.scholarship.module.account.Account;
import com.scholarship.module.apply.Apply;

public interface ApplyDao {
	public int count(Map<?,?> map);
	public List<Apply> query(Map<?,?> map, int startRow, int pageSize);
	public List<Apply> query(Map<?,?> map);
	public Apply queryById(int id);
	public int insert(Apply apply);
	public int update(Apply apply);
	public void delete(Apply apply);
	public void deleteById(int id);
	public void deleteByAccount(Account account);
}
