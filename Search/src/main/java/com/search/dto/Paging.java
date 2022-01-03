package com.search.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging {
	private int totalCount;
	private int displayPageNum = 10;
	
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private Criteria criteria;
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		pagingData();
	}
	
	private void pagingData() {
		endPage = (int) (Math.ceil(criteria.getPage() / (double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		int tempEndPage = (int)(Math.ceil(totalCount) / (double)criteria.getPerPageNum());
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage * criteria.getPerPageNum() >= totalCount ? false : true;
	}
}