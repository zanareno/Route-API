package com.mcdemo.route.gateway.service;

import com.mcdemo.route.dto.Fuel;
import org.springframework.stereotype.Service;

/**
 * Created by João on 2019-10-03.
 */
@Service
public class FuelPriceServiceImpl implements FuelPriceService {

  @Override
  public Double getPrice(final Fuel fuel) {
    switch (fuel){
      case DIESEL: return 1.321;
      case GAS_95: return 1.501;
      case GAS_98: return 1.652;
      case PROPANE: return 0.676;
      case ELECTRIC: return 0.3;
      default: return null;
    }
  }
}
