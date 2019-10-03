package com.mcdemo.route.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.mcdemo.route.dto.ModelDto;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

/**
 * Created by João on 2019-10-02.
 */
public class ModelControllerTest extends BaseServiceTest {

  private RequestEntity getModelRequest(String uri) throws URISyntaxException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(APPLICATION_JSON);
    return new RequestEntity<>(new ModelDto("Rocket", 2001), headers, HttpMethod.POST, new URI(uri));
  }

  @Test
  public void shouldAddModel() throws URISyntaxException {
    final ResponseEntity<String> result = this.restTemplate.exchange(getModelRequest("/brands/audi/models"), String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(201);
    assertThat(Objects.requireNonNull(result.getHeaders().getLocation()).getPath()).contains("/brands/audi/models/");
  }

  @Test
  public void shouldGetModel() {
    final ResponseEntity<String> result = this.restTemplate.getForEntity("/brands/Chevrolet/models/97", String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(200);
    assertThat(result.getBody()).contains("Camaro");
  }

  @Test
  public void shouldThrowException_whenSavingModel_BrandNotFound() throws URISyntaxException {
    final ResponseEntity<String> result = this.restTemplate.exchange(getModelRequest("/brands/test/models"), String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(404);
    assertThat(result.getBody()).contains("Brand not found.");
  }

  @Test
  public void getAll() {
    final ResponseEntity<String> result = this.restTemplate.getForEntity("/brands/ford/models", String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(200);

  }

  @Test
  public void deleteModel() {
    this.restTemplate.delete("/brands/ford/models/2");

  }

}