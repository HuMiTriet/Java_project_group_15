package com.twenty.one.security;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * simple hash function to hash any string into a string of 64 character
 * @author PJ
 * @see https://www.baeldung.com/sha-256-hashing-java
 */
public class HashPassword {
	private HashPassword(){};

	public static String sha2(String password) {
		String hashedPassword = DigestUtils.sha256Hex(password);
		return hashedPassword;
	}

	public static String sha3(String password) {
		String hashedPasword = new DigestUtils("SHA3-256").digestAsHex(password);
		return hashedPasword;
	}
}