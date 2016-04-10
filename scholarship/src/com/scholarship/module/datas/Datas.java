package com.scholarship.module.datas;

import java.sql.Date;

import com.scholarship.module.account.Account;

public class Datas {
	private int id;	//id
	private Account account;//关联账户
	private String 	college;//学院
	private String 	major;//专业
	private String 	grade;//班级
	private String 	year;//申请年份
	private int 	type;//状态 0基本信息1上次修改备份信息
	private String	name;//姓名
	private String 	sex;//性别
	private String	idnumber;//身份证
	private String	bankcard;//银行卡
	private String	nation;//民族
	private String	area;//地区
	private String	address;//家庭地址
	private String	distance;//离县城远近
	private String  expenses;//月生活费
	private String	isLoan;//是否有生源地贷款
	private	String	name_grandfather;//爷爷
	private String	name_grandmother;//奶奶
	private String	in_grandfather;//爷爷收入
	private String 	in_grandmother;//奶奶收入
	private String	health_grandfather;//爷爷健康
	private String 	health_grandmother;//奶奶健康
	private String	name_father;//父亲
	private String	name_mother;//母亲
	private String	in_father;//父亲收入
	private String 	in_mother;//母亲收入
	private String	career_father;//父亲职业
	private String 	career_mother;//母亲职业
	private String	health_father;//父亲身体
	private String 	health_mother;//母亲身体
	private String	others;//兄弟姐妹
	private String	in_family;//家庭收入
	private String	out_main;//主要支出
	private String	balance;//结余
	private String	descripe;//主要困难原因
	private String	file;//附件
	private String	accident;//变故
	private String	score_place;//成绩排名
	private	String	quality_place;//素质排名
	private String	memo;//备注
	private String  ex1;//扩展字段1
	private String  ex2;//扩展字段2
	private String  ex3;//扩展字段3
	private Date	createdate;
	private Date	updatedate;
	
	public Datas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Datas(int id, Account account, String college, String major,
			String grade, String year, int type, String name, String sex,
			String idnumber, String bankcard, String nation, String area,
			String address, String distance, String expenses, String isLoan,
			String name_grandfather, String name_grandmother,
			String in_grandfather, String in_grandmother,
			String health_grandfather, String health_grandmother,
			String name_father, String name_mother, String in_father,
			String in_mother, String career_father, String career_mother,
			String health_father, String health_mother, String others,
			String in_family, String out_main, String balance, String descripe,
			String file, String accident, String score_place,
			String quality_place, String memo, String ex1, String ex2,
			String ex3, Date createdate, Date updatedate) {
		super();
		this.id = id;
		this.account = account;
		this.college = college;
		this.major = major;
		this.grade = grade;
		this.year = year;
		this.type = type;
		this.name = name;
		this.sex = sex;
		this.idnumber = idnumber;
		this.bankcard = bankcard;
		this.nation = nation;
		this.area = area;
		this.address = address;
		this.distance = distance;
		this.expenses = expenses;
		this.isLoan = isLoan;
		this.name_grandfather = name_grandfather;
		this.name_grandmother = name_grandmother;
		this.in_grandfather = in_grandfather;
		this.in_grandmother = in_grandmother;
		this.health_grandfather = health_grandfather;
		this.health_grandmother = health_grandmother;
		this.name_father = name_father;
		this.name_mother = name_mother;
		this.in_father = in_father;
		this.in_mother = in_mother;
		this.career_father = career_father;
		this.career_mother = career_mother;
		this.health_father = health_father;
		this.health_mother = health_mother;
		this.others = others;
		this.in_family = in_family;
		this.out_main = out_main;
		this.balance = balance;
		this.descripe = descripe;
		this.file = file;
		this.accident = accident;
		this.score_place = score_place;
		this.quality_place = quality_place;
		this.memo = memo;
		this.ex1 = ex1;
		this.ex2 = ex2;
		this.ex3 = ex3;
		this.createdate = createdate;
		this.updatedate = updatedate;
	}

	public int getId() {
		return id;
	}

	public Account getAccount() {
		return account;
	}

	public String getCollege() {
		return college;
	}

	public String getMajor() {
		return major;
	}

	public String getGrade() {
		return grade;
	}

