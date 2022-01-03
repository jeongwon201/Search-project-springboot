package com.search.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Criteria {
	private int page; // start
	private int perPageNum; // display
	private int pageSize; // 10

	public int getPageStart() {
		if(this.page == 1) {
			return 1;
		}
		return (this.page - 1) * perPageNum + 1;
	}

	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;

		} else {
			this.page = page;
		}
	}

	public void setPerPageNum(int perPageNum) {
		int cnt = this.perPageNum;

		if (perPageNum != cnt) {
			this.perPageNum = cnt;
		} else {
			this.perPageNum = perPageNum;
		}

	}

}