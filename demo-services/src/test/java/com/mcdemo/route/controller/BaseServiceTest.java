package com.mcdemo.route.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.mcdemo.route.dto.BrandDto;
import com.mcdemo.route.dto.Fuel;
import com.mcdemo.route.dto.ModelDto;
import com.mcdemo.route.dto.VehicleDto;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by João on 2019-10-02.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public abstract class BaseServiceTest {

  @Autowired
  TestRestTemplate restTemplate;
  @LocalServerPort
  private int port;

  final String LICENSE_PLATE = "21-AA-12";

  RequestEntity getVehicleRequest() throws URISyntaxException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(APPLICATION_JSON);
    final VehicleDto vehicle = getVehicleDto();
    return new RequestEntity<>(vehicle, headers, HttpMethod.POST, new URI("/vehicles"));
  }

  VehicleDto getVehicleDto() {
    final VehicleDto vehicle = new VehicleDto();
    vehicle.setBrandDto(new BrandDto("Chevrolet"));
    final ModelDto modelDto = new ModelDto();
    modelDto.setId(97);
    vehicle.setModelDto(modelDto);
    vehicle.setLicensePlate(LICENSE_PLATE);
    vehicle.setAvgConsumption(3.1);
    vehicle.setFuel(Fuel.DIESEL);
    return vehicle;
  }

}
