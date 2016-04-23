package com.scholarship.dao.datas;

import java.util.List;
import java.util.Map;

import com.scholarship.module.account.Account;
import com.scholarship.module.datas.Datas;

/***
 * 信息DAO
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public interface DatasDao {
	/***
	 * 统计
	 * @param map
	 * @return
	 */
	public int count(Map<?,?> map);
	/***
	 * 查询(分页)
	 * @param map
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	public List<Datas> query(Map<?,?> map, int startRow, int pageSize);
	/***
	 * 查询
	 * @param map
	 * @return
	 */
	public List<Datas> query(Map<?,?> map);
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
	 * @param map
	 */
	public void chengeType(Map<?,?> map);
}
