package com.scholarship.module.apply;

import java.sql.Date;

import com.scholarship.module.account.Account;
import com.scholarship.module.scholarship.Scholarship;

public class Apply {
	private int 	id;//id
	private Account account;//学生账户
	private int 	status;//审批状态
	private String 	year;//申请年份
	private Account	approve_Account;//审批账户
	private Scholarship scholarship;//审批奖学金
	private Date	createDate;
	private Date	updateDate;
	
	public Apply() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Apply(int id, Account account, int status, String year,
			Account approve_Account, Scholarship scholarship, Date createDate,
			Date updateDate) {
		super();
		this.id = id;
		this.account = account;
		this.status = status;
		this.year = year;
		this.approve_Account = approve_Account;
		this.scholarship = scholarship;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public int getId() {
		return id;
	}

	public Account getAccount() {
		return account;
	}

	public int getStatus() {
		return status;
	}

	public String getYear() {
		return year;
	}

	public Account getApprove_Account() {
		return approve_Account;
	}

	public Scholarship getScholarship() {
		return scholarship;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setApprove_Account(Account approve_Account) {
		this.approve_Account = approve_Account;
	}

	public void setScholarship(Scholarship scholarship) {
		this.scholarship = scholarship;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
}
