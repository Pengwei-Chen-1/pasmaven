package com.cpw.dto;

/**
 * 分页信息
 * 
 * @author pengwei_chen
 * @date 2014-4-22 下午6:50:44
 */
public class PageInfo {

	private int pageNum;
	private int count;
	private int pageSize;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
