package com.mcdemo.route.utils;

import org.springframework.http.HttpHeaders;

/**
 * Created by João on 2019-10-02.
 */
public class Utils {

  public static HttpHeaders getContentLengthHeader(long size) {
    final HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentLength(size);
    return httpHeaders;
  }

  private Utils() {
  }
}
