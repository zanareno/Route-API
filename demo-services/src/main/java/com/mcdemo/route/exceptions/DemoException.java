package com.mcdemo.route.exceptions;

/**
 * Created by João on 2019-02-26.
 */
public class DemoException extends Exception {

  public DemoException(String message) {
    super(message);
  }

  public DemoException(Throwable cause) {
    super(cause);
  }
}
