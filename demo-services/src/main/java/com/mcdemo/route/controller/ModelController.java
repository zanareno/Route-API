package com.mcdemo.route.controller;

import static com.mcdemo.route.controller.ModelController.V1_MODELS;

import com.mcdemo.route.dto.BrandDto;
import com.mcdemo.route.dto.ModelDto;
import com.mcdemo.route.exceptions.NotFoundException;
import com.mcdemo.route.service.BrandService;
import com.mcdemo.route.service.ModelService;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.NumberUtils;
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
@RequestMapping(path = V1_MODELS)
public class ModelController implements ModelControllerService {

  public static final String V1_MODELS = "/brands/{brand}/models";
  private final ModelService modelService;
  private final BrandService brandService;

  @Override
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity addModel(@PathVariable("brand") String brandName, @RequestBody ModelDto model) throws NotFoundException {
    brandService.getOne(brandName);
    model.setBrandDto(brandName);
    final ModelDto addedModel = modelService.save(model);
    final URI location = ServletUriComponentsBuilder
        .fromCurrentServletMapping()
        .path("/brands/{brand}/models/{id}")
        .build()
        .expand(brandName, addedModel.getId())
        .toUri();
    return ResponseEntity.created(location).build();
  }

  @Override
  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getModel(@PathVariable("brand") String brandName, @PathVariable("id") String id) throws NotFoundException {
    final Integer idNumber = NumberUtils.parseNumber(id, Integer.class);
    return ResponseEntity.ok(modelService.getOne(brandName, idNumber));
  }

  @Override
  @GetMapping
  public ResponseEntity getAll(@PathVariable("brand") String brandName) throws NotFoundException {
    brandService.getOne(brandName);
    return ResponseEntity.ok(modelService.getAll(brandName));
  }

  @Override
  @DeleteMapping(path = "/{id}")
  public ResponseEntity deleteModel(@PathVariable("brand") String brandName, @PathVariable("id") String id) throws NotFoundException {
    final Integer idNumber = NumberUtils.parseNumber(id, Integer.class);
    modelService.delete(brandName, idNumber);
    return ResponseEntity.noContent().build();
  }

  public ModelController(final ModelService modelService, final BrandService brandService) {
    this.modelService = modelService;
    this.brandService = brandService;
  }
}
