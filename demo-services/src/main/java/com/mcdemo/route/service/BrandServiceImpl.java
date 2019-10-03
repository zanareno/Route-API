package com.mcdemo.route.service;

import com.mcdemo.route.domain.entity.Brand;
import com.mcdemo.route.domain.repository.BrandRepository;
import com.mcdemo.route.dto.BrandDto;
import com.mcdemo.route.exceptions.NotFoundException;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by João on 2019-10-01.
 */
@Service
public class BrandServiceImpl implements BrandService {

  private static final Logger LOGGER = LoggerFactory.getLogger(BrandServiceImpl.class);

  private final BrandRepository brandRepository;
  private final ModelMapper modelMapper;

  public List<BrandDto> getAll() {
    java.lang.reflect.Type targetListType = new TypeToken<List<BrandDto>>() {
    }.getType();
    final List<Brand> all = brandRepository.findAll();
    LOGGER.info("Retrieved {} brands.", all.size());
    return modelMapper.map(all, targetListType);
  }

  public BrandDto getOne(final String key) throws NotFoundException {
    final Brand brand = brandRepository.findById(key).orElseThrow(() -> new NotFoundException("Brand not found."));
    LOGGER.info("Retrieved brand: {}", brand.getName());
    return modelMapper.map(brand, BrandDto.class);
  }

  public void delete(final String key) {
    brandRepository.deleteById(key);
    LOGGER.info("Deleted brand {} successfully.", key);
  }

  public BrandDto save(final BrandDto brandDto) {
    final Brand brand = modelMapper.map(brandDto, Brand.class);
    final Brand newBrand = brandRepository.saveAndFlush(brand);
    LOGGER.info("Saved brand {} successfully.", newBrand.getName());
    return modelMapper.map(newBrand, BrandDto.class);
  }

  public BrandServiceImpl(final BrandRepository brandRepository, final ModelMapper modelMapper) {
    this.brandRepository = brandRepository;
    this.modelMapper = modelMapper;
  }
}
