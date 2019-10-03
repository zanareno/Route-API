package com.mcdemo.route.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.net.URISyntaxException;
import java.util.Objects;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

/**
 * Created by João on 2019-10-02.
 */
public class VehicleControllerTest extends BaseServiceTest {

  @Test
  public void deleteVehicle() {
    this.restTemplate.delete("/vehicles/" + LICENSE_PLATE);
  }

  @Test
  public void shouldAddVehicle() throws URISyntaxException {
    final ResponseEntity<String> result = this.restTemplate.exchange(getVehicleRequest(), String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(201);
    assertThat(Objects.requireNonNull(result.getHeaders().getLocation()).getPath()).contains("/vehicles/" + LICENSE_PLATE );
  }

  @Test
  public void getVehicle() {
    final ResponseEntity<String> result = this.restTemplate.getForEntity("/vehicles/" + LICENSE_PLATE, String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(200);
    assertThat(result.getBody()).contains(LICENSE_PLATE);
  }

  @Test
  public void getVehicle_notFound() {
    final ResponseEntity<String> result = this.restTemplate.getForEntity("/vehicles/45646", String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(404);
    assertThat(result.getBody()).contains("Vehicle not found.");
  }

  @Test
  public void getAll() {
    final ResponseEntity<String> result = this.restTemplate.getForEntity("/vehicles", String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(200);

  }

  @Test
  public void shouldThrowException_AddVehicle_alreadyExists() throws URISyntaxException {
    final ResponseEntity<String> result = this.restTemplate.exchange(getVehicleRequest(), String.class);
    assertThat(result.getStatusCodeValue()).isEqualTo(400);
    assertThat(result.getBody()).contains("Vehicle with license plate 21-AA-12 already exists.");
  }
}