package com.mcdemo.route.service;

import com.mcdemo.route.dto.ModelDto;
import com.mcdemo.route.exceptions.NotFoundException;
import java.util.List;

/**
 * Created by João on 2019-09-30.
 */
public interface ModelService extends CrudService<ModelDto, Integer> {

  List<ModelDto> getAll(final String brandName);

  ModelDto getOne(final String brandName, final Integer key) throws NotFoundException;

  void delete(final String brandName, final Integer key) throws NotFoundException;

}
