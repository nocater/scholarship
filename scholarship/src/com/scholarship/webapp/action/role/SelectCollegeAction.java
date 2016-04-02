package com.scholarship.webapp.action.role;

import java.io.IOException;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.scholarship.module.college.College;
import com.scholarship.service.college.CollegeService;
import com.scholarship.webapp.action.BaseAction;

public class SelectCollegeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CollegeService collegeService;
	private List<College> collegeList;
	
	/***
	 * 
	 */
	public void queryAllCollegeAjax(){
		//JSON java.lang.ClassNotFoundException: net.sf.ezmorph.Morpher 
		/*出现以上等异常，可能是使用Json缺少以下几个包中的一个： 
		 ezmorph-1.0.6.jar 
		 commons-lang 2.4 
		 commons-beanutils 1.7.0 
		 commons-collections 3.2 
		 commons-logging 1.1.1 */
		collegeList = collegeService.queryAll();
		JSONArray jsons = new JSONArray();
		for(College college:collegeList){
			JSONObject obj = new JSONObject();
			obj.put("id", college.getId());
			obj.put("name", college.getName());
			jsons.add(obj);
		}
		try {
			getResponse().setContentType("text/html;charset=UTF-8");
			getResponse().setCharacterEncoding("UTF-8");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().getWriter().write(jsons.toString());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public CollegeService getCollegeService() {
		return collegeService;
	}

	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}

	public List<College> getCollegeList() {
		return collegeList;
	}

	public void setCollegeList(List<College> collegeList) {
		this.collegeList = collegeList;
	}
	
}
