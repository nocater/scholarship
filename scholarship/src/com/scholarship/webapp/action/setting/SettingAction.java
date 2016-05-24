package com.scholarship.webapp.action.setting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.scholarship.module.conf.setting.Setting;
import com.scholarship.service.setting.SettingService;
import com.scholarship.webapp.action.BaseAction;

public class SettingAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SettingService settingService;
	
	public void query(){
		Setting set = new Setting();
		set.setKey("k");
		set.setValue("v");
		set.setType(1);
		
		settingService.insert(set);
		System.out.println("新增OK");
		
		List<Setting> list = null;
		Map<String,String> map = new HashMap<String,String>();
		map.put("k","v");
		list = settingService.query(map);
		System.out.println(list.get(0).getId());
		System.out.println("查询OK");
		
		settingService.queryById(set.getId());
		
		System.out.println(settingService.queryByKey("k"));
		
		System.out.println(settingService.countKey("k"));
		
		settingService.updateByKey(set);
		
		settingService.updateById(set);
		
	}
}
