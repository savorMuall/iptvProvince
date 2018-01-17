package com.iptv.util;

//模拟的一个分页对象PageBean 
public class PageEntity {
	// 开始数
	private int startNumber;
	// 结束数
	private int endNumber;

	//每页展示数量
	private final int pageSize = 10;
	
	//当前页码
	private int currentPage;
	
	//页码
	private int pageNo;

	public PageEntity() {
	}

	public PageEntity(Integer startNumber, Integer endNumber) {
		super();
		this.startNumber = startNumber;
		this.endNumber = endNumber;
	}

	public int getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(int startNumber) {
		this.startNumber = startNumber;
	}

	public int getEndNumber() {
		return endNumber;
	}

	public void setEndNumber(int endNumber) {
		this.endNumber = endNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

}
