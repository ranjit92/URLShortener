package com.url.shortener.utilities;

import java.security.MessageDigest;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class HashUtility {

	/**
	 * Digest() method is called to calculate message digest of an input digest()
	 * return array of byte
	 * 
	 * 
	 * @param url
	 * @return
	 */
	public String getUniqueHash(String url) {

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			byte[] messageDigest = md.digest(url.getBytes("UTF-8"));

			// converting byte array to Hexadecimal String
			StringBuilder sb = new StringBuilder(2 * messageDigest.length);

			for (byte b : messageDigest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			return getRandomUniqueKey(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Generates fixed length(6) unique key
	 * 
	 * @param encodedString
	 * @return
	 */
	private String getRandomUniqueKey(String encodedString) {

		String BasicBase64format = Base64.getUrlEncoder().encodeToString(encodedString.getBytes());
		StringBuilder resultKey = new StringBuilder(6);
		for (int i = 0; i < 6; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (BasicBase64format.length() * Math.random());

			// add Character one by one in end of StringBuilder
			resultKey.append(BasicBase64format.charAt(index));
		}
		return resultKey.toString();
	}

}
