package com.scholarship.dao.setting.impl;

import java.util.List;
import java.util.Map;

import com.scholarship.dao.mybatis.BaseDaoMyBatis;
import com.scholarship.dao.setting.SettingDao;
import com.scholarship.module.conf.setting.Setting;

public class SettingDaoImpl extends BaseDaoMyBatis implements SettingDao{
	
	/**
	 * 查询数据
	 * 
	 * @param String
	 * @return List<Setting>
	 */
	@Override
	public List<Setting> query(Map map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("setting_query",map);
	}

	/**
	 * 根据ID查询数据
	 * 
	 * @param int
	 * @return Setting
	 */
	@Override
	public Setting queryById(int id) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("setting_queryById", id);
	}
	
	/**
	 * 统计Key数据
	 * 
	 * @param String
	 * @return Setting
	 */
	@Override
	public int countKey(String key) {
		// TODO Auto-generated method stub
		System.out.println("keyyyyyyyyyyyy=="+key);
		return getSqlSession().update("setting_countKey", key);
	}

	/**
	 * 根据Key查询数据Value
	 * 
	 * @param String
	 * @return Setting
	 */
	@Override
	public String queryByKey(String key) {
		// TODO Auto-generated method stub
		return getSqlSession().selectOne("setting_queryByKey", key);
	}

	@Override
	public int insert(Setting setting) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("setting_insert", setting);
	}

	/**
	 * 更新数据ByKey
	 * 
	 * @param Setting
	 * @return
	 */
	@Override
	public void updateByKey(Setting setting) {
		// TODO Auto-generated method stub
		getSqlSession().update("setting_update",setting);
	}

	/**
	 * 更新数据ID
	 * 
	 * @param Setting
	 * @return
	 */
	@Override
	public void updateById(Setting setting) {
		// TODO Auto-generated method stub
		getSqlSession().update("setting_updateById", setting);
	}

	/**
	 * 删除数据ByKey
	 * 
	 * @param String
	 */
	@Override
	public void deleteByKey(String key) {
		// TODO Auto-generated method stub
		getSqlSession().delete("setting_deleteByKey", key);
	}

	/**
	 * 根据ID删除数据
	 * 
	 * @param int
	 */
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		getSqlSession().delete("setting_deleteById", id);
	}
	
	/**
	 * 根据类型删除记录
	 * 
	 * @param type
	 */
	@Override
	public void deleteByType(int type) {
		// TODO Auto-generated method stub
		getSqlSession().delete("setting_deleteByType",type);
	}
	
	/**
	 * 根据类型和key删除
	 * 
	 * @param setting
	 */
	@Override
	public void deleteByKeyAndType(Setting setting) {
		// TODO Auto-generated method stub
		getSqlSession().delete("setting_deleteByKeyAndType", setting);
	}

}
