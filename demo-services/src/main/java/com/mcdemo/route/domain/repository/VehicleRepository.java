package com.mcdemo.route.domain.repository;

import com.mcdemo.route.domain.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

/**
 * Created by João on 2019-09-30.
 */
@Service
public interface VehicleRepository extends JpaRepository<Vehicle, String>, PagingAndSortingRepository<Vehicle, String> {

}
