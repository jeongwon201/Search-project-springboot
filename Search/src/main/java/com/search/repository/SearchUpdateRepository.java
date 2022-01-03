package com.search.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.search.domain.SearchUpdate;

public interface SearchUpdateRepository extends JpaRepository<SearchUpdate, Long> {

}
