package com.scholarship.dao.scholarship;

import java.util.List;

import com.scholarship.module.scholarship.Scholarship;

/***
 * 奖学金DAO
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public interface ScholarshipDao{
	/***
	 * 查询所有
	 * @return
	 */
	public List<Scholarship> queryAll();
	/***
	 * 查询(ID)
	 * @param id
	 * @return
	 */
	public Scholarship querybyId(int id);
	/***
	 * 查询类别
	 * @return
	 */
	public List<String> queryCategories();
	/***
	 * 新增
	 * @param scholarship
	 * @return
	 */
	public int insert(Scholarship scholarship);
	/***
	 * 更新
	 * @param scholarship
	 * @return
	 */
	public int update(Scholarship scholarship);
	/***
	 * 删除
	 * @param scholarship
	 */
	public void delete(Scholarship scholarship);
	/***
	 * 删除(ID)
	 * @param id
	 */
	public void deleteById(int id);
}
