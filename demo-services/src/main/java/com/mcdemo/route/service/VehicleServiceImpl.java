package com.mcdemo.route.service;

import com.mcdemo.route.domain.entity.Vehicle;
import com.mcdemo.route.domain.repository.VehicleRepository;
import com.mcdemo.route.dto.BrandDto;
import com.mcdemo.route.dto.ModelDto;
import com.mcdemo.route.dto.VehicleDto;
import com.mcdemo.route.exceptions.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by João on 2019-10-01.
 */
@Service
public class VehicleServiceImpl implements VehicleService {

  private static final Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class);
  private final VehicleRepository vehicleRepository;
  private final ModelMapper modelMapper;

  public VehicleServiceImpl(final VehicleRepository vehicleRepository, final ModelMapper modelMapper) {
    this.vehicleRepository = vehicleRepository;
    this.modelMapper = modelMapper;
  }

  public List<VehicleDto> getAll() {
    final List<Vehicle> all = vehicleRepository.findAll();
    LOGGER.info("Retrieved {} vehicles.", all.size());
    return all.stream().map(this::getVehicleDto).collect(Collectors.toList());
  }

  public VehicleDto getOne(final String key) throws NotFoundException {
    final Vehicle vehicle = vehicleRepository.findById(key).orElseThrow(() -> new NotFoundException("Vehicle not found."));
    LOGGER.info("Retrieved vehicle: {} :: {} :: {}", vehicle.getModel().getBrand(), vehicle.getModel().getName(), vehicle.getModel().getYear());
    return getVehicleDto(vehicle);
  }

  public void delete(final String key) {
    vehicleRepository.deleteById(key);
    LOGGER.info("Deleted vehicle {} successfully.", key);
  }

  public VehicleDto save(final VehicleDto vehicleDto) {
    vehicleRepository.findById(vehicleDto.getLicensePlate()).ifPresent(vehicle -> {
      throw new IllegalArgumentException("Vehicle with license plate " + vehicle.getLicensePlate() + " already exists.");
    });

    final Vehicle vehicle = modelMapper.map(vehicleDto, Vehicle.class);
    LOGGER.info("Saved vehicle {} successfully.", vehicle.getLicensePlate());
    return modelMapper.map(vehicleRepository.saveAndFlush(vehicle), VehicleDto.class);
  }

  private VehicleDto getVehicleDto(final Vehicle vehicle) {
    final VehicleDto vehicleDto = modelMapper.map(vehicle, VehicleDto.class);
    vehicleDto.setBrandDto(modelMapper.map(vehicle.getBrand(), BrandDto.class));
    vehicleDto.setModelDto(modelMapper.map(vehicle.getModel(), ModelDto.class));
    return vehicleDto;
  }
}
