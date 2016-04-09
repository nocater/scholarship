package com.util.treeview;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.scholarship.module.college.College;
import com.scholarship.service.college.CollegeService;

/**
 * 
 * 人员树型视图 <功能详细描述>
 * 
 * @author 郭煜玺
 * @version V100R001C001 2011-2-20
 * @see [相关类/方法]
 * @since compliance V100R001C001
 */
public class CollegeTree {
	/**
	 * 统一打印日志
	 */
	private static final Log LOG = LogFactory.getLog(CollegeTree.class);

	private String ctx;

	private CollegeService collegeService;

	private StringBuffer htmlBuffer;


	/**
	 * 构造函数
	 * 
	 * @param collegeService
	 *            Service层对象
	 * @param ctx
	 *            上下文
	 * @throws IOException
	 *             IO异常
	 */
	public CollegeTree(CollegeService collegeService, String ctx)
			throws IOException {
		// Service层对象初始化
		this.collegeService = collegeService;

		// 页面对象初始化
		this.ctx = ctx;
		htmlBuffer = new StringBuffer();
	}

	/**
	 * 显示树型 <功能详细描述>
	 * 
	 * @throws IOException
	 *             IO异常
	 * @see [类、类#方法、类#成员]
	 */
	public String displayTree() throws IOException {
		
	
		StringBuffer treeChidren = new StringBuffer();
		// 树形节点存放map
		Map<String, Object> map = new HashMap<String, Object>();
		// 根节点
		map.put("parentId", 0);

		// 查询父节点下的所有组
		List<College> collegeList = collegeService.queryAll();
		
		// 显示ROOT节点
		for (int i = 0; i < collegeList.size(); i++) {
			JSONObject c = createJSONObject(collegeList.get(i));
			if(c !=null )
			{
				if(treeChidren.length() > 0)
				{
					treeChidren.append(","+c.toString());
				}
				else
				{
					treeChidren.append(c.toString());
				}
			}
		}
		
		return treeChidren.toString();
	}
	
	
	/**
     * 显示树型 <功能详细描述>根据组id查询
     * 
     * @throws IOException
     *             IO异常
     * @see [类、类#方法、类#成员]
     */
    public String displayTreeById(String groupId) throws IOException {
        StringBuffer treeChidren = new StringBuffer();
//        Map<String, Object> map = new HashMap<String, Object>();
//		map.put("empGroupLimit",groupId);
//		List<EmployeeGroup> groupList = collegeService.query(map);
//		List<EmployeeGroup> empGroupList = new ArrayList<EmployeeGroup>();
//		//所有分组 以分组ID-分组信息格式存储
//		Map<Integer, EmployeeGroup> allGroup = new HashMap<Integer, EmployeeGroup>();
//		//上级分组节点
//		Map<Integer, EmployeeGroup> root = new HashMap<Integer, EmployeeGroup>();
//		
//		EmployeeGroup eg;
//		Integer gid,parentId;
//		for(int i = 0; i < groupList.size(); i++){
//			eg = groupList.get(i);
//			allGroup.put(eg.getGroupId(), eg);
//		}
//		
//		for(Entry<Integer, EmployeeGroup> e : allGroup.entrySet()){
//			gid = e.getKey();
//			eg = e.getValue();
//			parentId = eg.getParentId();
//			if(root.get(gid) == null && allGroup.get(parentId) == null){
//				root.put(gid, eg);
//			}
//		}
//		
//		for(Entry<Integer, EmployeeGroup> e : root.entrySet()){
//			empGroupList.add(e.getValue());
//		}
//		
//		// 显示ROOT节点
//		for (int i = 0; i < empGroupList.size(); i++) {
//			JSONObject res = createJsonObj(empGroupList.get(i),groupList);
//			if(res !=null )
//			{
//				if(treeChidren.length() > 0)
//				{
//					treeChidren.append(","+res.toString());
//				}
//				else
//				{
//					treeChidren.append(res.toString());
//				}
//			}
//		}
		
        /*
        // 树形节点存放map
        Map<String, Object> map = new HashMap<String, Object>();
        // 根节点
        map.put("parentId", 0);

        // 查询父节点下的所有组
        List<EmployeeGroup> empGroupList = collegeService.queryByParentId(map);
        
        // 显示ROOT节点
        for (int i = 0; i < empGroupList.size(); i++) {
            EmployeeGroup em=empGroupList.get(i);
            String[] ss = groupId.split(",");
            for (int j = 0; j < ss.length; j++)
            {
                if(em.getGroupId().toString().equals(ss[j])){
                    JSONObject emp = createJSONObject(empGroupList.get(i));
                    if(emp !=null )
                    {
                        if(treeChidren.length() > 0)
                        {
                            treeChidren.append(","+emp.toString());
                        }
                        else
                        {
                            treeChidren.append(emp.toString());
                        }
                    }
                }
            }
        }
        */
        return treeChidren.toString();
    }
    
//    public JSONObject createJsonObj(EmployeeGroup userGroup,List<EmployeeGroup> groupList){
//		JSONObject objData = new JSONObject(); 
//		if(userGroup != null)
//		{
//			objData.put("lable", userGroup.getGroupName());
//			objData.put("id",String.valueOf(userGroup.getGroupId()));
//			String chidren = getChild(userGroup.getGroupId(),groupList);
//			if(chidren != null)
//			{
//				objData.put("chidren", chidren);
//			}
//		}
//		return objData;
//	}
    
