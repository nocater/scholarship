package com.scholarship.webapp.action.grade;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;
import com.scholarship.service.college.CollegeService;
import com.scholarship.service.grade.GradeService;
import com.scholarship.webapp.action.BaseAction;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class GradeAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GradeService gradeService;
	private CollegeService collegeService;
	private List<Grade> gradeList;
	private Grade grade;
	private Role role;
	private List<College> collegeList;
	
	private String gradeId;
	private String gradeName;
	private String ids;
	private String method;
	private String keyword;
	private String order;
	
	private String status;
	private int	   gradeStatus;
	private String collegeId;
	private String gradeGrade;
	private String gradeEdubg;
	
	public String queryAll(){
		gradeList = gradeService.queryAll();
		return SUCCESS;
	}
	
	/***
	 * 查询分配的班级AJAX
	 */
	public void queryGradesAjax(){
		String collegeId = (String)getRequest().getParameter("id");
		if(StringUtil.isNotBlank(collegeId)&&!collegeId.equals("0")){
			College college = new College();
			college.setId(Integer.parseInt(collegeId));
			gradeList = gradeService.queryByCollege(college);
		}else{
			gradeList = gradeService.queryAll();
		}
		
		//JSON
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String query(){
		HttpServletRequest request = getRequest();
		//获取当前账户角色
		role = (Role)getSession().getAttribute("LOGON_ROLE");
		Page page = null;
		
		//模糊/高级查询及排序
		Map<String,String> map = new HashMap<String,String>();
		if(StringUtil.isNotBlank(keyword)){
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put("name", keyword);
		}
		if(StringUtil.isNotBlank(order)){
			map.put("order", order);
		}
		
		if(StringUtil.isNotBlank(collegeId)){
			map.put("collegeId", collegeId);
		}
		if(StringUtil.isNotBlank(status)){
			map.put("status", status);
		}
		if(StringUtil.isNotBlank(gradeEdubg)){
			if(gradeEdubg.equals("-1"))map.put("edubg", "专科");
			if(gradeEdubg.equals("1"))map.put("edubg", "本科");
		}
		if(StringUtil.isNotBlank(gradeGrade)){
			map.put("grade", gradeGrade);
		}
		
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		
		//查询学院
		SearchResult<Grade> sr = gradeService.query(role, map, page);
		gradeList = sr.getList();
		request.setAttribute("Page", sr.getPage());
		
		collegeList = collegeService.queryAll();
		
		return SUCCESS;
	}
	
	public String queryById(){
		if(StringUtil.isNotBlank(gradeId)){
			grade = gradeService.queryById(Integer.parseInt(gradeId));
		}
		collegeList = collegeService.queryAll();
		return SUCCESS;
	}
	
	public String update(){
		if(StringUtil.isNotBlank(collegeId)){
			grade.setCollege(collegeService.queryById(Integer.parseInt(collegeId)));
		}
		if(StringUtil.isNotBlank(gradeEdubg)){
			grade.setEdubg(gradeEdubg);
		}
		grade.setStatus(1);
		if(gradeStatus==-1)grade.setStatus(-1);
		if(grade.getId()==0){
			this.insert(grade);
		}else{
			this.updateGrade(grade);
		}
		return SUCCESS;
	}
	
	public void checkGradeName(){
		String result = "false";
		if(StringUtil.isNotBlank(gradeName)){
			
			try {
				gradeName = java.net.URLDecoder.decode(gradeName, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			List<Grade> list = gradeService.queryByName(gradeName);
			if(list.size()>0){
				for(Grade g:list){
					result = "true";
					if(StringUtil.isNotBlank(gradeId)&&g.getId()==Integer.parseInt(gradeId)) result="false";
				}
			}
		}
		try {
			getResponse().setContentType("text/html;charset=UTF-8");
			getResponse().setCharacterEncoding("UTF-8");
			getResponse().setHeader("Cache-Control", "no-cache");
			getResponse().getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int insert(Grade g){
		return gradeService.insert(g);
	}
	
	public void updateGrade(Grade g){
		gradeService.update(g);
	}
	
	public void deleteById(int id){
		gradeService.deleteById(id);
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

	public CollegeService getCollegeService() {
		return collegeService;
	}

	public Grade getGrade() {
		return grade;
	}

	public Role getRole() {
		return role;
	}

	public List<College> getCollegeList() {
		return collegeList;
	}

	public String getIds() {
		return ids;
	}

	public String getMethod() {
		return method;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getOrder() {
		return order;
	}

	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public void setCollegeList(List<College> collegeList) {
		this.collegeList = collegeList;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getGradeEdubg() {
		return gradeEdubg;
	}

	public void setGradeEdubg(String gradeEdubg) {
		this.gradeEdubg = gradeEdubg;
	}

	public String getStatus() {
		return status;
	}

	public String getCollegeId() {
		return collegeId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getGradeGrade() {
		return gradeGrade;
	}

	public void setGradeGrade(String gradeGrade) {
		this.gradeGrade = gradeGrade;
	}

	public int getGradeStatus() {
		return gradeStatus;
	}

	public void setGradeStatus(int gradeStatus) {
		this.gradeStatus = gradeStatus;
	}
}
