package com.url.shortener.service;

import com.url.shortener.model.ShortURL;
import com.url.shortener.request.dto.URLRequest;
import com.url.shortener.response.dto.URLResponse;

public interface URLshortenerService {

	URLResponse addShortURL(URLRequest urlRequest);

	ShortURL getRedirectionUrl(String urlKey);

}
