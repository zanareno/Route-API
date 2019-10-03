package com.mcdemo.route.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by João on 2019-09-30.
 */
@Data
@NoArgsConstructor
public class ModelDto {

  private int id;
  private String name;
  private int year;
  @JsonProperty("brand")
  private String brandDto;

  public ModelDto(final String name, final int year) {
    this.name = name;
    this.year = year;
  }
}
