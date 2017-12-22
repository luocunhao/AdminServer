package com.pulan.utils;

import java.io.Serializable;

/**
 * 分页类，将分页属性抽象
 * 
 * @author songwb
 *
 */
public class Pager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3859457327735648293L;

	/**
	 * 默认每页条数
	 */
	public static final int DEFAULT_PAGESIZE = 5;
	/**
	 * 默认初始页码
	 */
	public static final int DEFAULT_PAGENUM = 0;
	/**
	 * 每页显示的条数
	 */

	private int pageSize;
	/**
	 * 当前页面
	 */
	private int currentPage;
	/**
	 * 总的条数
	 */
	private int totalRecord;// 一共多少条记录
	/**
	 * 总的页数
	 */
	private int totalPage;

	// 根据传入参数实例化Pager对象，计算相应的信息以及结果dataList
	public Pager(int currentPage, int pageSize,int totalRecord) {
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		this.totalPage = this.totalRecord / this.pageSize;
		// 非整除+1
		if (this.totalRecord % this.pageSize != 0) {
			this.totalPage += 1;
		}
		// 当前第几页,大于总页数则赋值为总页数，小于1则赋值为1
		if (this.totalPage < currentPage) {
			this.currentPage = this.totalPage;
		} else {
			this.currentPage = currentPage;
		}
		
		if (this.currentPage < 1) {
			this.currentPage = 1;
		}
	}

	public Pager() {

	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	@Override
	public String toString() {
		return "Pager [pageSize=" + pageSize + ", currentPage=" + currentPage + ", totalRecord=" + totalRecord
				+ ", totalPage=" + totalPage + "]";
	}
    
}
