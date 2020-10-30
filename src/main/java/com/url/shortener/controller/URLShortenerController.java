package com.url.shortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortener.model.ShortURL;
import com.url.shortener.request.dto.URLRequest;
import com.url.shortener.response.dto.URLResponse;
import com.url.shortener.service.URLshortenerService;

@RestController
@CrossOrigin
public class URLShortenerController {

	@Autowired
	URLshortenerService urlshortenerService;
	
	@PostMapping("/addURL")
	public ResponseEntity<URLResponse> addShortURL(@RequestBody URLRequest urlRequest) {
		URLResponse urlResponse;
		urlResponse = urlshortenerService.addShortURL(urlRequest);
		System.out.println("Got HTTP request: "+urlResponse.toString());
		return new ResponseEntity<>(urlResponse,HttpStatus.OK);
	}
	
	@GetMapping("/st/{urlKey}")
	public ResponseEntity<String> getRedirectionUrl(@PathVariable String urlKey){
		ShortURL shortUrl = urlshortenerService.getRedirectionUrl(urlKey);
		System.out.println("got request: "+ shortUrl);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("location", shortUrl.getActualUrl());
		
		return new ResponseEntity(responseHeaders,HttpStatus.MOVED_PERMANENTLY);
	}
	
}
