package com.util.export.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
import com.scholarship.module.conf.AppConfig;
import com.scholarship.module.datas.Datas;
import com.scholarship.service.account.AccountService;
import com.scholarship.service.apply.ApplyService;
import com.scholarship.service.college.CollegeService;
import com.scholarship.service.datas.DatasService;
import com.scholarship.service.grade.GradeService;
import com.scholarship.service.scholarship.ScholarshipService;

public class ExportType1 extends Export {
	
	private final String SCHOLARSHIP_ID = "5";//国家励志奖学金ID
	
	public ExportType1(Account account, AccountService accountService,
			ApplyService applyService, CollegeService collegeService,
			GradeService gradeService, DatasService datasService,
			ScholarshipService scholarshipService) {
		super(account, accountService, applyService, collegeService, gradeService, datasService,
				scholarshipService);
		
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings({ "resource", "finally" })
	@Override
	public InputStream export(String year) {
		// TODO Auto-generated method stub
		Map<String,String> map = new HashMap<String,String>();
		if(account.getRole().getId()!=1){
			map.put("roleId", String.valueOf(account.getRole().getId()));//当前账户角色可以看到的审批
		}
		map.put("year", year);//年份
		map.put("scholarshipId", SCHOLARSHIP_ID);
		map.put("status", "2");//审批已通过
		List<Account> accountList = applyService.queryAccountList(map);
		
//		List<Account> accountList = new ArrayList<Account>();
//		accountList.add(accountService.queryById(28));
		
		String[] title = {"序号","学生姓名","公民身份证号","院系","专业","学号","性别","民族","入学年份","联系方式"};
		String filePath = AppConfig.ctx+"csvTemplate\\附表4湖北省高校国家励志奖学金获奖学生初审名单表"+year+".xls";
		InputStream input = null;
		try {
			OutputStream os = new FileOutputStream(filePath);
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet sheet = wwb.createSheet("sheet1", 0);
			WritableCellFormat cellFormat = new WritableCellFormat();
			Label label;
	        Number number;
	        int row = 4;
	        
	        sheet.setColumnView(0, 10); // 设置列的宽度 
	        sheet.setColumnView(1, 15); // 设置列的宽度 
	        sheet.setColumnView(2, 25); // 设置列的宽度 
	        sheet.setColumnView(3, 25); // 设置列的宽度 
	        sheet.setColumnView(4, 20); // 设置列的宽度 
	        sheet.setColumnView(5, 20); // 设置列的宽度 
	        sheet.setColumnView(6, 10); // 设置列的宽度 
	        sheet.setColumnView(7, 10); // 设置列的宽度 
	        sheet.setColumnView(8, 15); // 设置列的宽度 
	        sheet.setColumnView(9, 15); // 设置列的宽度 
	        
	        
	        WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 18,  
                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);// 定义格式 字体 下划线 斜体 粗体 颜色  
	        
	        WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 14,  
                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        
	        WritableFont wf_content = new WritableFont(WritableFont.ARIAL, 11,  
                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        
	        label = new Label(0, 1, "湖北省高校国家励志奖学金获奖学生初审名单表");
	        cellFormat.setFont(wf_table);
	        cellFormat.setAlignment(jxl.format.Alignment.CENTRE);//设置对齐方式
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框  
	        label.setCellFormat(cellFormat);
	        sheet.addCell(label);
	        sheet.mergeCells(0, 1, 9, 1);
	        
	        label = new Label(0, 3, "学校公章");
	        cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_title);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
	        sheet.addCell(label);
	        sheet.mergeCells(0, 3, 6, 3);
	        
	        label = new Label(7, 3, "填表日期");
	        cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_title);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
	        sheet.addCell(label);
	        sheet.mergeCells(7, 3, 9, 3);
	        
	        //表头
	        for (int i = 0; i < title.length; i++) {
	        	label = new Label(i,row,title[i]);
	        	cellFormat = new WritableCellFormat();
		        cellFormat.setFont(wf_title);
		        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
		        label.setCellFormat(cellFormat);
				sheet.addCell(label);
			}
	        row++;
			for(Account a : accountList){
				System.out.println(a.getName());
				
				Datas d = datasService.queryByAccount(a, "0");
				//序号
				label = new Label(0, row, String.valueOf(row-5));
				cellFormat = new WritableCellFormat();
		        cellFormat.setFont(wf_content);
		        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
		        label.setCellFormat(cellFormat);
		        sheet.addCell(label);
				//姓名
				label = new Label(1, row, a.getName());
				cellFormat = new WritableCellFormat();
		        cellFormat.setFont(wf_content);
		        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
		        label.setCellFormat(cellFormat);
				sheet.addCell(label);
				//公民身份证号
				label = new Label(2, row, d.getIdnumber());
				cellFormat = new WritableCellFormat();
		        cellFormat.setFont(wf_content);
		        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
		        label.setCellFormat(cellFormat);
				sheet.addCell(label);
				//院系
				label = new Label(3, row, a.getCollege().getName());
				cellFormat = new WritableCellFormat();
		        cellFormat.setFont(wf_content);
		        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
		        label.setCellFormat(cellFormat);
				sheet.addCell(label);
				//专业
				label = new Label(4, row, a.getGrade().getMajor());
				cellFormat = new WritableCellFormat();
		        cellFormat.setFont(wf_content);
		        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
		        label.setCellFormat(cellFormat);
				sheet.addCell(label);
				//学号
				label = new Label(5, row, a.getAccno());
				cellFormat = new WritableCellFormat();
		        cellFormat.setFont(wf_content);
		        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
		        label.setCellFormat(cellFormat);
				sheet.addCell(label);
				//性别
				label = new Label(6, row, a.getSex());
				cellFormat = new WritableCellFormat();
		        cellFormat.setFont(wf_content);
		        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
		        label.setCellFormat(cellFormat);
				sheet.addCell(label);
				//民族
				label = new Label(7, row, "汉族");
				cellFormat = new WritableCellFormat();
		        cellFormat.setFont(wf_content);
		        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
		        label.setCellFormat(cellFormat);
				sheet.addCell(label);
				//入学年份
				label = new Label(8, row, a.getGrade().getGrade());
				cellFormat = new WritableCellFormat();
		        cellFormat.setFont(wf_content);
		        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
		        label.setCellFormat(cellFormat);
				sheet.addCell(label);
				//联系方式
				label = new Label(9, row, a.getPhone());
				cellFormat = new WritableCellFormat();
		        cellFormat.setFont(wf_content);
		        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
		        label.setCellFormat(cellFormat);
				sheet.addCell(label);
				
				row++;
			}
			wwb.write();
	        wwb.close();
	        input = new FileInputStream(filePath);
		} catch (IOException | WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return input;
		}
	}

}
