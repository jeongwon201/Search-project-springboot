package com.search.service;

import java.util.List;

import com.search.domain.SearchLog;

public interface SearchLogService {
	
	public void register(SearchLog searchLog) throws Exception;
	
	public long count() throws Exception;

	public List<SearchLog> list() throws Exception;
}