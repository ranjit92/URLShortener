package com.url.shortener.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.url.shortener.model.ShortURL;

@Repository
public class URLShortenerRepo implements URLshortenerRepo{

	@Autowired
	MongoTemplate mongotemplate;
	
	public ShortURL addShortURL(ShortURL shortURL) {
		return mongotemplate.save(shortURL);
 }

	@Override
	public ShortURL getRedirectionUrl(String urlKey) {
		Query query = new Query();
		query.addCriteria(Criteria.where("urlKey").is(urlKey));

		return mongotemplate.findOne(query, ShortURL.class);
	}
	
}
