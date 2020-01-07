package com.jiang.servicepage;

import java.io.Serializable;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
@Repository
@Scope("prototype")
public class Page /*extends ObjectMapper*/{
	
	/*public Page() {
		this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 设置 SerializationFeature.FAIL_ON_EMPTY_BEANS 为 false
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	}*/
	
	private static final long serialVersionUID = 1L;
	/**当前页面页码*/
	private int currentPageNo;
	/**当前页面条数*/
	private int currentPageNum;
	/**总条数*/
	private int totalCount;
	/**总页数*/
	private int totalPage;
	/**数据*/
	private List<?> list;
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getCurrentPageNum() {
		return currentPageNum;
	}
	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "Page [currentPageNo=" + currentPageNo + ", currentPageNum=" + currentPageNum + ", totalCount="
				+ totalCount + ", totalPage=" + totalPage + ", list=" + list + "]";
	}
	
}
