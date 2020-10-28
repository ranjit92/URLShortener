package com.url.shortener.response.dto;

import lombok.Data;

@Data
public class URLResponse {

	String originalURL;
	String shortURL;
}
