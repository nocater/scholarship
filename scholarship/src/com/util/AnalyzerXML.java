package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import org.dom4j.Document;
import org.dom4j.Element;

import com.scholarship.module.account.Account;
import com.scholarship.module.college.College;
import com.scholarship.module.conf.AppConfig;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;
import com.scholarship.service.account.AccountService;
import com.scholarship.service.college.CollegeService;
import com.scholarship.service.grade.GradeService;

/**
 * Description: 账户批量导入导出xml解析
 * 
 * @author 陈帅
 * @Version 1.0
 * @Created at 2016-04-01
 * @Modified by
 */
public class AnalyzerXML
{
    private static Document doc;

	private static Element accountElement; //用户标签
    
    private static Element accountLoginNameElement; //用户登录账号
    
    /**
     * 用户批量导入xml解析
     * @param File upFile
     * @param EmployeeService accountService
     * @return Map<String, Object>
     */
    public static Map<String, Object> readXML(File upFile, AccountService accountService, CollegeService collegeService, GradeService gradeService)
    {
        
        List<String> errorList = new ArrayList<String>(); //导出信息集合
        List<Account> accountList = new ArrayList<Account>(); //人员集合
        List<Account> repeatAccountList = new ArrayList<Account>();//重复人员集合
        Map<String, Object> returnMap = new HashMap<String, Object>(); //用于存储人员集合、导出信息集合
        try {
			Workbook workbook = Workbook.getWorkbook(upFile);
			Sheet sheet = workbook.getSheet(0);
			int sheetSize = sheet.getRows();
			NumberCell num;
			Account account;
//			DateCell date;
//			Date time;
//			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 1; i < sheetSize; i++) {
				 //用于判断用户是否可添加的标识
		        boolean dispute = true;
		        //用户判断用户是否重复的标识
		        boolean repeat = false;
		                
		        account = new Account();
		        Role role = new Role();
		        role.setId(2);
		        account.setRole(role);//默认为学生账户
					
	        	//学生学号
				Cell no = sheet.getCell(0,i);
				//判断是否为空
				if(no.getType() !=CellType.EMPTY){
					String accno = no.getContents();
					//判断数据库是否已经存在该ID
					List<Account> list = accountService.queryByAccno(accno);
					if(list!=null&&list.size()>0){
						repeat = true;
						account.setId(list.get(0).getId());
					}
					account.setAccno(accno);
				}else{
					dispute = false;
					errorList.add("第"+i+"行学生学号丢失");
				}
				
				//用户姓名
				Cell name = sheet.getCell(1,i);
				if(name.getType() != CellType.EMPTY){
					String EmpName = name.getContents();
					account.setName(EmpName);
				}else{
					dispute = false;
					errorList.add("第"+i+"行学生姓名丢失");
				}
				
				//用户密码
				Cell passwd = sheet.getCell(2,i);
				if(passwd.getType() != CellType.EMPTY){
					String passWd = passwd.getContents();
					account.setPassword(MD5.getMD5Password(passWd));
				}else{
					account.setPassword(MD5.getMD5Password("123456"));
				}
				
				//所属学院
				Cell college = sheet.getCell(3, i);
				if(college.getType() != CellType.EMPTY){
					String str_college = college.getContents();
					List<College> list = collegeService.queryByName(str_college);
					if(list!=null&&list.size()>0){
						account.setCollege(list.get(0));
					}else{
						errorList.add("第"+i+"行学生学院未匹配");
					}
				}else{
					dispute = false;
					errorList.add("第"+i+"行学生学院丢失");
				}
				
				//所属班级
				Cell grade = sheet.getCell(4, i);
				if(grade.getType() != CellType.EMPTY){
					String str_grade = grade.getContents();
					List<Grade> list = gradeService.queryByName(str_grade);
					if(list!=null&&list.size()>0){
						account.setGrade(list.get(0));
					}else{
						errorList.add("第"+i+"行学生班级未匹配");
					}
				}else{
					dispute = false;
					errorList.add("第"+i+"行学生班级丢失");
				}
				
				//添加重复的
				if(repeat&&dispute){
					repeatAccountList.add(account);
				}
				
				//添加正确的
				if(dispute&&repeat == false){
					accountList.add(account);
				}
			}
			
			returnMap.put("errorList", errorList);
            returnMap.put("accountList", accountList);
            returnMap.put("repeatAccountList", repeatAccountList);
            System.out.println();
            return returnMap;
            
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
        
    }
    
