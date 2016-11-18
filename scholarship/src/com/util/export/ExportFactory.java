package com.util.export;

import com.scholarship.module.account.Account;
import com.scholarship.service.account.AccountService;
import com.scholarship.service.apply.ApplyService;
import com.scholarship.service.college.CollegeService;
import com.scholarship.service.datas.DatasService;
import com.scholarship.service.grade.GradeService;
import com.scholarship.service.scholarship.ScholarshipService;
import com.util.export.impl.ExportType1;
import com.util.export.impl.ExportType2;
import com.util.export.impl.ExportType3;
import com.util.export.impl.ExportType4;
import com.util.export.impl.ExportType5;
import com.util.export.impl.ExportType6;
import com.util.export.impl.ExportType7;
import com.util.export.impl.ExportType8;
import com.util.export.impl.ExportTypeCustom;

public class ExportFactory {
	public static ExportXSL getInstance(String type,Account account, AccountService accountService, ApplyService applyService,
			CollegeService collegeService, GradeService gradeService,
			DatasService datasService, ScholarshipService scholarshipService) {
		ExportXSL export = null;
		switch (type) {
		case "1":
			export = new ExportType1(account,accountService, applyService, collegeService, gradeService, datasService, scholarshipService);
			break;
			
		case "2":
			export = new ExportType2(account,accountService, applyService, collegeService, gradeService, datasService, scholarshipService);
			break;
			
		case "3":
			export = new ExportType3(account,accountService, applyService, collegeService, gradeService, datasService, scholarshipService);
			break;	
			
		case "4":
			export = new ExportType4(account,accountService, applyService, collegeService, gradeService, datasService, scholarshipService);
			break;	
		
		case "5":
			export = new ExportType5(account,accountService, applyService, collegeService, gradeService, datasService, scholarshipService);
			break;
		
		case "6":
			export = new ExportType6(account,accountService, applyService, collegeService, gradeService, datasService, scholarshipService);
			break;
			
		case "7":
			export = new ExportType7(account,accountService, applyService, collegeService, gradeService, datasService, scholarshipService);
			break;
		
		case "8":
			export = new ExportType8(account,accountService, applyService, collegeService, gradeService, datasService, scholarshipService);
			break;
			
		case "10":
			export = new ExportTypeCustom(account,accountService, applyService, collegeService, gradeService, datasService, scholarshipService);
			break;
			
		default:
			break;
		}
		return export;
	}
}
