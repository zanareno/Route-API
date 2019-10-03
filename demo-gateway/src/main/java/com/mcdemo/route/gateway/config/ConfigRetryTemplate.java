package com.mcdemo.route.gateway.config;

import com.mcdemo.route.gateway.exception.ExceptionRetryPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by João on 2019-09-30.
 */
@Component
public class ConfigRetryTemplate {

  private final RetryTemplate retryTemplate;
  private final com.mcdemo.route.gateway.config.GWConfig gwConfig;

  public RetryTemplate getRetryTemplate() {
    return retryTemplate;
  }

  public ConfigRetryTemplate(final RetryTemplate retryTemplate, final com.mcdemo.route.gateway.config.GWConfig gwConfig) {
    this.retryTemplate = retryTemplate;
    this.gwConfig = gwConfig;
    FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
    fixedBackOffPolicy.setBackOffPeriod(this.gwConfig.getBackoff());
    retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
    retryTemplate.setRetryPolicy(new ExceptionRetryPolicy(this.gwConfig.getMaxAttempts()));
  }

}
