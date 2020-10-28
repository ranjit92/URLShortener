package com.url.shortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.url.shortener.model.ShortURL;
import com.url.shortener.repository.URLshortenerRepo;
import com.url.shortener.request.dto.URLRequest;
import com.url.shortener.response.dto.URLResponse;
import com.url.shortener.utilities.HashUtility;

@Service
public class URLshortenerServiceImpl implements URLshortenerService{

	@Autowired
	URLshortenerRepo urlshortenerRepo;
	
	@Autowired
	HashUtility hashUtility;
	
	@Value("${spring.appserver.base.url}")
    private String BASEURL;
	
	@Override
	public URLResponse addShortURL(URLRequest urlRequest) {
		String hashKey = hashUtility.getUniqueHash(urlRequest.getOrginalURL());
		
		ShortURL shortUrl = new ShortURL();
		shortUrl.setActualUrl(urlRequest.getOrginalURL());
		shortUrl.setUrlKey(hashKey);
		
		ShortURL savedUrl = urlshortenerRepo.addShortURL(shortUrl);
		
		URLResponse urlResponse = new URLResponse();
		urlResponse.setOriginalURL(savedUrl.getActualUrl());
		urlResponse.setShortURL(String.format("%s%s", BASEURL,hashKey));
		return urlResponse;
	}

	@Override
	public ShortURL getRedirectionUrl(String urlKey) {
		return urlshortenerRepo.getRedirectionUrl(urlKey);
	}

}
