package com.scholarship.module.scholarship;

public class Scholarship {
	private int 	id;			//id
	private String  category;	//奖金种类
	private String 	level;		//奖金等级
	private int money;			//奖金金额
	
	public Scholarship() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Scholarship(int id, String category, String level, int money) {
		super();
		this.id = id;
		this.category = category;
		this.level = level;
		this.money = money;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
	
}
