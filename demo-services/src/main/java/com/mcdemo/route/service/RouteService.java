package com.mcdemo.route.service;

import com.mcdemo.route.dto.RouteDetailsDto;
import com.mcdemo.route.dto.VehicleDto;

/**
 * Created by João on 2019-09-30.
 */
public interface RouteService {

  RouteDetailsDto estimateCosts(final RouteDetailsDto routeDetailsDto, final VehicleDto vehicleDto, final Double unitPrice);
}
