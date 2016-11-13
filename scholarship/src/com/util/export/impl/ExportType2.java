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

public class ExportType2 extends Export {
	
	private final String SCHOLARSHIP_ID  = "2";//国家助学金ID 2 3 4
	private final String SCHOLARSHIP_ID2 = "3";//国家助学金ID 2 3 4
	private final String SCHOLARSHIP_ID3 = "4";//国家助学金ID 2 3 4
	
	/***
	 * 导出【附表5湖北省普通高校国家助学金名单备案表】
	 */
	public ExportType2(Account account, AccountService accountService,
			ApplyService applyService, CollegeService collegeService,
			GradeService gradeService, DatasService datasService,
			ScholarshipService scholarshipService) {
		super(account, accountService, applyService, collegeService, gradeService, datasService,
				scholarshipService);
		
		// TODO Auto-generated constructor stub
	}
	
	
	/***
	 * 导出【附表5湖北省普通高校国家助学金名单备案表】
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
		map.put("scholarshipId", SCHOLARSHIP_ID);
		map.put("status", "2");//审批已通过
		List<Account> accountList = applyService.queryAccountList(map);
		map.put("scholarshipId", SCHOLARSHIP_ID2);
		List<Account> accountList2 = applyService.queryAccountList(map);
		map.put("scholarshipId", SCHOLARSHIP_ID3);
		List<Account> accountList3 = applyService.queryAccountList(map);
		
//		List<Account> accountList = new ArrayList<Account>();
//		accountList.add(accountService.queryById(28));
		
		String[] title = {"序号","学生姓名","性别","出生年月","民族","入学年份","身份证号","专业","一等","二等","三等","湖北省生源","外省生源","联系方式"};
		String filePath = AppConfig.ctx+"csvTemplate\\附表2 湖北省普通高校国家助学金名单备案表"+year+".xls";
		InputStream input = null;
		try {
			OutputStream os = new FileOutputStream(filePath);
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			WritableSheet sheet = wwb.createSheet("sheet1", 0);
			WritableCellFormat cellFormat = new WritableCellFormat();
			Label label=null;
	        Number number=null;
	        int row = 4;
	        
	        sheet.setColumnView(0, 10); // 设置列的宽度 
	        sheet.setColumnView(1, 15); // 设置列的宽度 
	        sheet.setColumnView(2, 10); // 设置列的宽度 
	        sheet.setColumnView(3, 10); // 设置列的宽度 
	        sheet.setColumnView(4, 10); // 设置列的宽度 
	        sheet.setColumnView(5, 10); // 设置列的宽度 
	        sheet.setColumnView(6, 25); // 设置列的宽度 
	        sheet.setColumnView(7, 20); // 设置列的宽度 
	        sheet.setColumnView(8, 5); // 设置列的宽度 
	        sheet.setColumnView(9, 5); // 设置列的宽度 
	        sheet.setColumnView(10, 5); // 设置列的宽度 
	        sheet.setColumnView(11, 10); // 设置列的宽度 
	        sheet.setColumnView(12, 10); // 设置列的宽度 
	        sheet.setColumnView(13, 15); // 设置列的宽度 
//	        sheet.setColumnView(14, 15); // 设置列的宽度 
	        
	        
	        WritableFont wf_table = new WritableFont(WritableFont.ARIAL, 18,  
                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);// 定义格式 字体 下划线 斜体 粗体 颜色  
	        
	        WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 14,  
                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        
	        WritableFont wf_content = new WritableFont(WritableFont.ARIAL, 11,  
                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.BLACK);
	        
	        label = new Label(0, 1, "湖北省普通高校国家助学金名单备案表("+year+"年)");
	        cellFormat.setFont(wf_table);
	        cellFormat.setAlignment(jxl.format.Alignment.CENTRE);//设置对齐方式
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框  
	        label.setCellFormat(cellFormat);
	        sheet.addCell(label);
	        sheet.mergeCells(0, 1, 13, 1);
	        
	        label = new Label(0, 3, "学校公章");
	        cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_title);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
	        sheet.addCell(label);
	        sheet.mergeCells(0, 3, 13, 3);
	        
	        //表头
	        for (int i = 0; i < 8; i++) {
	        	label = new Label(i,row,title[i]);
	        	cellFormat = new WritableCellFormat();
		        cellFormat.setFont(wf_title);
		        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
		        label.setCellFormat(cellFormat);
				sheet.addCell(label);
				sheet.mergeCells(i, row, i, row+1);
			}
	        
	        label = new Label(8, row, "受助等次");
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
	        sheet.addCell(label);
	        sheet.mergeCells(8, row, 10, row);
	        
	        label = new Label(11, row, "建档立卡情况");
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
	        sheet.addCell(label);
	        sheet.mergeCells(11, row, 12, row);
	        
	        //联系方式
	        label = new Label(13,row,title[13]);
        	cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_title);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			sheet.mergeCells(13, row, 13, row+1);
			 
	        row++;
	        for(int i = 8;i<13;i++){
	        	label = new Label(i,row,title[i]);
	        	cellFormat = new WritableCellFormat();
		        cellFormat.setFont(wf_content);
		        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
		        label.setCellFormat(cellFormat);
				sheet.addCell(label);
	        }
			row++;
			
			//	分别写入一等奖 二等奖 三等奖
			row = this.werite(accountList, sheet, cellFormat, wf_content, label, number, SCHOLARSHIP_ID, row);
			row = this.werite(accountList2, sheet, cellFormat, wf_content, label, number, SCHOLARSHIP_ID2, row);
			row = this.werite(accountList3, sheet, cellFormat, wf_content, label, number, SCHOLARSHIP_ID3, row);
	        
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
	
	private int werite(List<Account> accountList,WritableSheet sheet,
				WritableCellFormat cellFormat,
				WritableFont wf_content,
				Label label,
				Number number,
				String ss,
				int row) throws WriteException{
		for(Account a : accountList){
//			System.out.println(a.getName());
			
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
			
			//性别
			label = new Label(2, row, a.getSex());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//出生年月
			String s = d.getIdnumber();
			s=s.substring(6,10)+"."+s.substring(10,12);
			label = new Label(3, row, s);
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//民族
			label = new Label(4, row, d.getNation());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//入学年份
			label = new Label(5, row, a.getGrade().getInyear());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//公民身份证号
			label = new Label(6, row, d.getIdnumber());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//专业
			label = new Label(7, row, a.getGrade().getMajor());
			cellFormat = new WritableCellFormat();
	        cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        label.setCellFormat(cellFormat);
			sheet.addCell(label);
			
			//一等
			number = new Number(8, row, 0);
			if(ss.equals(SCHOLARSHIP_ID)){
				number = new Number(8, row, 1);
			}
			cellFormat = new WritableCellFormat();
			cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        number.setCellFormat(cellFormat);
			sheet.addCell(number);
			//二等
			number = new Number(9, row, 0);
			if(ss.equals(SCHOLARSHIP_ID2)){
				number = new Number(9, row, 1);
			}
			cellFormat = new WritableCellFormat();
			cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        number.setCellFormat(cellFormat);
			sheet.addCell(number);
			//三等
			number = new Number(10, row, 0);
			if(ss.equals(SCHOLARSHIP_ID3)){
				number = new Number(10, row, 1);
			}
			cellFormat = new WritableCellFormat();
			cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        number.setCellFormat(cellFormat);
			sheet.addCell(number);
			//东部 改为 省内生源
			number = new Number(11, row, 0);
			if(d.getArea().contains("省内")){
				number = new Number(11, row, 1);
			}
			cellFormat = new WritableCellFormat();
			cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        number.setCellFormat(cellFormat);
			sheet.addCell(number);
			//中部  改为 省外生源
			number = new Number(12, row, 0);
			if(d.getArea().contains("省外")){
				number = new Number(12, row, 1);
			}
			cellFormat = new WritableCellFormat();
			cellFormat.setFont(wf_content);
	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
	        number.setCellFormat(cellFormat);
			sheet.addCell(number);
			//西部
//			number = new Number(13, row, 0);
//			if(d.getArea().contains("西")){
//				number = new Number(13, row, 1);
//			}
//			cellFormat = new WritableCellFormat();
//			cellFormat.setFont(wf_content);
//	        cellFormat.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //设置边框
//	        number.setCellFormat(cellFormat);
//			sheet.addCell(number);
			
			//联系方式
			label = new Label(13, row, a.getPhone());
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
