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
import com.scholarship.module.scholarship.Scholarship;
import com.scholarship.service.account.AccountService;
import com.scholarship.service.apply.ApplyService;
import com.scholarship.service.college.CollegeService;
import com.scholarship.service.datas.DatasService;
import com.scholarship.service.grade.GradeService;
import com.scholarship.service.scholarship.ScholarshipService;

public class ExportType4 extends Export {
	
	private final String SCHOLARSHIP_ID1 = "9";	//明珠学子励志奖学金ID 9 10 11
	private final String SCHOLARSHIP_ID2 = "10";//明珠学子励志奖学金ID 9 10 11
	private final String SCHOLARSHIP_ID3 = "11";//明珠学子励志奖学金ID 9 10 11
	
	/***
	 * 导出【附件2：黄冈师范学院"明珠学子"励志奖学金获奖学生名单登记表】
	 */
	public ExportType4(Account account, AccountService accountService,
			ApplyService applyService, CollegeService collegeService,
			GradeService gradeService, DatasService datasService,
			ScholarshipService scholarshipService) {
		super(account, accountService, applyService, collegeService, gradeService, datasService,
				scholarshipService);
		
		// TODO Auto-generated constructor stub
	}
	
	/***
	 * 导出【附件2：黄冈师范学院"明珠学子"励志奖学金获奖学生名单登记表】
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
		
		map.put("scholarshipId", SCHOLARSHIP_ID1);
		List<Account> accountList1 = applyService.queryAccountList(map);
		map.put("scholarshipId", SCHOLARSHIP_ID2);
		List<Account> accountList2 = applyService.queryAccountList(map);
		map.put("scholarshipId", SCHOLARSHIP_ID3);
		List<Account> accountList3 = applyService.queryAccountList(map);
		
//		List<Account> accountList = new ArrayList<Account>();
//		accountList.add(accountService.queryById(28));
		
		
		String filePath = AppConfig.ctx+"csvTemplate\\黄冈师范学院'明珠学子'励志奖学金获奖学生名单登记表"+year+".xls";
		InputStream input = null;
		try {
			OutputStream os = new FileOutputStream(filePath);
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet sheet1 = wwb.createSheet("一等奖学金", 0);
			WritableSheet sheet2 = wwb.createSheet("二等奖学金", 0);
			WritableSheet sheet3 = wwb.createSheet("三等奖学金", 0);
			
	        
			//	分别写入一等奖 二等奖 三等奖
			this.werite(accountList1, sheet1, year, "一等奖学金",scholarshipServie.queryById(Integer.parseInt(SCHOLARSHIP_ID1)).getMoney());
			System.out.println(scholarshipServie.queryById(Integer.parseInt(SCHOLARSHIP_ID2)).getMoney());
			this.werite(accountList2, sheet2, year, "二等奖学金", scholarshipServie.queryById(Integer.parseInt(SCHOLARSHIP_ID2)).getMoney());
			this.werite(accountList3, sheet3, year, "三等奖学金", scholarshipServie.queryById(Integer.parseInt(SCHOLARSHIP_ID3)).getMoney());
	        
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
	
	private int werite(List<Account> accountList,WritableSheet sheet, String year, String category, int money) throws WriteException{
		String[] title = {"序号","姓名","身份证号","院系","专业","学号","性别","民族","入学年月","联系方式"};
		WritableCellFormat cellFormat = new WritableCellFormat();
		Label label=null;
        Number number=null;
		
		int row = 4;
        
        sheet.setColumnView(0, 10); // 设置列的宽度 
        sheet.setColumnView(1, 10); // 设置列的宽度 
        sheet.setColumnView(2, 25); // 设置列的宽度 
        sheet.setColumnView(3, 25); // 设置列的宽度 
        sheet.setColumnView(4, 20); // 设置列的宽度 
        sheet.setColumnView(5, 20); // 设置列的宽度 
        sheet.setColumnView(6, 10); // 设置列的宽度 
        sheet.setColumnView(7, 10); // 设置列的宽度 
        sheet.setColumnView(8, 10); // 设置列的宽度 
        sheet.setColumnView(9, 20); // 设置列的宽度 
        
        
        WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 18,  
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);// 定义格式 字体 下划线 斜体 粗体 颜色  
        
        WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 14,  
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
        
        WritableFont wf_content = new WritableFont(WritableFont.ARIAL, 11,  
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
        
        label = new Label(0, 1, "黄冈师范学院学年明珠学子励志奖学金获奖学生名单登记表("+year+"年"+category+")");
        cellFormat.setFont(wf_table);
        cellFormat.setAlignment(jxl.format.Alignment.CENTRE);//设置对齐方式
        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框  
        label.setCellFormat(cellFormat);
        sheet.addCell(label);
        sheet.mergeCells(0, 1, 9, 1);//合并大标题
        
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
	        cellFormat.setWrap(true);//自动换行
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
		}
        
		row++;
		
		for(Account a : accountList){
//			System.out.println(a.getId());
			
			Datas d = datasService.queryByAccount(a, "0");
			//序号
			label = new Label(0, row, String.valueOf(row-4));
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
			label = new Label(7, row, d.getNation());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//入学年份
			label = new Label(8, row, a.getGrade().getInyear());
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
		
		return row;
	}
}
