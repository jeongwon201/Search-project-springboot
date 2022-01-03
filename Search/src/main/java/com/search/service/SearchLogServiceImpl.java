package com.search.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.search.domain.SearchLog;
import com.search.repository.SearchLogRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchLogServiceImpl implements SearchLogService {
	
	private final SearchLogRepository searchLogRepository;
	
	@Override
	public void register(SearchLog searchLog) throws Exception {
		searchLogRepository.save(searchLog);
	}
	
	@Override
	public long count() throws Exception {
		return searchLogRepository.count();
	}
	
	@Override
	public List<SearchLog> list() throws Exception {
		return searchLogRepository.findAll();
	}

}