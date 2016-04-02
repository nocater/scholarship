package com.scholarship.webapp.action.scholarship;

import java.util.List;

import com.scholarship.module.scholarship.Scholarship;
import com.scholarship.service.scholarship.ScholarshipService;
import com.scholarship.webapp.action.BaseAction;

public class ScholarshipAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ScholarshipService scholarshipService;
	
	public String query(){
//		List<Scholarship> list = scholarshipService.queryAll();
//		System.out.println(list.get(0).getCategory()+list.get(2).getLevel());
		
		Scholarship scholarship = new Scholarship();
		scholarship.setCategory("123");
		scholarship.setLevel("222");
		scholarship.setMoney(2000);
//		scholarshipService.insert(scholarship);
//		System.out.println(scholarship.getId());
		
//		List<String> list = scholarshipService.queryCategories();
//		System.out.println(list.get(0));
//		System.out.println(list.get(1));
		
		scholarship = scholarshipService.queryById(6);
		scholarship.setCategory("dd");
		scholarship.setLevel("ff");
		scholarship.setMoney(999);
//		System.out.println(scholarshipService.insert(scholarship));
		
		scholarshipService.delete(scholarship);
		scholarshipService.deleteById(7);
		
		return SUCCESS;
	}
	
	public ScholarshipService getScholarshipService() {
		return scholarshipService;
	}

	public void setScholarshipService(ScholarshipService scholarshipService) {
		this.scholarshipService = scholarshipService;
	}
}
