package com.scholarship.service.audit;

import java.util.List;
import java.util.Map;

import com.scholarship.module.audit.Audit;
import com.scholarship.module.role.Role;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface AuditService {
	public int count(Map<?,?> map);
	public SearchResult<Audit>query(Role role,Map<String,String> map,Page page);
	public int insert(Audit audit);
	public Audit queryById(int id);
	public int deleteAll();
	public void updateAccountName(Map<?,?> map);
	/***
	 * 登录内部审计
	 * @param loginSucceed
	 * @param account_id
	 * @param sourceIP
	 * @param fieldList
	 * @return
	 */
	public int insertByLoginOperator(boolean loginSucceed,Integer accountId, String sourceIP, List<String> fieldList);
	/***
	 * 操作记录
	 * @param accountId
	 * @param type
	 * @param sourceIP
	 * @param fieldList
	 * @return
	 */
	public int operator(Integer accountId, String type,String sourceIP, List<String> fieldList);
}
