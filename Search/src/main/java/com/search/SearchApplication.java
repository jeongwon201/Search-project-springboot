package com.search;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.search.domain.SearchKeyword;
import com.search.domain.SearchUpdate;
import com.search.dto.JsonData;
import com.search.service.SearchKeywordService;
import com.search.service.SearchUpdateService;

import jep.Interpreter;
import jep.JepException;
import jep.SharedInterpreter;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class SearchApplication {

	private final SearchUpdateService searchUpdateService;
	private final SearchKeywordService searchKeywordService;

	public static void main(String[] args) {
		SpringApplication.run(SearchApplication.class, args);
	}

	@Scheduled(fixedRate = 10000000)
	public void initUpdate() throws JepException {
		this.update();
	}

	private void update() throws JepException {

		String filepath = "C:\\Users\\jeong\\Desktop\\Eclipse\\workspace-spring-jpa-thymeleaf\\Search\\src\\main\\resources\\static\\csv";
		String title = "data";
		String pyPath = "C:\\Users\\jeong\\Desktop\\Eclipse\\workspace-spring-jpa-thymeleaf\\Search\\src\\main\\resources\\static\\python\\pre.py";

		try {
			BufferedWriter fw = new BufferedWriter(new FileWriter(filepath + "/" + title + ".csv", true));

			fw.write("id" + '\t' + "keyword");
			List<SearchKeyword> list = searchKeywordService.list();
			for (int i = 0; i < list.size(); i++) {
				fw.newLine();
				fw.write(list.get(i).getKeywordNo() + "\t" + list.get(i).getKeyword());
			}

			fw.flush();
			fw.close();

			try (Interpreter interp = new SharedInterpreter()) {
				interp.runScript(pyPath);
				String res = "";
				res = interp.getValue("json_data").toString();

				ObjectMapper objectMapper = new ObjectMapper();
				List<JsonData> jsonDataList = null;
				try {
					jsonDataList = objectMapper.readValue(res, new TypeReference<List<JsonData>>() {});
					searchKeywordService.register(jsonDataList);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			File file1 = new File(filepath + "/data.csv");
			long updateCnt = searchUpdateService.count();
			File file2 = new File(filepath + "/data" + updateCnt + ".csv");

			SearchUpdate searchUpdate = new SearchUpdate();
			searchUpdate.setCsvName("data" + updateCnt + ".csv");
			searchUpdateService.register(searchUpdate);

			if (file2.exists())
				throw new java.io.IOException("file exists");
			boolean success = file1.renameTo(file2);
			if (success) {
				System.out.println("File Rename successfuly");
			} else
				System.out.println("File is not Rename");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
