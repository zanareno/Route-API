package com.mcdemo.route.controller;

import static com.mcdemo.route.controller.VehicleController.V1_VEHICLES;

import com.mcdemo.route.dto.BrandDto;
import com.mcdemo.route.dto.ModelDto;
import com.mcdemo.route.dto.VehicleDto;
import com.mcdemo.route.exceptions.NotFoundException;
import com.mcdemo.route.service.BrandService;
import com.mcdemo.route.service.ModelService;
import com.mcdemo.route.service.VehicleService;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by João on 2019-09-30.
 */
@RestController
@RequestMapping(path = V1_VEHICLES)
public class VehicleController implements VehicleControllerService {
  public final static String V1_VEHICLES = "/vehicles";
  private final VehicleService vehicleService;
  private final BrandService brandService;
  private final ModelService modelService;

  public VehicleController(final VehicleService vehicleService, final BrandService brandService, final ModelService modelService) {
    this.vehicleService = vehicleService;
    this.brandService = brandService;
    this.modelService = modelService;
  }

  @Override
  @PostMapping
  public ResponseEntity addVehicle(@RequestBody VehicleDto vehicle) throws NotFoundException {
    validateInput(vehicle);
    final VehicleDto addedVehicle = vehicleService.save(vehicle);
    final URI location = ServletUriComponentsBuilder
        .fromCurrentServletMapping()
        .path("/vehicles/{licensePlate}")
        .build()
        .expand(addedVehicle.getLicensePlate())
        .toUri();
    return ResponseEntity.created(location).build();
  }

  @Override
  @GetMapping(path = "/{licensePlate}")
  public ResponseEntity getVehicle(@PathVariable("licensePlate") String id) throws NotFoundException {
    return ResponseEntity.ok(vehicleService.getOne(id));
  }

  @Override
  @GetMapping
  public ResponseEntity getAll() {
    return ResponseEntity.ok(vehicleService.getAll());
  }

  @Override
  @DeleteMapping(path = "/{licensePlate}")
  public ResponseEntity deleteVehicle(@PathVariable("licensePlate") String id) {
    vehicleService.delete(id);
    return ResponseEntity.noContent().build();
  }

  private void validateInput(@RequestBody final VehicleDto vehicle) throws NotFoundException {
    final BrandDto brandDto = brandService.getOne(vehicle.getBrandDto().getName());
    final ModelDto modelDto = modelService.getOne(vehicle.getModelDto().getId());
    vehicle.setBrandDto(brandDto);
    vehicle.setModelDto(modelDto);
  }
}
