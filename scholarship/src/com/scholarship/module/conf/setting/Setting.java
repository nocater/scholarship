package com.scholarship.module.conf.setting;

import com.scholarship.module.conf.BaseConf;

public class Setting extends BaseConf {
	private Integer type;	// 类型
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public static void main(String[] argv) {
		Setting inst = new Setting();
		inst.setId(1);
		
		System.out.println("inst:"+inst.getId());
	}
}
