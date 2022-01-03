package com.search.dto;

import java.util.List;

import lombok.Getter;

@Getter
public class ChannelDto {
	private String keyword;
	private String lastBuildDate;
	private int total;
	private int start;
	private int display;
	private List<ItemDto> items;
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}