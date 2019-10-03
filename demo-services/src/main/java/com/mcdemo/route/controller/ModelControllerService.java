package com.mcdemo.route.controller;

import com.mcdemo.route.dto.ModelDto;
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
@Api(value = ModelController.V1_MODELS, description = "Operations about Vehicle's models", tags = "Models",
    produces = MediaType.APPLICATION_JSON_VALUE, protocols = "HTTP")
public interface ModelControllerService {

  @ApiOperation(value = "Add a new Model", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST")
  @ApiImplicitParams({@ApiImplicitParam(name = "brand", type = "String", required = true, paramType = "path", example = "Audi"),
      @ApiImplicitParam(name = "model", type = "ModelDto", required = true, paramType = "body")})
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Response without errors. Body is empty. "),
      @ApiResponse(code = 400, message = "The request was invalid. Occurs when invalid values are used."),
      @ApiResponse(code = 403, message = "Forbidden access to resource."),
      @ApiResponse(code = 404, message = "No resource found – There is no resource behind the URI."),
      @ApiResponse(code = 500, message = "Internal Server Error. Occurs when something went terribly wrong.")
  })
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity addModel(@PathVariable("brand") String brandName, @RequestBody ModelDto model) throws NotFoundException;

  @ApiOperation(value = "Retrieve information of one Model", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
  @ApiImplicitParams({@ApiImplicitParam(name = "brand", type = "String", required = true, paramType = "path", example = "Chevrolet"),
      @ApiImplicitParam(name = "id", type = "String", required = true, paramType = "path", example = "97")
  })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Response without errors. Body must not be empty. "),
      @ApiResponse(code = 400, message = "The request was invalid. Occurs when invalid values are used."),
      @ApiResponse(code = 403, message = "Forbidden access to resource."),
      @ApiResponse(code = 404, message = "No resource found – There is no resource behind the URI."),
      @ApiResponse(code = 500, message = "Internal Server Error. Occurs when something went terribly wrong.")
  })
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity getModel(@PathVariable("brand") String brand,@PathVariable("id") String id) throws NotFoundException;

  @ApiOperation(value = "Retrieve information of all models",
      produces = MediaType.APPLICATION_JSON_VALUE, response = ModelDto.class, httpMethod = "GET")
  @ApiImplicitParams({@ApiImplicitParam(name = "brand", type = "String", required = true, paramType = "path", example = "Chevrolet")})
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Response without errors. Body may be empty. "),
      @ApiResponse(code = 400, message = "The request was invalid. Occurs when invalid values are used."),
      @ApiResponse(code = 403, message = "Forbidden access to resource."),
      @ApiResponse(code = 404, message = "No resource found – There is no resource behind the URI."),
      @ApiResponse(code = 500, message = "Internal Server Error. Occurs when something went terribly wrong.")
  })
  @GetMapping
  ResponseEntity getAll(@PathVariable("brand") String brandName) throws NotFoundException;

  @ApiOperation(value = "Removes one Model", produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "DELETE")
  @ApiImplicitParams({@ApiImplicitParam(name = "brand", type = "String", required = true, paramType = "path", example = "Chevrolet"),
      @ApiImplicitParam(name = "id", type = "String", required = true, paramType = "path", example = "97")})
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "Response without errors. Body is empty. "),
      @ApiResponse(code = 400, message = "The request was invalid. Occurs when invalid values are used."),
      @ApiResponse(code = 403, message = "Forbidden access to resource."),
      @ApiResponse(code = 404, message = "No resource found – There is no resource behind the URI."),
      @ApiResponse(code = 500, message = "Internal Server Error. Occurs when something went terribly wrong.")
  })
  @DeleteMapping(path = "/{id}")
  ResponseEntity deleteModel(@PathVariable("brand") String brand, @PathVariable("id") String id) throws NotFoundException;
}
