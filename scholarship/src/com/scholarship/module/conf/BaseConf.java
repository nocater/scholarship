package com.scholarship.module.conf;

/**
 * Description: 设置实体
 * 
 * @author 陈帅
 * @Version 1.0
 * @Created at 2016-04-01
 * @Modified by 
 */
public class BaseConf  {

	protected	Integer		id;		// ID
	protected	String		key;	// 键名
	protected	String		value;	// 键值
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}


}
