package com.mcdemo.route.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcdemo.route.dto.RouteDto;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

/**
 * Created by João on 2019-10-03.
 */

public class RouteControllerTest extends BaseServiceTest {


  @Test
  public void shouldEstimateCost() throws IOException, URISyntaxException {
    final ResponseEntity<String> result = this.restTemplate.exchange(getRouteRequest(), String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(200);
    assertThat(result.getBody()).contains("36 mins");
    assertThat(result.getBody()).contains("2151");
    assertThat(result.getBody()).contains("10.4 km");
    assertThat(result.getBody()).contains("10422");
    assertThat(result.getBody()).contains("0.43");
  }


  private RequestEntity getRouteRequest() throws IOException, URISyntaxException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(APPLICATION_JSON);
    final RouteDto routeDto = new ObjectMapper().readValue(payload, RouteDto.class);
    return new RequestEntity<>(routeDto, headers, HttpMethod.POST, new URI("/routes/estimate-costs/" + LICENSE_PLATE));

  }

  private static String payload = "{\n"
      + "\t\"origin\": \"40.6655101,-73.89188969999998\",\n"
      + "\t\"destination\": \"40.6905615,-73.9976592\"\n"
      + "}";


}