package com.mcdemo.route.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.mcdemo.route.dto.BrandDto;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;


/**
 * Created by João on 2019-10-01.
 */

public class BrandControllerTest extends BaseServiceTest {

  private RequestEntity getBrandRequest() throws URISyntaxException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(APPLICATION_JSON);
    return new RequestEntity<>(new BrandDto("Rocket"), headers, HttpMethod.POST, new URI("/brands"));
  }

  @Test
  public void shouldAddBrand() throws URISyntaxException {
    final ResponseEntity<String> result = this.restTemplate.exchange(getBrandRequest(), String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(201);
    assertThat(Objects.requireNonNull(result.getHeaders().getLocation()).getPath()).contains("/brands/Rocket");
  }

  @Test
  public void getBrand_audi() {
    final ResponseEntity<String> result = this.restTemplate.getForEntity("/brands/audi", String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(200);
    assertThat(result.getBody()).contains("audi");
  }

  @Test
  public void getBrand_notFound() {
    final ResponseEntity<String> result = this.restTemplate.getForEntity("/brands/teste", String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(404);
    assertThat(result.getBody()).contains("Brand not found.");
  }

  @Test
  public void getAll() {
    final ResponseEntity<String> result = this.restTemplate.getForEntity("/brands/austin/models", String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(200);

  }

  @Test
  public void deleteBrand() {
    this.restTemplate.delete("/brands/Rocket");

  }
}