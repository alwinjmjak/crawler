package com.buildit.webcrawler.service;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Pattern;

public interface UrlCrawlService {

	public CompletableFuture<String> getUrlContent(String url);
	
	public CompletableFuture<Void> getCrawledUrls(String baseUrl, String response, Pattern tagPattern, Pattern linkPattern, Set<String> internalLinks, Set<String> externalLinks);
}
