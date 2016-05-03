package com.scholarship.webapp.action.grade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.scholarship.module.college.College;
import com.scholarship.module.conf.AppConfig;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;
import com.scholarship.service.college.CollegeService;
import com.scholarship.service.grade.GradeService;
import com.scholarship.service.role.RoleService;
import com.scholarship.webapp.action.BaseAction;
import com.util.AnalyzerXML;
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
	private RoleService roleService;
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
	
	private InputStream in;
	private String createRole; //是否创建角色
	private String uploadPath; // 上传文件路径
	
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
			Map<String,String> map = new HashMap<String,String>();
			map.put("status", "1");
			gradeList = gradeService.query(map);
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
		
		//查询班级
		SearchResult<Grade> sr = gradeService.query(role, map, page);
		gradeList = sr.getList();
		request.setAttribute("Page", sr.getPage());
		
		collegeList = collegeService.queryAll();
		
		return SUCCESS;
	}
	
	public String queryById(){
		if(StringUtil.isNotBlank(gradeId)){
			grade = gradeService.queryById(Integer.parseInt(gradeId));
		}else{
			grade = new Grade();
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
	
	/**
	 * 执行
	 */
	public String execute(){
		if(StringUtil.isNotBlank(ids)){
			String[] arrays = ids.split(",");
			
			if(StringUtil.isNotBlank(method)){
				if(method.equals("-1")){
					//删除学院
					for(int i = 0;i< arrays.length;i++){
						this.deleteById(Integer.parseInt(arrays[i]));
					}
				}else if(method.equals("1")){
					//批量激活
					for(int i = 0;i< arrays.length;i++){
						Grade g = gradeService.queryById(Integer.parseInt(arrays[i]));
						g.setStatus(1);
						this.updateGrade(g);
					}
				}else if(method.equals("2")){
					//批量锁定
					for(int i = 0;i< arrays.length;i++){
						Grade g = gradeService.queryById(Integer.parseInt(arrays[i]));
						g.setStatus(-1);
						this.updateGrade(g);
					}
				}
			}
		}
		return SUCCESS;
	}
	
	/***
	 * 班级导入前清除session中的重复班级数组
	 * @return
	 */
	public String queryImport(){
		getSession().removeAttribute("repeatGradeList");
		return SUCCESS;
	}
	
	/***
	 * 班级导入()
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String importGrade(){
		// 解析上传的xml文件
		Map<String, Object> map = AnalyzerXML.readXML(new File(uploadPath),collegeService,gradeService);
		getSession().removeAttribute("repeatGradeList");
		
		// 获得用户集合
		if (map != null) {
			gradeList = (List<Grade>) map.get("gradeList");
			for(Grade g : gradeList){
				if(g.getId()==0){
					gradeService.insert(g);
				}else{
					gradeService.update(g);
				}
				
				if(createRole.equals("1")){
					List<Role> list = roleService.queryByName("班主任_"+g.getName());
					if(list == null || list.size()==0){
						ArrayList<Grade> l = new ArrayList<Grade>();
						l.add(g);
						Role r = new Role();
						r.setMemo("班级导入自动创建");
						r.setName("班主任_"+g.getName());
						r.setGradeList(l);
						roleService.insert(r);
//						System.out.println("已创建角色"+r.getId()+"-"+r.getName()+"关联班级："+g.getId()+"-"+g.getName());
					}
				}
			}
		}
		
//		if(gradeList!=null)
//			getSession().setAttribute("repeatAccountList", gradeList);
			
		return SUCCESS;
	}
	
	/**
	 * 将保存到本地的资源XML提供给下载
	 */
	public InputStream getTargetFile() {
		return in;
	}
	
	/**
	 * 下载用户模板
	 */
	public InputStream getLoadTemplateFile() {
		try {
			File file = new File(AppConfig.ctx + "csvTemplate/grade.xls");
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return in;
	}
	
	public int insert(Grade g){
		return gradeService.insert(g);
	}
	
	public void updateGrade(Grade g){
		gradeService.update(g);
	}
	
	public void deleteById(int id){
		Grade g = new Grade();
		g.setId(id);
		gradeService.deleteRelation(g);//删除关联
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

	public String getCreateRole() {
		return createRole;
	}

	public void setCreateRole(String createRole) {
		this.createRole = createRole;
	}

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
}
