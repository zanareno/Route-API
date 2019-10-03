package com.mcdemo.route.controller;

import static com.mcdemo.route.controller.RouteController.V1_ROUTES;

import com.mcdemo.route.dto.RouteDetailsDto;
import com.mcdemo.route.dto.RouteDto;
import com.mcdemo.route.dto.VehicleDto;
import com.mcdemo.route.exceptions.NotFoundException;
import com.mcdemo.route.gateway.service.DistanceService;
import com.mcdemo.route.gateway.service.FuelPriceService;
import com.mcdemo.route.service.RouteService;
import com.mcdemo.route.service.VehicleService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by João on 2019-09-30.
 */
@RestController
@RequestMapping(path = V1_ROUTES)
public class RouteController implements RouteControllerService {

  public static final String V1_ROUTES = "/routes";
  private final DistanceService distanceService;
  private final FuelPriceService fuelPriceService;
  private final RouteService routeService;
  private final VehicleService vehicleService;

  public RouteController(final DistanceService distanceService, final FuelPriceService fuelPriceService, final RouteService routeService,
      final VehicleService vehicleService) {
    this.distanceService = distanceService;
    this.fuelPriceService = fuelPriceService;
    this.routeService = routeService;
    this.vehicleService = vehicleService;
  }

  @PostMapping(path = "/estimate-costs/{license_plate}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity calculateCosts(@RequestBody RouteDto route, @PathVariable("license_plate") String licensePlate) throws NotFoundException {
    final RouteDetailsDto routeDetailsDto = distanceService.getDistanceWithRetryPolicy(route);
    final VehicleDto vehicleDto = vehicleService.getOne(licensePlate);
    final Double price = fuelPriceService.getPrice(vehicleDto.getFuel());
    final RouteDetailsDto estimateCosts = routeService.estimateCosts(routeDetailsDto, vehicleDto, price);
    return ResponseEntity.ok(estimateCosts);
  }
}
