package com.mcdemo.route.controller;

import com.mcdemo.route.dto.VehicleDto;
import com.mcdemo.route.exceptions.NotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by João on 2019-10-03.
 */
@Api(value = VehicleController.V1_VEHICLES, description = "Operations about Route Vehicles", tags = "Vehicles",
    produces = MediaType.APPLICATION_JSON_VALUE, protocols = "HTTP")
public interface VehicleControllerService {

  @ApiOperation(value = "Add a new Vehicle",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST")
  @ApiImplicitParams({@ApiImplicitParam(name = "vehicle", type = "VehicleDto", required = true, paramType = "body") })
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Response without errors. Body is empty. "),
      @ApiResponse(code = 400, message = "The request was invalid. Occurs when invalid values are used."),
      @ApiResponse(code = 403, message = "Forbidden access to resource."),
      @ApiResponse(code = 404, message = "No resource found – There is no resource behind the URI."),
      @ApiResponse(code = 500, message = "Internal Server Error. Occurs when something went terribly wrong.")
  })
  @PostMapping
  ResponseEntity addVehicle(@RequestBody VehicleDto vehicle) throws NotFoundException;

  @ApiOperation(value = "Retrieve information of one Vehicle", produces = MediaType.APPLICATION_JSON_VALUE, response = VehicleDto.class,  httpMethod = "GET")
  @ApiImplicitParams({@ApiImplicitParam(name = "licensePlate", type = "String", required = true, paramType = "path", example = "12-AV-54")})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Response without errors. Body must not be empty. "),
      @ApiResponse(code = 400, message = "The request was invalid. Occurs when invalid values are used."),
      @ApiResponse(code = 403, message = "Forbidden access to resource."),
      @ApiResponse(code = 404, message = "No resource found – There is no resource behind the URI."),
      @ApiResponse(code = 500, message = "Internal Server Error. Occurs when something went terribly wrong.")
  })
  @GetMapping(path = "/{licensePlate}")
  ResponseEntity getVehicle(@PathVariable("licensePlate") String id) throws NotFoundException;

  @ApiOperation(value = "Retrieve information of all Vehicles",
      produces = MediaType.APPLICATION_JSON_VALUE, response = VehicleDto.class, httpMethod = "GET")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Response without errors. Body may be empty. "),
      @ApiResponse(code = 400, message = "The request was invalid. Occurs when invalid values are used."),
      @ApiResponse(code = 403, message = "Forbidden access to resource."),
      @ApiResponse(code = 404, message = "No resource found – There is no resource behind the URI."),
      @ApiResponse(code = 500, message = "Internal Server Error. Occurs when something went terribly wrong.")
  })
  @GetMapping
  ResponseEntity getAll();

  @ApiOperation(value = "Removes one Vehicle", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "DELETE")
  @ApiImplicitParams({@ApiImplicitParam(name = "licensePlate", type = "String", required = true, paramType = "path", example = "12-AV-54")})
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "Response without errors. Body is empty. "),
      @ApiResponse(code = 400, message = "The request was invalid. Occurs when invalid values are used."),
      @ApiResponse(code = 403, message = "Forbidden access to resource."),
      @ApiResponse(code = 404, message = "No resource found – There is no resource behind the URI."),
      @ApiResponse(code = 500, message = "Internal Server Error. Occurs when something went terribly wrong.")
  })
  @DeleteMapping(path = "/{licensePlate}")
  ResponseEntity deleteVehicle(@PathVariable("licensePlate") String id);
}