	public JSONObject createJSONObject(College college){
		JSONObject objData = new JSONObject(); 
		if(college != null)
		{
			objData.put("lable", college.getName());
			objData.put("id",String.valueOf(college.getId()));
//			String chidren = getChidren(college.getId());
//			if(chidren != null)
//			{
//				objData.put("chidren", chidren);
//			}
		}
		return objData;
	}
	
	public String getChild(Integer id,List<College> collegeList){
//		StringBuffer treeChidren = new StringBuffer();
//		JSONObject objData;
//		College userGroup = null;
//		String chidren;
//		List<College> list = new ArrayList<College>();
//		for(College eg : list){
//			if(eg.getParentId() == id.intValue()){
//				empGroupList.add(eg);
//			}
//		}
//		for(int j = 0; j < empGroupList.size(); j++)
//		{
//			userGroup = empGroupList.get(j);
//			objData = new JSONObject(); 
//			if(userGroup != null)
//			{
//				objData.put("lable", userGroup.getGroupName());
//				objData.put("id", String.valueOf(userGroup.getGroupId()));
//				chidren = getChild(userGroup.getGroupId(),groupList);
//				if(chidren != null)
//				{
//					objData.put("chidren", chidren);
//				}
//			}
//			if(treeChidren.length() > 0)
//			{
//				treeChidren.append(","+objData.toString());
//			}
//			else
//			{
//				treeChidren.append(objData.toString());
//			}
//		}
//		
		String temStr = null;
//		if(treeChidren.toString().length() > 0)
//		{
//			temStr = "["+treeChidren.toString()+"]";
//		}
		return temStr;
	}
	
	public  String getChidren(Integer id){
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		map.put("parentId", id);
//		List<EmployeeGroup> empGroupList = collegeService.queryByParentId(map);
//		StringBuffer treeChidren = new StringBuffer();
//		
//		for(int j = 0; j < empGroupList.size(); j++)
//		{
//			EmployeeGroup userGroup = empGroupList.get(j);
//			JSONObject objData = new JSONObject(); 
//			if(userGroup != null)
//			{
//				objData.put("lable", userGroup.getGroupName());
//				objData.put("id", String.valueOf(userGroup.getGroupId()));
//				String chidren = getChidren(userGroup.getGroupId());
//				if(chidren != null)
//				{
//					objData.put("chidren", chidren);
//				}
//			}
//			if(treeChidren.length() > 0)
//			{
//				treeChidren.append(","+objData.toString());
//			}
//			else
//			{
//				treeChidren.append(objData.toString());
//			}
//		}
//		
		String temStr = null;
//		if(treeChidren.toString().length() > 0)
//		{
//			temStr = "["+treeChidren.toString()+"]";
//		}
//		
		return temStr;
	}

}
