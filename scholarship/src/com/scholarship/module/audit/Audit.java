package com.scholarship.module.audit;


import java.util.Date;

import com.scholarship.module.account.Account;

public class Audit {
	private	int id;
	private int 	accountId;
	private Account account;
	private String  accountName;
	private Date	operationTime;
	private String	operation;
	private String	note;
	private String  sourceIP;
	
	public Audit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Audit(int id, int accountId, Account account, String accountName,
			Date operationTime, String operation, String note, String sourceIP) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.account = account;
		this.accountName = accountName;
		this.operationTime = operationTime;
		this.operation = operation;
		this.note = note;
		this.sourceIP = sourceIP;
	}

	public int getId() {
		return id;
	}

	public int getAccountId() {
		return accountId;
	}

	public Account getAccount() {
		return account;
	}

	public String getAccountName() {
		return accountName;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public String getOperation() {
		return operation;
	}

	public String getNote() {
		return note;
	}

	public String getSourceIP() {
		return sourceIP;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setSourceIP(String sourceIP) {
		this.sourceIP = sourceIP;
	}
}
