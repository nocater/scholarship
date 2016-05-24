package com.util.export.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import com.scholarship.module.account.Account;
import com.scholarship.module.college.College;
import com.scholarship.module.conf.AppConfig;
import com.scholarship.module.datas.Datas;
import com.scholarship.module.export.ExportCustom;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.scholarship.Scholarship;
import com.scholarship.service.account.AccountService;
import com.scholarship.service.apply.ApplyService;
import com.scholarship.service.college.CollegeService;
import com.scholarship.service.datas.DatasService;
import com.scholarship.service.grade.GradeService;
import com.scholarship.service.scholarship.ScholarshipService;
import com.util.StringUtil;

public class ExportTypeCustom extends Export {
//	ExportCustom es = new ExportCustom();
	
	/***
	 * 导出自定义所有数据
	 */
	public ExportTypeCustom(Account account, AccountService accountService,
			ApplyService applyService, CollegeService collegeService,
			GradeService gradeService, DatasService datasService,
			ScholarshipService scholarshipService) {
		super(account, accountService, applyService, collegeService, gradeService, datasService,
				scholarshipService);
		
		// TODO Auto-generated constructor stub
	}
	
	/***
	 *  导出自定义所有数据
	 */
	@SuppressWarnings({ "resource", "finally" })
	@Override
	public InputStream export(String year) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		if(account.getRole().getId()!=1){
			map.put("roleId", String.valueOf(account.getRole().getId()));//当前账户角色可以看到的审批
		}
		map.put("year", year);//年份
		map.put("status", "2");//审批已通过
		
//		String ids = es.getIds();
		String ids = "12";
		String[] l = null;
		if(StringUtil.isNotBlank(ids)){
			l = ids.split(",");
		}
		
		String filePath = AppConfig.ctx+"csvTemplate\\自定义表格"+year+".xls";
		InputStream input = null;
		try {
			OutputStream os = new FileOutputStream(filePath);
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			
			if(l!=null){
				for(String id : l){
					Scholarship s = scholarshipServie.queryById(Integer.parseInt(id));
					String tip = s.getCategory()+s.getLevel();
					map.put("scholarshipId", id);
					List<Account> accountList = applyService.queryAccountList(map);
					System.out.println("导出："+s.getCategory()+s.getLevel()+"  人数："+accountList.size());
					WritableSheet sheet = wwb.createSheet(tip, 0);
					this.werite(accountList, sheet, year, tip,s);
				}
			}
	        
			wwb.write();
	        wwb.close();
	        
	        input = new FileInputStream(filePath);
		} catch (IOException | WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Export Table has fail--");
		}finally{
			return input;
		}
	}
	
	private int werite(List<Account> accountList,WritableSheet sheet, String year, String category, Scholarship s) throws WriteException{
		String[] title_student =  {"序号","姓名","学号","密码","性别","QQ","电话","电子邮件"};
		String[] title_class   =  {"学院","班级","专业","学历","入学年份","年级","学年"};
		String[] title_datas   =  {"出生年月","身份证号","银行卡号","民族","东部","中部","西部","家庭住址","住址简写","离县城远近","月生活费","生源地贷款","爷爷","爷爷收入","爷爷健康","奶奶","奶奶收入","奶奶健康","父亲","父亲收入","父亲职业","父亲身体","母亲","母亲收入","母亲职业","母亲身体","兄弟姐妹","家庭收入","主要支出","结余","主要困难原因","变故","成绩排名","素质排名"};
		String[] title_scholarship   =  {"奖金种类","奖金等级","奖金金额"};
		WritableCellFormat cellFormat = new WritableCellFormat();
		Label label=null;
        Number number=null;
		
		int row = 4;
        int column = 0;
		for(;column<title_student.length+title_class.length+title_datas.length+title_scholarship.length;column++){
			sheet.setColumnView(column, 20); // 设置列的宽度 
		}
        
        WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 18,  
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);// 定义格式 字体 下划线 斜体 粗体 颜色  
        
        WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 13,  
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
        
        WritableFont wf_content = new WritableFont(WritableFont.ARIAL, 11,  
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
        
        label = new Label(0, 1, "黄冈师范学院自定义导出表("+year+"年"+category+")");
        cellFormat.setFont(wf_table);
        cellFormat.setAlignment(jxl.format.Alignment.CENTRE);//设置对齐方式
        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框  
        label.setCellFormat(cellFormat);
        sheet.addCell(label);
        sheet.mergeCells(0, 1, 7, 1);//合并大标题
        
        column = 0;
        //表头1
        for (int i = 0; i < title_student.length; i++,column++) {
        	label = new Label(column,row,title_student[i]);
        	cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_title);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
//	        cellFormat.setWrap(true);//自动换行
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
		}
        //表头2
        for (int i = 0; i < title_class.length;  i++,column++) {
        	label = new Label(column,row,title_class[i]);
        	cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_title);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
//	        cellFormat.setWrap(true);//自动换行
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
		}
        //表头3
        for (int i = 0; i < title_datas.length;  i++,column++) {
        	label = new Label(column,row,title_datas[i]);
        	cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_title);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
