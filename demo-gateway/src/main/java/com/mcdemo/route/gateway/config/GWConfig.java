package com.mcdemo.route.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * Created by João on 2019-09-30.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "configuration")
public class GWConfig {

  private String googleApiKey;
  private String googleEndpointUrl;

  private String michelinApiKey;
  private String michelinEndpointUrl;

  private Long backoff;
  private int maxAttempts;

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  public RetryTemplate retryTemplate() {
    return new RetryTemplate();
  }
}
