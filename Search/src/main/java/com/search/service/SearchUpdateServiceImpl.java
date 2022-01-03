package com.search.service;

import org.springframework.stereotype.Service;

import com.search.domain.SearchUpdate;
import com.search.repository.SearchUpdateRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchUpdateServiceImpl implements SearchUpdateService {

	private final SearchUpdateRepository searchUpdateRepository;
	
	@Override
	public void register(SearchUpdate searchUpdate) throws Exception {
		searchUpdateRepository.save(searchUpdate);
	}
	
	@Override
	public long count() throws Exception {
		return searchUpdateRepository.count();
	}

}
