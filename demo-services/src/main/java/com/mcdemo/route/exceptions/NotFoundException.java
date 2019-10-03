package com.mcdemo.route.exceptions;

/**
 * Created by João on 2018-09-21.
 */
public class NotFoundException extends Exception {

  public NotFoundException() {
    this("Resource not found.");
  }

  public NotFoundException(String message) {
    super(message);
  }
}
