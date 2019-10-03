package com.mcdemo.route.service;

import com.mcdemo.route.dto.RouteDetailsDto;
import com.mcdemo.route.dto.VehicleDto;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by João on 2019-09-30.
 */
@Service
public class RouteServiceImpl implements RouteService {

  private static final Logger LOGGER = LoggerFactory.getLogger(RouteServiceImpl.class);

  public RouteDetailsDto estimateCosts(final RouteDetailsDto routeDetailsDto, final VehicleDto vehicleDto, final Double unitPrice) {

    final BigDecimal costs = BigDecimal.valueOf(vehicleDto.getAvgConsumption())
        .multiply(BigDecimal.valueOf(routeDetailsDto.getDistanceDto().getValue())
            .divide(BigDecimal.valueOf(1000),5, RoundingMode.CEILING)
            .divide(BigDecimal.valueOf(100),5, RoundingMode.CEILING)
            .multiply(BigDecimal.valueOf(unitPrice))).setScale(2, BigDecimal.ROUND_HALF_UP);
    LOGGER.info("Calculated costs for vehicle {}. Costs: {}.", vehicleDto.getLicensePlate(), costs);
    return routeDetailsDto.withCost(costs);
  }
}
