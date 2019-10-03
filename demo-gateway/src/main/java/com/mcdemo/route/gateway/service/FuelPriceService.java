package com.mcdemo.route.gateway.service;

import com.mcdemo.route.dto.Fuel;

/**
 * Created by João on 2019-10-01.
 */
public interface FuelPriceService {

  Double getPrice(final Fuel fuel);
}
