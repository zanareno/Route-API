package com.mcdemo.route.controller;

import com.mcdemo.route.dto.BrandDto;
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
 * Created by Joao on 2019-10-03.
 */
@Api(value = BrandController.V1_BRANDS, description = "Operations about Vehicle's brands", tags = "Brands",
    produces = MediaType.APPLICATION_JSON_VALUE, protocols = "HTTP")
public interface BrandControllerService {

  @ApiOperation(value = "Add a new Brand", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST")
  @ApiImplicitParams({@ApiImplicitParam(name = "brand", type = "BrandDto", required = true, paramType = "body")})
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Response without errors. Body is empty. "),
      @ApiResponse(code = 400, message = "The request was invalid. Occurs when invalid values are used."),
      @ApiResponse(code = 403, message = "Forbidden access to resource."),
      @ApiResponse(code = 404, message = "No resource found – There is no resource behind the URI."),
      @ApiResponse(code = 500, message = "Internal Server Error. Occurs when something went terribly wrong.")
  })
  @PostMapping
  ResponseEntity addBrand(@RequestBody BrandDto brand);

  @ApiOperation(value = "Retrieve information of one Brand", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
  @ApiImplicitParams({@ApiImplicitParam(name = "name", type = "String", required = true, paramType = "path", example = "audi")})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Response without errors. Body must not be empty. "),
      @ApiResponse(code = 400, message = "The request was invalid. Occurs when invalid values are used."),
      @ApiResponse(code = 403, message = "Forbidden access to resource."),
      @ApiResponse(code = 404, message = "No resource found – There is no resource behind the URI."),
      @ApiResponse(code = 500, message = "Internal Server Error. Occurs when something went terribly wrong.")
  })
  @GetMapping(path = "/{name}")
  ResponseEntity getBrand(@PathVariable("name") String name) throws NotFoundException;

  @ApiOperation(value = "Retrieve information of all brands", produces = MediaType.APPLICATION_JSON_VALUE, response = BrandDto.class, httpMethod = "GET")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Response without errors. Body may be empty. "),
      @ApiResponse(code = 400, message = "The request was invalid. Occurs when invalid values are used."),
      @ApiResponse(code = 403, message = "Forbidden access to resource."),
      @ApiResponse(code = 404, message = "No resource found – There is no resource behind the URI."),
      @ApiResponse(code = 500, message = "Internal Server Error. Occurs when something went terribly wrong.")
  })
  @GetMapping
  ResponseEntity getAll();

  @ApiOperation(value = "Removes one Brand", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "DELETE")
  @ApiImplicitParams({@ApiImplicitParam(name = "name", type = "String", required = true, paramType = "path", example = "audi")})
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "Response without errors. Body is empty. "),
      @ApiResponse(code = 400, message = "The request was invalid. Occurs when invalid values are used."),
      @ApiResponse(code = 403, message = "Forbidden access to resource."),
      @ApiResponse(code = 404, message = "No resource found – There is no resource behind the URI."),
      @ApiResponse(code = 500, message = "Internal Server Error. Occurs when something went terribly wrong.")
  })
  @DeleteMapping(path = "/{name}")
  ResponseEntity deleteBrand(@PathVariable("name") String name);
}
