package com.search.util;

import java.util.HashMap;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.dto.ChannelDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NaverSearch {
	
	public ChannelDto search(String keyword, int display, int start, String sort) {
		
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("X-Naver-Client-Id", "jffoU1wE9E77jyZlIAQf");
		httpHeaders.add("X-Naver-Client-Secret", "qP7q5uEjvF");
		String body = "";
		
		HttpEntity<String> requestEntity = new HttpEntity<String>(body, httpHeaders);
		
		String responseUrl = "https://openapi.naver.com/v1/search/shop.json?query=" + keyword + "&display=" + display + "&start=" + start + "&sort=" + sort;
		ResponseEntity<String> responseEntity = restTemplate.exchange(responseUrl, HttpMethod.GET, requestEntity, String.class);
		
		String response = responseEntity.getBody();
		
		ChannelDto channelDto = new ChannelDto();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			channelDto = objectMapper.readValue(response, ChannelDto.class);
			channelDto.setKeyword(keyword);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		log.info("response = " + response);
		log.info("keyword = " + channelDto.getKeyword());
		//log.info("ChannelDto.items = " + channelDto.getItems());
		
		return channelDto;
	}
	
	public String typoTranslation(String keyword) {
		
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("X-Naver-Client-Id", "jffoU1wE9E77jyZlIAQf");
		httpHeaders.add("X-Naver-Client-Secret", "qP7q5uEjvF");
		String body = "";

		HttpEntity<String> requestEntity = new HttpEntity<String>(body, httpHeaders);

		String responseUrl = "https://openapi.naver.com/v1/search/errata.json?query=" + keyword;
		ResponseEntity<String> responseEntity = restTemplate.exchange(responseUrl, HttpMethod.GET, requestEntity, String.class);
		
		String response = responseEntity.getBody();
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			HashMap<String, String> map = objectMapper.readValue(response, HashMap.class);
			if(map.get("errata") != "") {
				keyword = map.get("errata");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return keyword;
	}
	
}
