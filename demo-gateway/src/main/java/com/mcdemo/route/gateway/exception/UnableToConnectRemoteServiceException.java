package com.mcdemo.route.gateway.exception;

/**
 * Created by João on 2019-02-05.
 */
public class UnableToConnectRemoteServiceException extends RuntimeException {

  public UnableToConnectRemoteServiceException(final Throwable cause) {
    super(cause);
  }

  public UnableToConnectRemoteServiceException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
