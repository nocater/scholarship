package com.scholarship.module.role;

import java.sql.Date;
import java.util.List;

import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;

public class Role {
	private int id;			//ID
	private String name;	//角色名字
	private String memo;	//备注
	private List<College> collegeList;
	private List<Grade>	  gradeList;
	private Date createdate;	//创建日期
	private Date updatedate;	//更新日期
	
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(int id, String name, String memo, List<College> collegeList,
			List<Grade> gradeList, Date createdate, Date updatedate) {
		super();
		this.id = id;
		this.name = name;
		this.memo = memo;
		this.collegeList = collegeList;
		this.gradeList = gradeList;
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

	public List<College> getCollegeList() {
		return collegeList;
	}

	public List<Grade> getGradeList() {
		return gradeList;
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

	public void setCollegeList(List<College> collegeList) {
		this.collegeList = collegeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	
}
