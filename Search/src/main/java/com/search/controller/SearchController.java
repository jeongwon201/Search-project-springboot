package com.search.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.search.domain.SearchKeyword;
import com.search.domain.SearchLog;
import com.search.dto.ChannelDto;
import com.search.dto.Criteria;
import com.search.dto.Paging;
import com.search.service.SearchKeywordService;
import com.search.service.SearchLogService;
import com.search.util.NaverSearch;
import com.search.util.NetUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/search")
public class SearchController {
	
	private final NaverSearch naverSearch;
	private final SearchLogService searchLogService;
	private final SearchKeywordService searchKeywordService;
	
	@GetMapping("/list")
	public String list(String keyword, Criteria criteria, Model model, HttpServletRequest request) throws Exception {
		
		keyword = naverSearch.typoTranslation(keyword);
		ChannelDto shopItems = naverSearch.search(keyword, criteria.getPerPageNum(), criteria.getPageStart(), "sim");
		
		SearchLog searchLog = new SearchLog();
		searchLog.setKeyword(shopItems.getKeyword());
		searchLog.setRemoteAddr(NetUtils.getIp(request));
		searchLogService.register(searchLog);
		
		SearchKeyword searchKeyword = new SearchKeyword();
		searchKeyword.setKeyword(shopItems.getKeyword());
		searchKeywordService.register(searchKeyword);
		
		Paging paging = new Paging();
		paging.setCriteria(criteria);
		paging.setTotalCount(shopItems.getTotal());
		
		searchKeyword = searchKeywordService.read(keyword);
		String strSimilarWord = searchKeyword.getSimilarWord();

		List<String> similarWords = new ArrayList<String>();
		if(strSimilarWord == "") {
			similarWords.add("없음");
		} else {
			
			String[] splitStr = strSimilarWord.split(", ");
			for(int i=0; i<splitStr.length; i++) {
				similarWords.add(splitStr[i]);
			}
		}
		
		model.addAttribute("similarWords", similarWords);
		model.addAttribute("shopItems", shopItems);
		model.addAttribute("paging", paging);
		model.addAttribute("keyword", keyword);
		
		return "search/list";
	}
	
}