package com.fifteen.auth.security;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * simple hash function to hash any string into a string of 64 character.
 * This is the hashed password that will finally be stored in the remote
 * database.
 * 
 * @author Triet Huynh
 * @see https://www.baeldung.com/sha-256-hashing-java
 */
public class PasswordHasher {
  private PasswordHasher() {
  }

  public static String sha2(String password) {
    return DigestUtils.sha256Hex(password);
  }
}
