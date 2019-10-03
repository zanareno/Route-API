package com.mcdemo.route.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by João on 2019-09-30.
 */
@Data
public class VehicleDto {

  private String licensePlate;
  @JsonProperty("brand")
  private BrandDto brandDto;
  @JsonProperty("model")
  private ModelDto modelDto;
  @JsonProperty("average_consumption")
  private Double avgConsumption;
  private Fuel fuel;

}
