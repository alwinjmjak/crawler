package com.buildit.webcrawler;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.buildit.webcrawler.controller.Crawler;

@RunWith(MockitoJUnitRunner.class)
public class CrawlerTest {
	
	@InjectMocks
	Crawler crawlerController;
	
	@Mock
	RestTemplate template;
	
		@Test
		public void testCrawl(){
			
			String url = "mockUrl";
			
			
			String response = "";
			ResponseEntity<String> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
			when(template.getForEntity(url, String.class)).thenReturn(responseEntity);
			crawlerController.crawl(url);
		}
}
