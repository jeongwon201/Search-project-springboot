package com.search.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = "keywordNo")
@ToString
@Entity
@Table(name="search_keyword")
public class SearchKeyword {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long keywordNo;
	
	@Column(length = 200, nullable = false)
	private String keyword;
	
	@Column(length = 500, nullable = false)
	private String keywordSpell;
	
	@Column(nullable = false)
	private Long cnt;
	
	@Column(length = 1000, nullable = false)
	private String similarWord;
}