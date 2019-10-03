package com.mcdemo.route.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by João on 2019-09-30.
 */
@Data
@NoArgsConstructor
public class BrandDto {

  private String name;

  public BrandDto(final String name) {
    this.name = name;
  }
}
