package com.scholarship.webapp.action.role;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.service.grade.GradeService;
import com.scholarship.webapp.action.BaseAction;
import com.util.StringUtil;

public class SelectGradeAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GradeService gradeService;
	private List<Grade> gradeList;
	private String collegeId;
	
	public void queryAllGradeAjax(){
		//JSON java.lang.ClassNotFoundException: net.sf.ezmorph.Morpher 
		/*出现以上等异常，可能是使用Json缺少以下几个包中的一个： 
		 ezmorph-1.0.6.jar 
		 commons-lang 2.4 
		 commons-beanutils 1.7.0 
		 commons-collections 3.2 
		 commons-logging 1.1.1 */
		Map<String,String> map = new HashMap<String,String>();
		map.put("status", "1");
		gradeList = gradeService.query(map);
		JSONArray jsons = new JSONArray();
		for(Grade grade:gradeList){
			JSONObject obj = new JSONObject();
			obj.put("id", grade.getId());
			obj.put("name", grade.getName());
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
	
	public void queryGradesByCollegeIdAjax(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("status", "1");
		if(StringUtil.isNotBlank(collegeId)){
			map.put("collegeId", collegeId);
		}
		gradeList = gradeService.query(map);
		JSONArray jsons = new JSONArray();
		for(Grade grade:gradeList){
			JSONObject obj = new JSONObject();
			obj.put("id", grade.getId());
			obj.put("name", grade.getName());
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
	
	public GradeService getGradeService() {
		return gradeService;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public String getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}
	
}
