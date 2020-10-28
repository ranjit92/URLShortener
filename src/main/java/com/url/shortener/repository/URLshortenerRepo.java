package com.url.shortener.repository;

import com.url.shortener.model.ShortURL;

public interface URLshortenerRepo {

	ShortURL addShortURL(ShortURL shortUrl);

	ShortURL getRedirectionUrl(String urlKey);

}
