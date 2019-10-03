package com.mcdemo.route.domain.repository;

import com.mcdemo.route.domain.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 * Created by João on 2019-09-30.
 */
@Service
public interface BrandRepository extends JpaRepository<Brand, String>, PagingAndSortingRepository<Brand, String> {

}
