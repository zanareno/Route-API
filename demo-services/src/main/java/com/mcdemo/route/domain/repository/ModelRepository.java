package com.mcdemo.route.domain.repository;

import com.mcdemo.route.domain.entity.Model;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 * Created by João on 2019-09-30.
 */
@Service
public interface ModelRepository extends JpaRepository<Model, Integer>, PagingAndSortingRepository<Model, Integer> {

   Optional<Model> findByIdAndBrand_name(Integer id, String name);

  void deleteByIdAndBrand_name(Integer id, String name);

  List<Model> findAllByBrand_name(String brandName);
}
