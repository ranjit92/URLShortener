package com.url.shortener.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "shorturl")
public class ShortURL {
	@Id
	private String urlKey;

	private String actualUrl;

}
