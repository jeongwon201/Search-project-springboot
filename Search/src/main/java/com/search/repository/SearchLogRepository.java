package com.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.search.domain.SearchLog;

public interface SearchLogRepository extends JpaRepository<SearchLog, Long> {

}
