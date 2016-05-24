package com.scholarship.service.setting;

import java.util.List;
import java.util.Map;

import com.scholarship.module.conf.setting.Setting;

public interface SettingService {
	public List<Setting> query(Map<?,?> map);
	public Setting queryById(int id);
	public int countKey(String key);
	public String queryByKey(String key);
	public int insert(Setting setting);
	public void updateByKey(Setting setting);
	public void updateById(Setting setting);
	public void deleteByKey(String key);
	public void delete(int id);
	public void deleteByType(int type);
	public void deleteByKeyAndType(Setting setting);
}
