package com.scholarship.module.account;

import java.sql.Date;

import com.scholarship.module.college.College;
import com.scholarship.module.grade.Grade;
import com.scholarship.module.role.Role;

public class Account {
	private int 	id;			//id
	private Role 	role;		//角色
	private College college;	//学院
	private Grade   grade;		//班级
	private String 	accno;		//学号
	private String 	name;		//姓名
	private String 	password;	//密码
	private String  sex;		//性别
	private String	qq;			//QQ
	private String  phone;		//电话
	private String 	email;		//电子邮件
	private Date    createDate;  //创建日期
	private Date    updateDate;	//修改日期
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int id, Role role, College college, Grade grade,
			String accno, String name, String password, String sex, String qq,
			String phone, String email, Date createDate, Date updateDate) {
		super();
		this.id = id;
		this.role = role;
		this.college = college;
		this.grade = grade;
		this.accno = accno;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.qq = qq;
		this.phone = phone;
		this.email = email;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public int getId() {
		return id;
	}

	public Role getRole() {
		return role;
	}

	public College getCollege() {
		return college;
	}

	public Grade getGrade() {
		return grade;
	}

	public String getAccno() {
		return accno;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getSex() {
		return sex;
	}

	public String getQq() {
		return qq;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
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

	public void setRole(Role role) {
		this.role = role;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