	public String getYear() {
		return year;
	}

	public int getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getSex() {
		return sex;
	}

	public String getIdnumber() {
		return idnumber;
	}

	public String getBankcard() {
		return bankcard;
	}

	public String getNation() {
		return nation;
	}

	public String getArea() {
		return area;
	}

	public String getAddress() {
		return address;
	}

	public String getDistance() {
		return distance;
	}

	public String getExpenses() {
		return expenses;
	}

	public String getIsLoan() {
		return isLoan;
	}

	public String getName_grandfather() {
		return name_grandfather;
	}

	public String getName_grandmother() {
		return name_grandmother;
	}

	public String getIn_grandfather() {
		return in_grandfather;
	}

	public String getIn_grandmother() {
		return in_grandmother;
	}

	public String getHealth_grandfather() {
		return health_grandfather;
	}

	public String getHealth_grandmother() {
		return health_grandmother;
	}

	public String getName_father() {
		return name_father;
	}

	public String getName_mother() {
		return name_mother;
	}

	public String getIn_father() {
		return in_father;
	}

	public String getIn_mother() {
		return in_mother;
	}

	public String getCareer_father() {
		return career_father;
	}

	public String getCareer_mother() {
		return career_mother;
	}

	public String getHealth_father() {
		return health_father;
	}

	public String getHealth_mother() {
		return health_mother;
	}

	public String getOthers() {
		return others;
	}

	public String getIn_family() {
		return in_family;
	}

	public String getOut_main() {
		return out_main;
	}

	public String getBalance() {
		return balance;
	}

	public String getDescripe() {
		return descripe;
	}

	public String getFile() {
		return file;
	}

	public String getAccident() {
		return accident;
	}

	public String getScore_place() {
		return score_place;
	}

	public String getQuality_place() {
		return quality_place;
	}

	public String getMemo() {
		return memo;
	}

	public String getEx1() {
		return ex1;
	}

	public String getEx2() {
		return ex2;
	}

	public String getEx3() {
		return ex3;
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

	public void setAccount(Account account) {
		this.account = account;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public void setExpenses(String expenses) {
		this.expenses = expenses;
	}

	public void setIsLoan(String isLoan) {
		this.isLoan = isLoan;
	}

	public void setName_grandfather(String name_grandfather) {
		this.name_grandfather = name_grandfather;
	}

	public void setName_grandmother(String name_grandmother) {
		this.name_grandmother = name_grandmother;
	}

	public void setIn_grandfather(String in_grandfather) {
		this.in_grandfather = in_grandfather;
	}

	public void setIn_grandmother(String in_grandmother) {
		this.in_grandmother = in_grandmother;
	}

	public void setHealth_grandfather(String health_grandfather) {
		this.health_grandfather = health_grandfather;
	}

	public void setHealth_grandmother(String health_grandmother) {
		this.health_grandmother = health_grandmother;
	}

	public void setName_father(String name_father) {
		this.name_father = name_father;
	}

	public void setName_mother(String name_mother) {
		this.name_mother = name_mother;
	}

	public void setIn_father(String in_father) {
		this.in_father = in_father;
	}

	public void setIn_mother(String in_mother) {
		this.in_mother = in_mother;
	}

	public void setCareer_father(String career_father) {
		this.career_father = career_father;
	}

	public void setCareer_mother(String career_mother) {
		this.career_mother = career_mother;
	}

	public void setHealth_father(String health_father) {
		this.health_father = health_father;
	}

	public void setHealth_mother(String health_mother) {
		this.health_mother = health_mother;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public void setIn_family(String in_family) {
		this.in_family = in_family;
	}

	public void setOut_main(String out_main) {
		this.out_main = out_main;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public void setDescripe(String descripe) {
		this.descripe = descripe;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public void setAccident(String accident) {
		this.accident = accident;
	}

	public void setScore_place(String score_place) {
		this.score_place = score_place;
	}

	public void setQuality_place(String quality_place) {
		this.quality_place = quality_place;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setEx1(String ex1) {
		this.ex1 = ex1;
	}

	public void setEx2(String ex2) {
		this.ex2 = ex2;
	}

	public void setEx3(String ex3) {
		this.ex3 = ex3;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

}
