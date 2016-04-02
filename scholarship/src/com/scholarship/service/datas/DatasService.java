package com.scholarship.service.datas;

import java.util.Map;

import com.scholarship.module.account.Account;
import com.scholarship.module.datas.Datas;
import com.scholarship.module.role.Role;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface DatasService {
	public int count(Map<String,String> map);
	public SearchResult<Datas>query(Role role,Map<String,String> map,Page page);
	public Datas queryByAccount(Account account, String type);
	public Datas queryById(int id);
	public int insert(Datas datas);
	public int update(Datas datas);
	public void delete(Datas datas);
	public void deleteById(int id);
	public void chengeType(int id,int type);
}
