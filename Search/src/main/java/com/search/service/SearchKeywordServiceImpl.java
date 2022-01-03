package com.search.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.search.domain.SearchKeyword;
import com.search.dto.JsonData;
import com.search.repository.SearchKeywordRepository;
import com.search.util.Jaso;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SearchKeywordServiceImpl implements SearchKeywordService {

	private final Jaso jaso;
	private final SearchKeywordRepository searchKeywordRepository;
	public void register(SearchKeyword searchKeyword) throws Exception {
		
		if(searchKeywordRepository.findByKeyword(searchKeyword.getKeyword()) != null) {
			SearchKeyword searchKeywordEntity = searchKeywordRepository.findByKeyword(searchKeyword.getKeyword());
			searchKeywordEntity.setKeywordSpell(jaso.hangulToJaso(searchKeyword.getKeyword()));
			searchKeywordEntity.setCnt(searchKeywordEntity.getCnt() + 1);
			searchKeywordRepository.save(searchKeywordEntity);
		} else {
			SearchKeyword searchKeywordEntity = new SearchKeyword();
			searchKeywordEntity.setKeyword(searchKeyword.getKeyword());
			searchKeywordEntity.setKeywordSpell(jaso.hangulToJaso(searchKeyword.getKeyword()));
			searchKeywordEntity.setSimilarWord("");
			searchKeywordEntity.setCnt((long) 1);
			searchKeywordRepository.save(searchKeywordEntity);
		}
	}
	
	@Override
	public void register(List<JsonData> jsonDataList) throws Exception {
		for(int i=0; i<jsonDataList.size(); i++) {
			SearchKeyword searchKeywordEntity = searchKeywordRepository.findByKeyword(jsonDataList.get(i).getKeyword());
			searchKeywordEntity.setSimilarWord(jsonDataList.get(i).getKeywordList());
			searchKeywordRepository.save(searchKeywordEntity);
		}
		
	}

	@Override
	public List<SearchKeyword> list() throws Exception {
		return searchKeywordRepository.findAll();
	}
	
	@Override
	public List<String> searchKeywordList(String keyword_spell) throws Exception {
		return searchKeywordRepository.findByKeywordContains(keyword_spell);
	}

	@Override
	public SearchKeyword read(String keyword) throws Exception {
		return searchKeywordRepository.findByKeyword(keyword);
	}

}