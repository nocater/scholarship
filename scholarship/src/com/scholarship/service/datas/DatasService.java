package com.scholarship.service.datas;

import java.util.Map;

import com.scholarship.module.account.Account;
import com.scholarship.module.datas.Datas;
import com.scholarship.module.role.Role;
import com.util.page.Page;
import com.util.page.SearchResult;

/***
 * 信息Service
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public interface DatasService {
	/***
	 * 统计
	 * @param map
	 * @return
	 */
	public int count(Map<String,String> map);
	/***
	 * 查询(分页)
	 * @param role
	 * @param map
	 * @param page
	 * @return
	 */
	public SearchResult<Datas>query(Role role,Map<String,String> map,Page page);
	/***
	 * 查询(账户  默认状态0  1为修改备份未开启)
	 * @param account
	 * @param type
	 * @return
	 */
	public Datas queryByAccount(Account account, String type);
	/***
	 * 查询(ID)
	 * @param id
	 * @return
	 */
	public Datas queryById(int id);
	/***
	 * 新增
	 * @param datas
	 * @return
	 */
	public int insert(Datas datas);
	/***
	 * 更新
	 * @param datas
	 * @return
	 */
	public int update(Datas datas);
	/***
	 * 删除
	 * @param datas
	 */
	public void delete(Datas datas);
	/***
	 * 删除(ID)
	 * @param id
	 */
	public void deleteById(int id);
	/***
	 * 删除(账户)
	 * @param account
	 */
	public void deleteByAccount(Account account);
	/***
	 * 更改状态
	 * @param id
	 * @param type
	 */
	public void chengeType(int id,int type);
}
