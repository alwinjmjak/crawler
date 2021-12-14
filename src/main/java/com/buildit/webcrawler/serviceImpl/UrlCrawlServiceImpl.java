package com.buildit.webcrawler.serviceImpl;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.buildit.webcrawler.service.UrlCrawlService;
import com.buildit.webcrawler.util.ServiceUtil;

/**
 * Async rest template call for the given url
 * @author Alwin
 *
 */
@Service
public class UrlCrawlServiceImpl implements UrlCrawlService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Returns the completable Future once the task is completed
	 * else return null in case of exception or error
	 */
	@Async
	public CompletableFuture<String> getUrlContent(String url) {
		  try {
	    	ResponseEntity<String> reponse = restTemplate.exchange(url,HttpMethod.GET,ServiceUtil.getRequestEntity(), String.class);
	    	if(reponse.getStatusCode().equals(HttpStatus.OK)) {
	    		return CompletableFuture.completedFuture(reponse.getBody()); }
		   } catch (Exception exception) {
			    return CompletableFuture.completedFuture(null);
		}
    	return CompletableFuture.completedFuture(null);
	}
	
	/**
	 * Returns the completable Future once the task is completed
	 * else return null in case of exception or error
	 */
	@Async
	public CompletableFuture<Void> getCrawledUrls(String baseUrl, String response, Pattern tagPattern, Pattern linkPattern, Set<String> internalLinks, Set<String> externalLinks) {
    	return CompletableFuture.runAsync(() -> {
    		Matcher tagMatcher = tagPattern.matcher(response);
   
    	while(tagMatcher.find()) {
    		String href = tagMatcher.group(1);
    		Matcher linkMatcher = linkPattern.matcher(href);
    		
    		while(linkMatcher.find()) {
    			String extractedUrl = linkMatcher.group(1);
    			
    			if(extractedUrl.contains(baseUrl) || extractedUrl.startsWith("\"/")) {
    				internalLinks.add(extractedUrl);
    			} else {
    				externalLinks.add(extractedUrl);
    			}
    		}
	    } 
	}); }
}
