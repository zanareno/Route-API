package com.mcdemo.route.gateway.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.mcdemo.route.dto.RouteDto;
import com.mcdemo.route.gateway.config.ConfigRetryTemplate;
import com.mcdemo.route.gateway.config.GWConfig;
import com.mcdemo.route.gateway.exception.UnableToConnectRemoteServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by João on 2019-10-01.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class GoogleDistanceDtoServiceImplTest {

  @Mock
  private DistanceService distanceService;

  private ConfigRetryTemplate retryTemplate;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    final GWConfig gwConfig = new GWConfig();
    gwConfig.setBackoff(20L);
    gwConfig.setMaxAttempts(4);
    retryTemplate = new ConfigRetryTemplate(new RetryTemplate(), gwConfig);
  }


  @Test
  public void givenTemplateRetryService_whenCallWithException_thenRetry() {
    try {
      doThrow(UnableToConnectRemoteServiceException.class).when(distanceService).getDistance(any(RouteDto.class));
      retryTemplate.getRetryTemplate().execute(retryContext -> distanceService.getDistance(new RouteDto()));
    } catch (Exception e) {

      verify(distanceService, times(4)).getDistance(any(RouteDto.class));
    }
  }
}