package com.util.export.impl;

import java.io.File;
import java.io.InputStream;

import com.scholarship.module.account.Account;
import com.scholarship.service.account.AccountService;
import com.scholarship.service.apply.ApplyService;
import com.scholarship.service.college.CollegeService;
import com.scholarship.service.datas.DatasService;
import com.scholarship.service.grade.GradeService;
import com.scholarship.service.scholarship.ScholarshipService;
import com.util.export.ExportXSL;

public abstract class Export implements ExportXSL {
	protected AccountService accountService;
	protected ApplyService applyService;
	protected CollegeService collegeService;
	protected GradeService gradeService;
	protected DatasService datasService;
	protected ScholarshipService scholarshipServie;
	protected Account account;
	protected File fileTemplate;
	protected File file;
	
	
	public Export(Account account, AccountService accountService, ApplyService applyService,
			CollegeService collegeService, GradeService gradeService,
			DatasService datasService, ScholarshipService scholarshipServie) {
		super();
		this.account = account;
		this.accountService = accountService;
		this.applyService = applyService;
		this.collegeService = collegeService;
		this.gradeService = gradeService;
		this.datasService = datasService;
		this.scholarshipServie = scholarshipServie;
	}

	@Override
	public abstract InputStream export(String year);

	public AccountService getAccountService() {
		return accountService;
	}


	public ApplyService getApplyService() {
		return applyService;
	}


	public CollegeService getCollegeService() {
		return collegeService;
	}


	public GradeService getGradeService() {
		return gradeService;
	}


	public DatasService getDatasService() {
		return datasService;
	}


	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}


	public void setApplyService(ApplyService applyService) {
		this.applyService = applyService;
	}


	public void setCollegeService(CollegeService collegeService) {
		this.collegeService = collegeService;
	}


	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}


	public void setDatasService(DatasService datasService) {
		this.datasService = datasService;
	}

	public ScholarshipService getScholarshipServie() {
		return scholarshipServie;
	}

	public void setScholarshipServie(ScholarshipService scholarshipServie) {
		this.scholarshipServie = scholarshipServie;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public File getFileTemplate() {
		return fileTemplate;
	}

	public void setFileTemplate(File fileTemplate) {
		this.fileTemplate = fileTemplate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
