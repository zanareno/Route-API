package com.mcdemo.route.controller;

import static com.mcdemo.route.controller.BrandController.V1_BRANDS;

import com.mcdemo.route.dto.BrandDto;
import com.mcdemo.route.exceptions.NotFoundException;
import com.mcdemo.route.service.BrandService;
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
@RequestMapping(path = V1_BRANDS)
public class BrandController implements BrandControllerService{
  public final static String V1_BRANDS = "/brands";
  private final BrandService brandService;

  @Override
  @PostMapping
  public ResponseEntity addBrand(@RequestBody BrandDto brand) {
    final BrandDto addedBrand = brandService.save(brand);
    final URI location = ServletUriComponentsBuilder
        .fromCurrentServletMapping()
        .path("/brands/{name}")
        .build()
        .expand(addedBrand.getName())
        .toUri();
    return ResponseEntity.created(location).build();
  }

  @Override
  @GetMapping(path = "/{name}")
  public ResponseEntity getBrand(@PathVariable("name") String name) throws NotFoundException {
    return ResponseEntity.ok(brandService.getOne(name));
  }

  @Override
  @GetMapping
  public ResponseEntity getAll() {
    return ResponseEntity.ok(brandService.getAll());
  }

  @Override
  @DeleteMapping(path = "/{name}")
  public ResponseEntity deleteBrand(@PathVariable("name") String name) {
    brandService.delete(name);
    return ResponseEntity.noContent().build();
  }

  public BrandController(final BrandService brandService) {
    this.brandService = brandService;
  }
}
