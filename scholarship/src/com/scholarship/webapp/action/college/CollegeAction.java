package com.scholarship.webapp.action.college;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.scholarship.module.college.College;
import com.scholarship.module.role.Role;
import com.scholarship.service.college.CollegeService;
import com.scholarship.webapp.action.BaseAction;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class CollegeAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<College> collegeList;
	private CollegeService collegeService;
	private College college;
	private Role role;
	private String collegeId;
	private String collegeName;
	private String collegeMemo;
	
	private String ids;
	private String method;
	
	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	private String keyword;
	private String order;
	
	public String queryAll(){
		collegeList = collegeService.queryAll();
		return SUCCESS;
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
		
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		
		//查询学院
		SearchResult<College> sr = collegeService.query(role, map, page);
		collegeList = sr.getList();
		request.setAttribute("Page", sr.getPage());
		
		return SUCCESS;
	}
	
	public String queryById(){
		if(StringUtil.isNotBlank(collegeId)){
			college = collegeService.queryById(Integer.parseInt(collegeId));
		}
		return SUCCESS;
	}
	
	public String update(){
		
		if(college.getId()==0){
			//新增学院
			this.insert(college);
		}else{
			//更新学院
			this.update(college);
		}
		
		return SUCCESS;
	}
	
	public void checkCollegeName(){
		String result = "false";
		if(StringUtil.isNotBlank(collegeName)){
			
			try {
				collegeName = java.net.URLDecoder.decode(collegeName, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			List<College> list = collegeService.queryByName(collegeName);
			if(list.size()>0){
				for(College c:list){
					result = "true";
					if(StringUtil.isNotBlank(collegeId)&&c.getId()==Integer.parseInt(collegeId)) result="false";
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
	
	public String execute(){
		if(StringUtil.isNotBlank(method)){
			if(method.equals("-1")){
				//删除学院
				if(StringUtil.isNotBlank(ids)){
					String[] arrays = ids.split(",");
					for(int i = 0;i< arrays.length;i++){
						int id = Integer.parseInt(arrays[i]);
						this.deleteById(id);
					}
				}
			}
			
		}
		return SUCCESS;
	}
	
	public int insert(College college){
		collegeService.insert(college);
		return college.getId();
	}
	
	public void deleteById(int id){
		collegeService.deleteById(id);
	}
	
	public void update(College college){
		collegeService.update(college);
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getOrder() {
		return order;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(String collegeId) {
		this.collegeId = collegeId;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public String getCollegeMemo() {
		return collegeMemo;
	}

	public void setCollegeMemo(String collegeMemo) {
		this.collegeMemo = collegeMemo;
	}

	public String getIds() {
		return ids;
	}

	public String getMethod() {
		return method;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
}
