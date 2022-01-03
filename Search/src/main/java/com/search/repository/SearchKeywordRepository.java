package com.search.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.search.domain.SearchKeyword;

public interface SearchKeywordRepository extends JpaRepository<SearchKeyword, Long> {
	
	public SearchKeyword findByKeyword(String keyword);
	
	@Query(nativeQuery = true, value = "SELECT keyword FROM search_keyword sk WHERE sk.keyword_spell LIKE %?1% ORDER BY sk.cnt DESC limit 8")
	public List<String> findByKeywordContains(String keyword_spell);

}
