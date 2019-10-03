package com.mcdemo.route.service;

import com.mcdemo.route.domain.entity.Model;
import com.mcdemo.route.domain.repository.ModelRepository;
import com.mcdemo.route.dto.ModelDto;
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
public class ModelServiceImpl implements ModelService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ModelServiceImpl.class);

  private final ModelRepository modelRepository;
  private final ModelMapper modelMapper;

  public List<ModelDto> getAll(final String brandName) {
    java.lang.reflect.Type targetListType = new TypeToken<List<ModelDto>>() {}.getType();
    final List<Model> all = modelRepository.findAllByBrand_name(brandName);
    LOGGER.info("Retrieved {} models of {}.", all.size(), brandName);
    return modelMapper.map(all, targetListType);
  }

  @Override
  public List<ModelDto> getAll() {
    java.lang.reflect.Type targetListType = new TypeToken<List<ModelDto>>() {}.getType();
    final List<Model> all = modelRepository.findAll();
    LOGGER.info("Retrieved {} models.", all.size());
    return modelMapper.map(all, targetListType);
  }

  @Override
  public ModelDto getOne(final Integer key) throws NotFoundException {
    final Model one = modelRepository.findById(key).orElseThrow(() -> new NotFoundException("Model not found."));
    final ModelDto map = modelMapper.map(one, ModelDto.class);
    LOGGER.info("Retrieved Model: {} of brand {}", one.getName(), one.getBrand().getName());
    return map;
  }

  public ModelDto getOne(final String brandName, final Integer key) throws NotFoundException {
    final Model one = modelRepository.findByIdAndBrand_name(key, brandName).orElseThrow(() -> new NotFoundException("Model not found."));
    final ModelDto map = modelMapper.map(one, ModelDto.class);
    LOGGER.info("Retrieved Model: {} of brand {}", one.getName(), one.getBrand().getName());
    return map;
  }

  public void delete(final Integer key) {
    modelRepository.deleteById(key);
    LOGGER.info("Deleted model {} successfully.", key);
  }

  public void delete(final String brandName, final Integer key) throws NotFoundException {
    getOne(brandName, key);
    modelRepository.deleteByIdAndBrand_name(key, brandName);
    LOGGER.info("Deleted model {} successfully.", key);
  }

  public ModelDto save(final ModelDto modelDto) {
    final Model model = modelMapper.map(modelDto, Model.class);
    LOGGER.info("Saved model {} successfully.", model.getName());
    return modelMapper.map(modelRepository.saveAndFlush(model), ModelDto.class);
  }

  public ModelServiceImpl(final ModelRepository modelRepository, final ModelMapper modelMapper) {
    this.modelRepository = modelRepository;
    this.modelMapper = modelMapper;
  }

}
