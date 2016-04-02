package com.util.page;

import java.util.ArrayList;
import java.util.List;

public class SearchResult<T> {
	private List<T> list = new ArrayList<T>();

	private Page page;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

}
