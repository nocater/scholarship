package com.scholarship.dao.audit;

import java.util.List;
import java.util.Map;

import com.scholarship.module.audit.Audit;

/***
 * 审计DAO
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public interface AuditDao {
	/***
	 * 统计
	 */
	public int count(Map<?,?> map);
	/***
	 * 新增
	 * @param audit
	 * @return
	 */
	public int insert(Audit audit);
	/***
	 * 查询
	 * @param map
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	public List<Audit> query(Map<?,?> map,int startRow, int pageSize);
	/***
	 * 查询(ID)
	 * @param id
	 * @return
	 */
	public Audit queryById(int id);
	/***
	 * 删除所有
	 * @return
	 */
	public int deleteAll();
	/***
	 * 更新审计中账户姓名
	 * @param map
	 */
	public void updateAccountName(Map<?,?> map);
}
