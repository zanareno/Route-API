package com.mcdemo.route.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

/**
 * Created by João on 2019-10-02.
 */
@Data
public final class RouteDetailsDto {

  @JsonProperty("route")
  private RouteDto routeDto;
  private DistanceDto distanceDto;
  private DurationDto duration;
  private BigDecimal cost;


  public static RouteDetailsDto aRouteDetails() {
    return new RouteDetailsDto();
  }

  public RouteDetailsDto withRouteDto(RouteDto routeDto) {
    this.routeDto = routeDto;
    return this;
  }

  public RouteDetailsDto withDistance(DistanceDto distance) {
    this.distanceDto = distance;
    return this;
  }

  public RouteDetailsDto withDuration(DurationDto duration) {
    this.duration = duration;
    return this;
  }

  public RouteDetailsDto withCost(BigDecimal cost) {
    this.cost = cost;
    return this;
  }

  public RouteDetailsDto build() {
    RouteDetailsDto routeDetailsDto = new RouteDetailsDto();
    routeDetailsDto.setRouteDto(routeDto);
    this.setDistanceDto(distanceDto);
    routeDetailsDto.setDuration(duration);
    routeDetailsDto.setCost(cost);
    return routeDetailsDto;
  }

  private RouteDetailsDto() {
  }
}
