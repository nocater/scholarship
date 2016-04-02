package com.scholarship.dao.audit;

import java.util.List;
import java.util.Map;

import com.scholarship.module.audit.Audit;

public interface AuditDao {
	public int count(Map<?,?> map);
	public int insert(Audit audit);
	public List<Audit> query(Map<?,?> map,int startRow, int pageSize);
	public Audit queryById(int id);
	public int deleteAll();
	public void updateAccountName(Map<?,?> map);
}
