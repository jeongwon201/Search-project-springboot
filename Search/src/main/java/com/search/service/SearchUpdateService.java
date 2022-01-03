package com.search.service;

import com.search.domain.SearchUpdate;

public interface SearchUpdateService {
	
	public void register(SearchUpdate searchUpdate) throws Exception;
	
	public long count() throws Exception;
}
