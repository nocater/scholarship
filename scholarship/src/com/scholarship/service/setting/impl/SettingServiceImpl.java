package com.scholarship.service.setting.impl;

import java.util.List;
import java.util.Map;

import com.scholarship.dao.setting.SettingDao;
import com.scholarship.module.conf.setting.Setting;
import com.scholarship.service.impl.BaseServiceImpl;
import com.scholarship.service.setting.SettingService;

public class SettingServiceImpl extends BaseServiceImpl implements SettingService{
	public SettingDao settingDao;
	
	@Override
	public List<Setting> query(Map<?, ?> map) {
		// TODO Auto-generated method stub
		return settingDao.query(map);
	}

	@Override
	public Setting queryById(int id) {
		// TODO Auto-generated method stub
		return settingDao.queryById(id);
	}

	@Override
	public int countKey(String key) {
		// TODO Auto-generated method stub
		return settingDao.countKey(key);
	}

	@Override
	public String queryByKey(String key) {
		// TODO Auto-generated method stub
		return settingDao.queryByKey(key);
	}

	@Override
	public int insert(Setting setting) {
		// TODO Auto-generated method stub
		return settingDao.insert(setting);
	}

	@Override
	public void updateByKey(Setting setting) {
		// TODO Auto-generated method stub
		settingDao.updateByKey(setting);
	}

	@Override
	public void updateById(Setting setting) {
		// TODO Auto-generated method stub
		settingDao.updateById(setting);
	}

	@Override
	public void deleteByKey(String key) {
		// TODO Auto-generated method stub
		settingDao.deleteByKey(key);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		settingDao.delete(id);
	}

	@Override
	public void deleteByType(int type) {
		// TODO Auto-generated method stub
		settingDao.deleteByType(type);
	}

	@Override
	public void deleteByKeyAndType(Setting setting) {
		// TODO Auto-generated method stub
		settingDao.deleteByKeyAndType(setting);
	}

	public SettingDao getSettingDao() {
		return settingDao;
	}

	public void setSettingDao(SettingDao settingDao) {
		this.settingDao = settingDao;
	}

}
