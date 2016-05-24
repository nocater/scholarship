package com.scholarship.dao.setting;

import java.util.List;
import java.util.Map;

import com.scholarship.module.conf.setting.Setting;

/***
 * 设置DAO
 * Copyright (c) ${2016.4.1} write by 咖啡里安眠
 * 
 * @author chenshuai
 * @version 1.0 
 */
public interface SettingDao {

	/**
	 * 查询数据
	 * 
	 * @param String
	 * @return List<Setting>
	 */
	@SuppressWarnings("unchecked")
	public List<Setting> query(Map map);

	/**
	 * 根据ID查询数据
	 * 
	 * @param int
	 * @return Setting
	 */
	public Setting queryById(int id);

	/**
	 * 统计Key数据
	 * 
	 * @param String
	 * @return Setting
	 */
	public int countKey(String key);

	/**
	 * 根据Key查询数据Value
	 * 
	 * @param String
	 * @return Setting
	 */
	public String queryByKey(String key);

	/**
	 * 新建数据并返回PK
	 * 
	 * @param Setting
	 * @return
	 */
	public int insert(Setting setting);

	/**
	 * 更新数据ByKey
	 * 
	 * @param Setting
	 * @return
	 */
	public void updateByKey(Setting setting);

	/**
	 * 更新数据ID
	 * 
	 * @param Setting
	 * @return
	 */
	public void updateById(Setting setting);

	/**
	 * 删除数据ByKey
	 * 
	 * @param String
	 */
	public void deleteByKey(String key);

	/**
	 * 根据ID删除数据
	 * 
	 * @param int
	 */
	public void delete(int id);

	/**
	 * 根据类型删除记录
	 * 
	 * @param type
	 */
	public void deleteByType(int type);

	/**
	 * 根据类型和key删除
	 * 
	 * @param setting
	 */
	public void deleteByKeyAndType(Setting setting);

}
