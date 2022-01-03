package com.search.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = "updNo")
@ToString
@Entity
@Table(name="search_update")
public class SearchUpdate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long updNo;
	
	@Column(length = 200, nullable = false)
	private String csvName;
	
	@CreationTimestamp
	private LocalDateTime updDate;
}