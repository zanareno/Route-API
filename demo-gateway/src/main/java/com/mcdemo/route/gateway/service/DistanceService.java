package com.mcdemo.route.gateway.service;

import com.mcdemo.route.dto.RouteDetailsDto;
import com.mcdemo.route.dto.RouteDto;

/**
 * Created by João on 2019-10-01.
 */
public interface DistanceService {

  RouteDetailsDto getDistanceWithRetryPolicy(final RouteDto route);

  RouteDetailsDto getDistance(final RouteDto route);
}
