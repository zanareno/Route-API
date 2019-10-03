package com.mcdemo.route.gateway.exception;

import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.NeverRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;

/**
 * Created by João on 2019-02-05.
 */

public class ExceptionRetryPolicy extends ExceptionClassifierRetryPolicy {

  public ExceptionRetryPolicy(Integer attempts) {
    final SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
    simpleRetryPolicy.setMaxAttempts(attempts);

    this.setExceptionClassifier(classifiable -> {
      // Retry only when UnableToConnectRemoteServiceException was thrown
      if (classifiable instanceof UnableToConnectRemoteServiceException) {
        return simpleRetryPolicy;
      }

      // Do not retrypolicy for other exceptions
      return new NeverRetryPolicy();
    });
  }
}

