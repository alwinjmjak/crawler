package com.buildit.webcrawler.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.buildit.webcrawler.model.Page;
import com.buildit.webcrawler.service.UrlCrawlService;
import com.buildit.webcrawler.util.ServiceUtil;

@RestController
public class CrawlController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	UrlCrawlService crawlService;
	
    @GetMapping("/crawl")
    public Page crawl(@RequestParam(value = "url") String url) {
    	url = "https://crawler-test.com/";

		
    	Set<String> internalLinks = new HashSet<>();
    	Set<String> externalLinks = new HashSet<>();
    	//Set<String> imageLinks = new HashSet<>();
    	Page page = new Page();

    	try {
			String response = crawlService.getUrlContent(url).get();
			Pattern tagPattern = Pattern.compile(ServiceUtil.ANCHOUR_PATTERN);
			Pattern linkPattern = Pattern.compile(ServiceUtil.HREF_PATTERN);
	    	String baseUrl = ServiceUtil.extractBaseUrl(url);
	    	crawlService.getCrawledUrls(url, response, tagPattern, linkPattern, internalLinks, externalLinks).get();
		} catch (Exception e) {
			return page;
		}
    	page.setExternalLinks(new ArrayList<>(externalLinks));
    	page.setInternalLinks(new ArrayList<>(internalLinks));
        return page;
    }

}
