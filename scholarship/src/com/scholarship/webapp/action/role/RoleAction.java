package com.scholarship.webapp.action.role;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;
import com.scholarship.service.audit.AuditService;
import com.scholarship.service.college.CollegeService;
import com.scholarship.service.grade.GradeService;
import com.scholarship.service.role.RoleService;
import com.scholarship.webapp.action.BaseAction;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;
import com.util.treeview.CollegeTree;

public class RoleAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RoleService roleService;
	private CollegeService collegeService;
	private AuditService auditService;
	private GradeService gradeService;
	
	private String roleId;
	private Role role;
	private String roleName;
	private String roleMemo;
	private String str_colleges;
	private String str_grades;
	private List<Role> roleList;
	private List<String> collegeList;
	private List<String> gradeList;
	private String colleges="";
	private String grades="";
	private String keyword;
	private String order;
	private String ids="";
	private String method="";//-1为删除角色 1为复制角色
	
	private String collegesTree;
	
	/***
	 * 查询所有角色
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String query(){
		HttpServletRequest request = super.getRequest();
		Page page = null;
		role = new Role();
		// 处理数据分页的起始条数
		String startIndex = request.getParameter("startIndex");
		if (StringUtil.isNotBlank(startIndex)) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
		} else {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
		}
		
		//模糊查询 名字
		if(StringUtil.isNotBlank(keyword)){
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
				role.setName(keyword);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		SearchResult<Role> sr = (SearchResult<Role>) roleService.query(role, order, page);
		roleList = (List<Role>) sr.getList();
		request.setAttribute("Page", sr.getPage());
		
		return SUCCESS;
	}
	
	/***
	 * 查询角色(ID)
	 * @return
	 */
	public String queryById(){
		if(StringUtil.isNotBlank(roleId))
			role = roleService.queryById(Integer.parseInt(roleId));
		
		try {
			CollegeTree collegetree = new CollegeTree(collegeService, super.getRequest().getContextPath());
			collegesTree = collegetree.displayTree();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/***
	 * 更新角色
	 * @return
	 */
	public String update(){
		//管理员和学生信息不能修改
		if(role.getId()==1||role.getId()==2) return SUCCESS;
		//ID为0 为新增角色 否则为修改角色
		if(role.getId()==0){
			roleService.insert(role);
		}else{
			roleService.update(role);
		}
		
		//重置关联信息
		roleService.deleteRelation(role);
		if(StringUtil.isNotBlank(colleges)){
			String[] array = colleges.split(",");
			for(String str : array){
				if(StringUtil.isBlank(str))continue;
				Map<String,Integer> map = new HashMap<String,Integer>();
				map.put("role_id", role.getId());
				map.put("college_id", Integer.parseInt(str));
				map.put("grade_id", null);
				roleService.insertRelation(map);
			}
		}
		if(StringUtil.isNotBlank(grades)){
			String[] array = grades.split(",");
			for(String str : array){
				if(StringUtil.isBlank(str))continue;
				Map<String,Integer> map = new HashMap<String,Integer>();
				map.put("role_id", role.getId());
				map.put("college_id", null);
				map.put("grade_id", Integer.parseInt(str));
				roleService.insertRelation(map);
			}
		}
		
		return SUCCESS;
	}
	
	/***
	 * 搜索
	 * @return
	 */
	public String search(){
		/*if(StringUtil.isNotBlank(keyword)){
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");
				Map<String,String> map = new HashMap<String,String>();
				map.put("name", keyword);
				roleList = roleService.query(map);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return SUCCESS;
	}
	
	public String execute(){
		
		if(StringUtil.isNotBlank(method)){
			if(method.equals("-1")){
				//删除角色
				if(StringUtil.isNotBlank(ids)){
					String[] arrays = ids.split(",");
					for(int i = 0;i< arrays.length;i++){
						if(!arrays[i].equals("1")&&!arrays[i].equals("2")){//1 2为管理员和学生 内置角色
							role = roleService.queryById(Integer.parseInt(arrays[i]));
							delete(role);
						}
					}
				}
			}else if(method.equals("1")){
				if(StringUtil.isNotBlank(ids)){
					//复制角色
					String[] arrays = ids.split(",");
					for(int i=0;i<arrays.length;i++){
						if(!arrays[i].equals("1")&&!arrays[i].equals("2")){//1 2为管理员和学生 内置角色
							role = roleService.queryById(Integer.parseInt(arrays[i]));
							role.setId(0);
							String name="";
							int number =1;
							do{
								name=role.getName()+"-复制"+number;
								number++;
								if(number>100) name="你丫有病把这么测试";
							}while(roleService.queryByName(name).size()>0);
							role.setName(name);
							insert(role);
						}
					}
				}
			}
		}
		return SUCCESS;
	}
	
	/***
	 * 添加角色
	 * @return
	 */
	public void insert(Role r){
		roleService.insert(r);
		//添加关联 已转移到Service实现
//		Map<String,Integer> map=null;
//		for(College c : r.getCollegeList()){
//			map = new HashMap<String,Integer>();
//			map.put("role_id", r.getId());
//			map.put("college_id", c.getId());
//			map.put("grade_id", null);
//			roleService.insertRelation(map);
//		}
//		for(Grade g:r.getGradeList()){
//			map = new HashMap<String,Integer>();
//			map.put("role_id", r.getId());
//			map.put("college_id", null);
//			map.put("grade_id", g.getId());
//			roleService.insertRelation(map);
//		}
	}
	
	/***
	 * 删除角色
	 * @return
	 */
	public void delete(Role r){
//		roleService.deleteRelation(r);  //已转移到Service实现
		roleService.delete(r.getId());
	}
	
	/***
	 * 检查角色名字是否重复
	 */
	public void checkRoleName(){
		String result = "false";
		if(StringUtil.isNotBlank(roleName)){
			
			try {
				roleName = java.net.URLDecoder.decode(roleName, "UTF-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			List<Role> list = roleService.queryByName(roleName);
			if(list.size()>0){
				for(Role r:list){
					result="true";
					if(StringUtil.isNotBlank(roleId)&&r.getId()==Integer.parseInt(roleId)) result="false";
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
	
	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<String> getCollegeList() {
		return collegeList;
	}

	public void setCollegeList(List<String> collegeList) {
		this.collegeList = collegeList;
	}

	public List<String> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<String> gradeList) {
		this.gradeList = gradeList;
	}

	public String getRoleMemo() {
		return roleMemo;
	}

	public void setRoleMemo(String roleMemo) {
		this.roleMemo = roleMemo;
	}

	public String getStr_colleges() {
		return str_colleges;
	}

	public void setStr_colleges(String str_colleges) {
		this.str_colleges = str_colleges;
	}

	public String getStr_grades() {
		return str_grades;
	}

	public void setStr_grades(String str_grades) {
		this.str_grades = str_grades;
	}

	public String getColleges() {
		return colleges;
	}

	public void setColleges(String colleges) {
		this.colleges = colleges;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getGrades() {
		return grades;
	}

	public void setGrades(String grades) {
		this.grades = grades;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public CollegeService getCollegeService() {
		return collegeService;
	}

	public AuditService getAuditService() {
		return auditService;
	}

	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public String getCollegesTree() {
		return collegesTree;
	}

	public void setCollegesTree(String collegesTree) {
		this.collegesTree = collegesTree;
	}

	public GradeService getGradeService() {
		return gradeService;
	}

	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}
	
}
