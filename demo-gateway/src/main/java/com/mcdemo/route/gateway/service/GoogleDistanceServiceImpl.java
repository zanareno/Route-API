package com.mcdemo.route.gateway.service;

import static com.mcdemo.route.dto.RouteDetailsDto.aRouteDetails;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.mcdemo.route.dto.RouteDetailsDto;
import com.mcdemo.route.dto.RouteDto;
import com.mcdemo.route.gateway.config.ConfigRetryTemplate;
import com.mcdemo.route.gateway.config.GWConfig;
import com.mcdemo.route.gateway.dto.GoogleResultDto;
import com.mcdemo.route.gateway.exception.NotFoundException;
import com.mcdemo.route.gateway.exception.UnableToConnectRemoteServiceException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Created by João on 2019-10-01.
 */
@Service
public class GoogleDistanceServiceImpl implements DistanceService {

  private static final Logger LOGGER = LoggerFactory.getLogger(GoogleDistanceServiceImpl.class);
  private final RestTemplate restTemplate;
  private final ConfigRetryTemplate retryTemplate;
  private final GWConfig gwConfig;

  public GoogleDistanceServiceImpl(final RestTemplate restTemplate, final ConfigRetryTemplate retryTemplate, final GWConfig gwConfig) {
    this.restTemplate = restTemplate;
    this.retryTemplate = retryTemplate;
    this.gwConfig = gwConfig;
  }

  @Override
  public RouteDetailsDto getDistanceWithRetryPolicy(final RouteDto route) {
    return this.retryTemplate.getRetryTemplate().execute(retryContext -> this.getDistance(route));
  }

  @Override
  public RouteDetailsDto getDistance(final RouteDto route) {
    try {
      final Map<String, String> uriVariables = new HashMap<>();
      uriVariables.put("key", gwConfig.getGoogleApiKey());
      uriVariables.put("origin", route.getOrigin());
      uriVariables.put("destination", route.getDestination());
      LOGGER.info("Invoking google api with origin: {} and destination {}.",route.getOrigin(), route.getDestination());
      final GoogleResultDto googleResultDto = extractData(restTemplate.getForObject(gwConfig.getGoogleEndpointUrl(), String.class, uriVariables));
      return aRouteDetails().withRouteDto(route).withDistance(googleResultDto.getDistance()).withDuration(googleResultDto.getDuration());
    } catch (final RestClientException e) {
      throw handleException(e);
    }
  }


  private GoogleResultDto extractData(final String json) {
    if(!json.contains("\"OK\"")) {
      throw new IllegalStateException("Problem occurred while obtaining route from google.");
    }
    final JSONArray read = JsonPath.parse(json).read("$..rows..elements.[:1]");

    final List<GoogleResultDto> googleResultDto;
    try {
      TypeReference<List<GoogleResultDto>> hashMapType = new TypeReference<List<GoogleResultDto>>() {
      };
      googleResultDto = new ObjectMapper().convertValue(read, hashMapType);
      LOGGER.debug("Converted google api result successfully to dto.");
    } catch (Exception e) {
      LOGGER.warn("Problem occurred while parsing api route result.");
      throw new IllegalStateException(e);
    }
    return googleResultDto.stream().findFirst().orElseThrow(IllegalStateException::new);
  }

  private RuntimeException handleException(final RestClientException e) {
    RuntimeException result = e;
    if (e instanceof HttpClientErrorException) {
      LOGGER.warn("Http Status code: {} ", ((HttpClientErrorException) e).getStatusCode());
      if (((HttpClientErrorException) e).getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE) {
        result = new UnableToConnectRemoteServiceException(e);
      } else if (((HttpClientErrorException) e).getStatusCode() == HttpStatus.NOT_FOUND) {
        result = new NotFoundException(e.getMessage());
      }
    } else if (e instanceof ResourceAccessException) {
      result = new UnableToConnectRemoteServiceException(e);
    }
    return result;
  }
}
