package com.scholarship.webapp.action.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.scholarship.module.account.Account;
import com.scholarship.module.conf.AppConfig;
import com.scholarship.module.conf.setting.Setting;
import com.scholarship.service.audit.AuditService;
import com.scholarship.service.setting.SettingService;
import com.scholarship.webapp.action.BaseAction;
import com.util.StringUtil;

public class ConfigAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AuditService auditService;
	public SettingService settingService;
	
	private String applySwitch;
	private String codesSwitch;
	private String alertInfo;
	private File upFile;
	
	public String queryApplySwitch(){
		applySwitch = settingService.queryByKey("applyswitch");
		AppConfig.APPLY = Integer.parseInt(applySwitch);
		applySwitch = String.valueOf(AppConfig.APPLY);
		return SUCCESS;
	}
	
	public String queryCodesSwitch(){
		codesSwitch = settingService.queryByKey("codesswitch");
		AppConfig.CODES = Integer.parseInt(codesSwitch);
		codesSwitch = String.valueOf(AppConfig.CODES);
		return SUCCESS;
	}
	
	public String changeApplySwitch(){
		Account account = (Account) getSession().getAttribute("LOGON_ACCOUNT");
		Setting setting = new Setting();
		setting.setKey("applyswitch");
		
		if(AppConfig.APPLY==0)AppConfig.APPLY=1;
		else AppConfig.APPLY=0;
		applySwitch = String.valueOf(AppConfig.APPLY);
		
		//更改数据库信息
		setting.setValue(applySwitch);
		settingService.updateByKey(setting);
		
		addActionMessage("已"+(AppConfig.APPLY==0?"关闭":"开启")+"奖/助学金申请提交");
		
		List<String> list = new ArrayList<>();
		list.add(account.getName()+"("+account.getId()+")已"+(AppConfig.APPLY==0?"关闭":"开启")+"奖/助学金申请提交");
		auditService.operator(account.getId(), "修改申请开关", getRequest().getRemoteAddr(),list);
		
		return SUCCESS;
	}
	
	public String changeCodesSwitch(){
		Account account = (Account) getSession().getAttribute("LOGON_ACCOUNT");
		Setting setting = new Setting();
		setting.setKey("codesswitch");
		
		if(AppConfig.CODES==0)AppConfig.CODES=1;
		else AppConfig.CODES=0;
		codesSwitch = String.valueOf(AppConfig.CODES);
		
		//更改数据库信息
		setting.setValue(codesSwitch);
		settingService.updateByKey(setting);
		
		addActionMessage("已"+(AppConfig.CODES==0?"关闭":"开启")+"验证码");
		
		List<String> list = new ArrayList<>();
		list.add(account.getName()+"("+account.getId()+")已"+(AppConfig.CODES==0?"关闭":"开启")+"验证码");
		auditService.operator(account.getId(), "修改验证码开关", getRequest().getRemoteAddr(),list);
		
		return SUCCESS;
	}
	
	public String queryAlertInfo(){
		AppConfig.ALERT = settingService.queryByKey("alertinfo");
		alertInfo = AppConfig.ALERT;
		return SUCCESS;
	}
	
	public String changeAlertInfo(){
		Account account = (Account) getSession().getAttribute("LOGON_ACCOUNT");
		Setting setting = new Setting();
		setting.setKey("alertinfo");
		
		if(StringUtil.isNotBlank(alertInfo)){
			AppConfig.ALERT = alertInfo;
			
			setting.setValue(alertInfo);
			settingService.updateByKey(setting);
			
			addActionMessage("已修改");
			List<String> list = new ArrayList<>();
			list.add(account.getName()+"("+account.getId()+")已修改申请警告信息");
			auditService.operator(account.getId(), "修改警告信息", getRequest().getRemoteAddr(),list);
		}
		return SUCCESS;
	}
	
	public String queryTipFile(){
		return SUCCESS;
	}
	
	public String uploadFile(){
		File file = new File(AppConfig.ctx+"/csvTemplate/"+"tipFile.zip");
		try {
			FileUtils.copyFile(upFile, file);
			AppConfig.TIPFILE = file.getName();
			System.out.println(AppConfig.TIPFILE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public AuditService getAuditService() {
		return auditService;
	}

	public void setAuditService(AuditService auditService) {
		this.auditService = auditService;
	}

	public String getApplySwitch() {
		return applySwitch;
	}

	public String getCodesSwitch() {
		return codesSwitch;
	}

	public void setApplySwitch(String applySwitch) {
		this.applySwitch = applySwitch;
	}

	public void setCodesSwitch(String codesSwitch) {
		this.codesSwitch = codesSwitch;
	}

	public String getAlertInfo() {
		return alertInfo;
	}

	public void setAlertInfo(String alertInfo) {
		this.alertInfo = alertInfo;
	}

	public File getUpFile() {
		return upFile;
	}

	public void setUpFile(File upFile) {
		this.upFile = upFile;
	}

	public SettingService getSettingService() {
		return settingService;
	}

	public void setSettingService(SettingService settingService) {
		this.settingService = settingService;
	}

}