//	        cellFormat.setWrap(true);//自动换行
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
		}
        //表头4
        for (int i = 0; i < title_scholarship.length;  i++,column++) {
        	label = new Label(column,row,title_scholarship[i]);
        	cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_title);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
//	        cellFormat.setWrap(true);//自动换行
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
		}
        
		row++;
		for(Account a : accountList){
			column = 0;
//			System.out.println(a.getName());
//			String[] title_student =  {"序号","姓名","学号","密码","性别","QQ","电话","电子邮件"};
			//
//			label = new Label(column++, row, );
//			cellFormat = new WritableCellFormat();
//	        cellFormat.setFont(wf_content);
//	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
//	        label.setCellFormat(cellFormat);
//			sheet.addCell(label);
			
			//序号
			label = new Label(column++, row, String.valueOf(row-4));
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
	        sheet.addCell(label);
			
	        //姓名
			label = new Label(column++, row, a.getName());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//学号
			label = new Label(column++, row, a.getAccno());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//密码
			label = new Label(column++, row, a.getPassword());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//性别
			label = new Label(column++, row, a.getSex());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//QQ
			label = new Label(column++, row, a.getQq());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//电话
			label = new Label(column++, row, a.getPhone());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//邮箱
			label = new Label(column++, row, a.getEmail());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//*******************
			//*****学院班级信息******
			//*******************
			
//			String[] title_class   =  {"学院","班级","专业","学历","入学年份","年级","学年"};
			
			College c = a.getCollege();
			Grade g = a.getGrade();
			
			//学院
			label = new Label(column++, row, c.getName());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//班级
			label = new Label(column++, row, g.getName());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//专业
			label = new Label(column++, row, g.getMajor());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//学历
			label = new Label(column++, row, g.getEdubg());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//入学年份
			label = new Label(column++, row, g.getInyear());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//年级
			label = new Label(column++, row, g.getGrade());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//学年
			label = new Label(column++, row, String.valueOf(g.getStay()));
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			
			//*******************
			//*****申请信息******
			//*******************
			
//			String[] title_datas   =  {"身份证号","银行卡号","民族","东部","中部","西部","家庭住址","住址简写","离县城远近","月生活费","生源地贷款","爷爷","爷爷收入","爷爷健康","奶奶","奶奶收入","奶奶健康",
//			"父亲","父亲收入","父亲职业","父亲身体","母亲","母亲收入","母亲职业","母亲身体",
//			"兄弟姐妹","家庭收入","主要支出","结余","主要困难原因","变故","成绩排名","素质排名"};
			
			Datas d = datasService.queryByAccount(a, "0");
			
			//出生年月
			String birth = d.getIdnumber();
			birth=birth.substring(6,10)+"."+birth.substring(10,12);
			label = new Label(column++, row, birth);
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//身份证号
			label = new Label(column++, row, d.getIdnumber());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//银行卡号
			label = new Label(column++, row, d.getBankcard());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//民族
			label = new Label(column++, row, d.getNation());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//东部
			number = new Number(column++, row, 0);
			if(d.getArea().contains("东")){
				number = new Number(column-1, row, 1);
			}
			cellFormat = new WritableCellFormat();
			cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        number.setCellFormat(cellFormat);
			sheet.addCell(number);
			//中部
			number = new Number(column++, row, 0);
			if(d.getArea().contains("中")){
				number = new Number(column-1, row, 1);
			}
			cellFormat = new WritableCellFormat();
			cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        number.setCellFormat(cellFormat);
			sheet.addCell(number);
			//西部
			number = new Number(column++, row, 0);
			if(d.getArea().contains("西")){
				number = new Number(column-1, row, 1);
			}
			cellFormat = new WritableCellFormat();
			cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        number.setCellFormat(cellFormat);
			sheet.addCell(number);
			
			//家庭住址
			label = new Label(column++, row, d.getAddress());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//住址简写
			label = new Label(column++, row, d.getAddressX());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//县城远近
			label = new Label(column++, row, d.getDistance());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//月生活费
			label = new Label(column++, row, d.getExpenses());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//生源地贷款
			label = new Label(column++, row, d.getIsLoan().equals("1")?"是":"否");
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//爷爷
			label = new Label(column++, row, d.getName_grandfather());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//爷爷收入
			label = new Label(column++, row, d.getIn_grandfather());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//爷爷健康
			label = new Label(column++, row, d.getHealth_grandfather());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//奶奶
			label = new Label(column++, row, d.getName_grandmother());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//奶奶收入
			label = new Label(column++, row, d.getIn_grandmother());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//奶奶健康
			label = new Label(column++, row, d.getHealth_grandmother());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//父亲
			label = new Label(column++, row, d.getName_father());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//父亲收入
			label = new Label(column++, row, d.getIn_father());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//父亲职业
			label = new Label(column++, row, d.getCareer_father());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//父亲身体
			label = new Label(column++, row, d.getHealth_father());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//母亲
			label = new Label(column++, row, d.getName_mother());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//母亲收入
			label = new Label(column++, row, d.getIn_mother());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//母亲职业
			label = new Label(column++, row, d.getCareer_mother());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//母亲身体
			label = new Label(column++, row, d.getHealth_mother());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//兄弟姐妹
			label = new Label(column++, row, d.getOthers());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//家庭收入
			label = new Label(column++, row, d.getIn_family());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//主要支出
			label = new Label(column++, row, d.getOut_main());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//结余
			label = new Label(column++, row, d.getBalance());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//主要困难原因
			label = new Label(column++, row, d.getDescripe());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//变故
			label = new Label(column++, row, d.getAccident());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//成绩排名
			label = new Label(column++, row, d.getScore_place());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//素质排名
			label = new Label(column++, row, d.getQuality_place());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//*******************
			//*****申请信息******
			//*******************
			
//			String[] title_scholarship   =  {"奖金种类","奖金等级","奖金金额"};
			
			//奖金种类
			label = new Label(column++, row, s.getCategory());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);

			//奖金等级
			label = new Label(column++, row, s.getLevel());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);

			//拟发放金额
			label = new Label(column++, row, String.valueOf(s.getMoney()));
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			
			row++;
		}
		
		return row;
	}
	
	public String[] getTitles(){
		String[] titles =  {"序号"};
//		if(es.getName()==1){
//			titles[titles.length]="姓名";
//		}
//		if(es.getPassword()==1){
//			titles[titles.length]="密码";
//		}
//		if(es.getSex()==1){
//			titles[titles.length]="性别";
//		}
//		if(es.getQq()==1){
//			titles[titles.length]="QQ";
//		}
//		if(es.getPhone()==1){
//			titles[titles.length]="电话";
//		}
//		if(es.getEmail()==1){
//			titles[titles.length]="邮箱";
//		}
//		
//		
//		if(es.getCollege()==1){
//			titles[titles.length]="学院";
//		}
//		if(es.get_class()==1){
//			titles[titles.length]="班级";
//		}
//		if(es.getMajor()==1){
//			titles[titles.length]="专业";
//		}
//		if(es.getEdubg()==1){
//			titles[titles.length]="学历";
//		}
//		if(es.getInyear()==1){
//			titles[titles.length]="入学年份";
//		}
//		if(es.getGrade()==1){
//			titles[titles.length]="年级";
//		}
//		if(es.getStay()==1){
//			titles[titles.length]="学年";
//		}
//		
//		if(es.getIdnumber()==1){
//			titles[titles.length]="身份证号码";
//		}
//		if(es.getBankcard()==1){
//			titles[titles.length]="银行卡号";
//		}
//		if(es.getNation()==1){
//			titles[titles.length]="民族";
//		}
//		if(es.getArea()==1){
//			titles[titles.length]="地区";
//		}
//		if(es.getAddress()==1){
//			titles[titles.length]="家庭住址";
//		}
//		if(es.getAddressX()==1){
//			titles[titles.length]="住址简写";
//		}
//		if(es.getDistance()==1){
//			titles[titles.length]="县城远近";
//		}
//		if(es.getExpenses()==1){
//			titles[titles.length]="生活费";
//		}
//		if(es.getIsLoan()==1){
//			titles[titles.length]="生源地贷款";
//		}
//		if(es.getName_grandfather()==1){
//			titles[titles.length]="爷爷";
//		}
//		if(es.getIn_grandfather()==1){
//			titles[titles.length]="爷爷收入";
//		}
//		if(es.getHealth_grandfather()==1){
//			titles[titles.length]="爷爷健康";
//		}
//		if(es.getName_grandmother()==1){
//			titles[titles.length]="奶奶";
//		}
//		if(es.getIn_grandmother()==1){
//			titles[titles.length]="奶奶收入";
//		}
//		if(es.getHealth_grandmother()==1){
//			titles[titles.length]="奶奶健康";
//		}
//		if(es.getName_father()==1){
//			titles[titles.length]="父亲";
//		}
//		if(es.getIn_father()==1){
//			titles[titles.length]="父亲收入";
//		}
//		if(es.getCareer_father()==1){
//			titles[titles.length]="父亲职业";
//		}
//		if(es.getHealth_father()==1){
//			titles[titles.length]="父亲健康";
//		}
//		if(es.getOthers()==1){
//			titles[titles.length]="兄弟姐妹情况";
//		}
//		if(es.getIn_family()==1){
//			titles[titles.length]="家庭年收入";
//		}
//		if(es.getOut_main()==1){
//			titles[titles.length]="主要支出项";
//		}
//		if(es.getBalance()==1){
//			titles[titles.length]="结余";
//		}
//		if(es.getDescripe()==1){
//			titles[titles.length]="主要困难原因";
//		}
//		if(es.getAccident()==1){
//			titles[titles.length]="变故";
//		}
//		if(es.getScore_place()==1){
//			titles[titles.length]="成绩排名";
//		}
//		if(es.getQuality_place()==1){
//			titles[titles.length]="素质排名";
//		}
//		if(es.getCategory()==1){
//			titles[titles.length]="奖助学金种类";
//		}
//		if(es.getLevel()==1){
//			titles[titles.length]="等级";
//		}
//		if(es.getMoney()==1){
//			titles[titles.length]="拟发放金额";
//		}
		return titles;
	}
}
