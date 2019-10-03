package com.mcdemo.route.service;

import com.mcdemo.route.exceptions.NotFoundException;
import java.util.List;

/**
 * Created by João on 2019-09-30.
 */
public interface CrudService<T, K> {

  List<T> getAll();

  T getOne(K key) throws NotFoundException;

  void delete(K key);

  T save(T object);
}
