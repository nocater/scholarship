package com.scholarship.service.audit.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;

import com.scholarship.dao.account.AccountDao;
import com.scholarship.dao.audit.AuditDao;
import com.scholarship.module.account.Account;
import com.scholarship.module.audit.Audit;
import com.scholarship.module.role.Role;
import com.scholarship.service.audit.AuditService;
import com.scholarship.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

public class AuditServiceImpl extends BaseServiceImpl implements AuditService {
	private AuditDao auditDao;
	private AccountDao accountDao;
	
	@Override
	public int count(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return auditDao.count(map);
	}

	@Override
	public SearchResult<Audit> query(Role role, Map<String, String> map,
			Page page) {
		// TODO Auto-generated method stub
		if(role.getId()!=1) return null;
		
		int rowCount = this.count(map);
		page.setTotalCount(rowCount);
		List<Audit> list = auditDao.query(map,
				page.getStartIndex(), page.getPageSize());
		
		// 解析JSON
		for (Audit sa : list) {
			String buf = "";
			JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(sa
					.getNote());
			List output = (List) JSONSerializer.toJava(jsonArray);
			for (Object obj : output) {
				String newstr = ((String)obj).replaceAll("'","~");
				newstr = newstr.replaceAll("\"","&");
				newstr = newstr.replaceAll("\r\n","<br />");
				buf += newstr +"<br />"; 
			}
			sa.setNote(buf);

		}
		
		// 处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}

	@Override
	public int insert(Audit audit) {
		// TODO Auto-generated method stub
		return auditDao.insert(audit);
	}

	@Override
	public Audit queryById(int id) {
		// TODO Auto-generated method stub
		return auditDao.queryById(id);
	}

	@Override
	public int deleteAll() {
		// TODO Auto-generated method stub
		return auditDao.deleteAll();
	}

	@Override
	public void updateAccountName(Map<?, ?> map) {
		// TODO Auto-generated method stub
		auditDao.updateAccountName(map);
	}

	public AuditDao getAuditDao() {
		return auditDao;
	}

	public void setAuditDao(AuditDao auditDao) {
		this.auditDao = auditDao;
	}
	
	/***
	 * 登录内部审计
	 */
	@Override
	public int insertByLoginOperator(boolean loginSucceed, Integer accountId,
			String sourceIP, List<String> fieldList) {
		// TODO Auto-generated method stub
		Audit audit = new Audit();

		// 行为描述
		if(loginSucceed){
			audit.setOperation("系统登录");
		}else{
			audit.setOperation("系统登录失败");
		}
		// 操作者
		audit.setAccountId(accountId);
		
		// 操作者名称
		if(accountId!=0){
			Account account = accountDao.queryById(accountId);
			audit.setAccountName(account.getName()+"("+account.getAccno()+")");
		}
		// 行为时间
		audit.setOperationTime(new Date());
		// 行为IP地址
		audit.setSourceIP(sourceIP);

		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(fieldList
				.toArray());
		audit.setNote(jsonArray.toString());

		if (fieldList.size() > 0) {
			return auditDao.insert(audit);
		} else {
			return 0;
		}
	}
	
	/***
	 * 操作审计
	 */
	@Override
	public int operator(Integer accountId, String type, String sourceIP,
			List<String> fieldList) {
		// TODO Auto-generated method stub
		Audit audit = new Audit();

		// 行为描述
		audit.setOperation(type);
		
		// 操作者
		audit.setAccountId(accountId);
		
		// 操作者名称
		if(accountId!=0){
			Account account = accountDao.queryById(accountId);
			audit.setAccountName(account.getName());
		}
		// 行为时间
		audit.setOperationTime(new Date());
		// 行为IP地址
		audit.setSourceIP(sourceIP);

		JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(fieldList
				.toArray());
		audit.setNote(jsonArray.toString());

		if (fieldList.size() > 0) {
			return auditDao.insert(audit);
		} else {
			return 0;
		}
	}
	
	public AccountDao getAccountDao() {
		return accountDao;
	}

	public void setAccountDao(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

}
