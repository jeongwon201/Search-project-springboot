package com.search.service;

import java.util.List;

import com.search.domain.SearchKeyword;
import com.search.dto.JsonData;

public interface SearchKeywordService {
	public void register(SearchKeyword searchKeyword) throws Exception;
	
	public void register(List<JsonData> jsonDataList) throws Exception;
	
	public List<SearchKeyword> list() throws Exception;
	
	public List<String> searchKeywordList(String keyword_spell) throws Exception;
	
	public SearchKeyword read(String keyword) throws Exception;
}
