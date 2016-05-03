package com.scholarship.module.grade;

import java.sql.Date;

import com.scholarship.module.college.College;

public class Grade {
	private int id;				//ID
	private College college;	//学院
	private int		status;		//激活|锁定
	private String major;		//专业
	private String name;		//班级名字
	private String edubg;		//学历
	private String grade;		//年级
	private String inyear;		//入学年份
	private int    stay;		//学年
	private String memo;		//备注
	private Date   createdate;	//创建日期
	private Date   updatedate;	//更新日期
	
	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Grade(int id, College college, int status, String major,
			String name, String edubg, String grade, String inyear, int stay,
			String memo, Date createdate, Date updatedate) {
		super();
		this.id = id;
		this.college = college;
		this.status = status;
		this.major = major;
		this.name = name;
		this.edubg = edubg;
		this.grade = grade;
		this.inyear = inyear;
		this.stay = stay;
		this.memo = memo;
		this.createdate = createdate;
		this.updatedate = updatedate;
	}

	public int getId() {
		return id;
	}

	public College getCollege() {
		return college;
	}

	public int getStatus() {
		return status;
	}

	public String getMajor() {
		return major;
	}

	public String getName() {
		return name;
	}

	public String getEdubg() {
		return edubg;
	}

	public String getGrade() {
		return grade;
	}

	public String getInyear() {
		return inyear;
	}

	public int getStay() {
		return stay;
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

	public void setCollege(College college) {
		this.college = college;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEdubg(String edubg) {
		this.edubg = edubg;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setInyear(String inyear) {
		this.inyear = inyear;
	}

	public void setStay(int stay) {
		this.stay = stay;
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