    /**
     * 班级批量导入xml解析
     * @param File upFile
     * @param EmployeeService accountService
     * @return Map<String, Object>
     */
    public static Map<String, Object> readXML(File upFile, CollegeService collegeService, GradeService gradeService)
    {
        
        List<String> errorList = new ArrayList<String>(); //导出信息集合
        List<Grade> gradeList = new ArrayList<Grade>(); //班级集合
        List<Grade> repeatGradeList = new ArrayList<Grade>();//重复班级集合
        Map<String, Object> returnMap = new HashMap<String, Object>(); //用于存储人员集合、导出信息集合
        try {
			Workbook workbook = Workbook.getWorkbook(upFile);
			Sheet sheet = workbook.getSheet(0);
			int sheetSize = sheet.getRows();
			NumberCell num;
			Grade grade;
//			DateCell date;
//			Date time;
//			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
			for (int i = 1; i < sheetSize; i++) {
				//用于判断学院是否可添加的标识
		        boolean dispute = true;
		        //学院判断用户是否重复的标识
		        boolean repeat = false;
		                
		        grade = new Grade();

		        //名字
				Cell name = sheet.getCell(0,i);
				//判断是否为空
				if(name.getType() !=CellType.EMPTY){
					String g_name = name.getContents();
					if(StringUtil.isBlank(g_name)) dispute = false;
					//判断数据库是否已经存在该ID
					List<Grade> list = gradeService.queryByName(g_name);
					if(list!=null&&list.size()>0){
						repeat = true;
						grade.setId(list.get(0).getId());
					}
					grade.setName(g_name);
				}else{
					dispute = false;
					errorList.add("第"+i+"行班级名字丢失");
				}
				
				//专业
				Cell major = sheet.getCell(1,i);
				if(major.getType() != CellType.EMPTY){
					String g_major = major.getContents();
					grade.setMajor(g_major);
				}else{
					dispute = false;
					errorList.add("第"+i+"行学院专业丢失");
				}
				
				//学院
				Cell college = sheet.getCell(2,i);
				if(college.getType() != CellType.EMPTY){
					String g_college = college.getContents();
					List<College> list = collegeService.queryByName(g_college);
					if(list!=null&&list.size()>0){
						grade.setCollege(list.get(0));
					}
				}else{
					
				}
				
				//学历
				Cell edubg = sheet.getCell(3, i);
				if(edubg.getType() != CellType.EMPTY){
					String g_edubg = edubg.getContents();
					if(g_edubg.contains("本科")){
						grade.setEdubg("本科");
					}else if(g_edubg.contains("专科")){
						grade.setEdubg("专科");
					}
				}else{
					dispute = false;
					errorList.add("第"+i+"行班级学历丢失");
				}
				
				//入学年份
				Cell _grade = sheet.getCell(4, i);
				if(_grade.getType() != CellType.EMPTY){
					String g_grade = _grade.getContents();
					grade.setGrade(g_grade);
				}else{
					dispute = false;
					errorList.add("第"+i+"行班级入学年份丢失");
				}
				
				//入学年份
				Cell inyear = sheet.getCell(5, i);
				if(inyear.getType() != CellType.EMPTY){
					String g_inyear = inyear.getContents();
					grade.setInyear(g_inyear);
				}else{
					dispute = false;
					errorList.add("第"+i+"行班级年级丢失");
				}
				
				//学年制
				Cell stay = sheet.getCell(6, i);
				if(stay.getType() != CellType.EMPTY){
					NumberCell n_stay = (NumberCell)stay;
					double g_stay = n_stay.getValue();
					grade.setStay((int)g_stay);
				}else{
					dispute = false;
					errorList.add("第"+i+"行班级学年制丢失");
				}
				
				//状态
				Cell status = sheet.getCell(7, i);
				if(status.getType() != CellType.EMPTY){
					String g_status = status.getContents();
					if(StringUtil.isBlank(g_status)||g_status.contains("激活")||g_status.contains("1")){
						grade.setStatus(1);
					}else {
						grade.setStatus(-1);
					}
				}else{
					grade.setStatus(1);
				}
				
				grade.setMemo("导入创建");
				
				//添加重复的
				if(repeat&&dispute){
					repeatGradeList.add(grade);
				}
				
				//添加正确的   if(dispute&&repeat == false){
				if(dispute){
					gradeList.add(grade);
				}
				
			}
			
			returnMap.put("errorList", errorList);
            returnMap.put("gradeList", gradeList);
            returnMap.put("repeatGradeList", repeatGradeList);
            System.out.println();
            return returnMap;
            
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
        
    }
    
    /**
     * 判断标签和标签里的值是否为null
     */
    public static boolean dealAccount(Element element)
    {
        boolean bool = false;
        if (element != null)
        {
            if (StringUtil.isNotBlank(element.getTextTrim()))
            {
                bool = true;
            }
        }
        
        return bool;
    }
    
    /**
     * 验证accountRoleElement标签格式是否合法
     */
    public static boolean dealEmployeeRole(Element element)
    {
        boolean bool = false;
        //验证标签是否为null
        if (element != null)
        {
            //验证内容是否为null
            if (StringUtil.isNotBlank(element.getTextTrim()))
            {
                //验证内容格式是否合法
                if (element.getTextTrim().indexOf("//") != -1)
                {
                    bool = true;
                }
            }
        }
        
        return bool;
    }
    
    /**
     * 生成用户批量导出xml
     * @param File upFile
     * @param EmployeeService accountService
     * @return Map<String, Object>
     * @throws IOException 
     * @throws WriteException 
     */
    public static InputStream writeXML(List<Account> accountList) 
    		throws IOException, WriteException
    {
//    	String[] title = {"编号","姓名","邮件","所属公司或组织","手机号码","固定电话","通信地址","备注","是否是临时用户","有效期","状态","登录名","认证方式","密码","组名称","下次登录修改密码","创建日期","注册者IP","密码有效期","密码是否过期","更新日期","锁定开始","重置开始","登录失败","修改密码时间","是否显示","远程桌面","指纹设备","指纹注册结果","拼音","授权编号","USBKey","登录次数","工作开始时间","工作结束时间","工作开始日","工作结束日","金税","指纹模板","神马设备组","国泰白锁","USB证书锁","格尔网关","动态令牌","用户角色","用户策略","锁定策略","密码策略","Google令牌密钥"};
        String filePath = AppConfig.ctx+"csvTemplate/account.xls";
//        WritableWorkbook wwb;
//        OutputStream os = new FileOutputStream(filePath);
//        wwb = Workbook.createWorkbook(os);
//        WritableSheet sheet = wwb.createSheet("用户信息", 0);
//        Label label;
//        Number number;
//        SimpleDateFormat dateTime = new SimpleDateFormat("yyyy-MM-dd");
//        for (int i = 0; i < title.length; i++) {
//			label = new Label(i,0,title[i]);
//			sheet.addCell(label);
//		}
//        for (int i = 0; i < accountList.size(); i++) {
//        	//用户ID
//			number = new Number(0,i+1,accountList.get(i).getEmpId());
//			sheet.addCell(number);
//			//用户名称
//			label = new Label(1,i+1,accountList.get(i).getEmpName());
//			sheet.addCell(label);
//			//用户邮件
//			label = new Label(2,i+1,accountList.get(i).getEmpEmail());
//			sheet.addCell(label);
//			//所属公司或组织
//			label = new Label(3,i+1,accountList.get(i).getEmpOrganization());
//			sheet.addCell(label);
//			//手机号码
//			label = new Label(4,i+1,accountList.get(i).getEmpMobile());
//			sheet.addCell(label);
//			//固定电话
//			label = new Label(5,i+1,accountList.get(i).getEmpTel());
//			sheet.addCell(label);
//			//通信地址
//			label = new Label(6,i+1,accountList.get(i).getEmpAddress());
//			sheet.addCell(label);
//			label = new Label(47,i+1,stringBuffer_account_pwd.toString());
//			sheet.addCell(label);
//			//Google动态令牌密钥
//			label = new Label(48,i+1,accountList.get(i).getSecretKey());
//			sheet.addCell(label);
//		}
//        wwb.write();
//        wwb.close();
        InputStream input = new FileInputStream(filePath);
        return input;
    }
    
}
