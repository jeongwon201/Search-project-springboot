package com.search.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = "logNo")
@ToString
@Entity
@Table(name="search_log")
public class SearchLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long logNo;
	
	@Column(length = 200, nullable = false)
	private String keyword;
	
	@Column(length = 50, nullable = false)
	private String remoteAddr;
	
	@CreationTimestamp
	private LocalDateTime regDate;
}