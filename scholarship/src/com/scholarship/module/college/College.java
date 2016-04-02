package com.scholarship.module.college;

import java.sql.Date;

public class College {
	private int id;				//id
	private String name;		//学院名字
	private String memo;		//备注
	private Date   createdate;	//创建日期
	private Date   updatedate;	//更新日期
	
	public College() {
		super();
		// TODO Auto-generated constructor stub
	}

	public College(int id, String name, String memo, Date createdate,
			Date updatedate) {
		super();
		this.id = id;
		this.name = name;
		this.memo = memo;
		this.createdate = createdate;
		this.updatedate = updatedate;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getMemo() {
		return memo;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
}
