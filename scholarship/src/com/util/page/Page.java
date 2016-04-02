package com.util.page;

import java.util.List;

public class Page {
	public static final int DEFAULT_PAGE_SIZE = 15;
	
	public static final int DEFAULT_RULE_PAGE_SIZE = 50;
	
	private int pageSize = 15;

	private int totalCount; // 所有条数

	private int currentPage;

	private int startIndex = 0; // 起始页

	private int[] indexes = new int[0];

	private int nextIndex; // 下一页

	private int previousIndex; // 上一页

	private int pageCount;

	private int lastIndex = 0; // 末页

	public Page(int pageSize, int startIndex) {
		this.pageSize = pageSize;
		this.startIndex = startIndex;

	}

	public Page(List list, int totalCount) {
		setPageSize(pageSize);
		setTotalCount(totalCount);
		setStartIndex(0);
	}

	public Page(List list, int totalCount, int startIndex) {
		setPageSize(pageSize);
		setTotalCount(totalCount);
		setStartIndex(startIndex);
	}

	public Page(List list, int totalCount, int pageSize, int startIndex) {
		setPageSize(pageSize);
		if (totalCount == 0) {
			setTotalCount(1);
		} else {
			setTotalCount(totalCount);
		}
		setStartIndex(startIndex);
	}

	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			int count = totalCount / pageSize;
			if (totalCount % pageSize > 0)
				count++;
			indexes = new int[count];
			for (int i = 0; i < count; i++) {
				indexes[i] = pageSize * i;
			}
		} else {
			this.totalCount = 0;
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setIndexes(int[] indexes) {
		this.indexes = indexes;
	}

	public int[] getIndexes() {
		return indexes;
	}

	public void setStartIndex(int startIndex) {
		if (totalCount <= 0)
			this.startIndex = 0;
		else if (startIndex >= totalCount)
			this.startIndex = indexes[indexes.length - 1];
		else if (startIndex < 0)
			this.startIndex = 0;
		else {
			this.startIndex = indexes[startIndex / pageSize];
		}
	}

	public int getStartIndex() {

		return startIndex;
	}

	public void setNextIndex(int nextIndex) {
		this.nextIndex = nextIndex;
	}

	public int getNextIndex() {
		int nextIndex = getStartIndex() + pageSize;
		if (nextIndex >= totalCount)
			return getStartIndex();
		else
			return nextIndex;
	}

	public void setPreviousIndex(int previousIndex) {
		this.previousIndex = previousIndex;
	}

	public int getPreviousIndex() {
		int previousIndex = this.getStartIndex() - this.pageSize;

		if (previousIndex <= 0)
			return 0;
		else
			return previousIndex;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageCount() {
		int count = totalCount / pageSize;
		if (totalCount % pageSize > 0)
			count++;
		return count;
	}

	public int getCurrentPage() {
		if (0 == this.getPageCount()) {
			return 0;
		} else {
			return getStartIndex() / pageSize + 1;
		}
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getLastIndex() {

		if (indexes.length <= 0) {
			return 0;
		} else {
			return indexes[indexes.length - 1];
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
