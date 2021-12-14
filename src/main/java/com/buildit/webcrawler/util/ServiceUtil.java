package com.buildit.webcrawler.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ServiceUtil {
	
	public static final String ANCHOUR_PATTERN = "(?i)<a([^>]+)>(.+?)</a>";
	public static final String HREF_PATTERN = "\\s*(?i)href\\s*=\\s*(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))";

	
    /**
     * provides a standard configuration before invoking the GET HTTP method in rest template
     * @return
     */
	public static HttpEntity<Object> getRequestEntity() {
		HttpHeaders headers = new HttpHeaders();
    	
		List<MediaType> mediaTypeList = new ArrayList<>();
    	mediaTypeList.add(MediaType.TEXT_HTML);
    	
    	headers.setContentType(MediaType.TEXT_HTML);
    	headers.setAccept(mediaTypeList);
    	HttpEntity<Object> requestEntity = new HttpEntity<Object>(headers);
    	
    	return requestEntity;
	}
	
	/**
	 * Get the base URL to match and invoke internal links
	 * @param url
	 * @return
	 */
	public static String extractBaseUrl(String url) {
		
    	URL urlObject = null;
    	String baseUrl = null;
		try {
			urlObject = new URL(url);
		} catch (MalformedURLException e) {
			return baseUrl;
		}
    	baseUrl = urlObject.getProtocol() + "://" + urlObject.getHost();
    	return baseUrl;
	}
}
