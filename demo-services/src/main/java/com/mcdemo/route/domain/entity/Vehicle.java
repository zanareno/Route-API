package com.mcdemo.route.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

/**
 * Created by João on 2019-09-30.
 */
@Data
@Entity
@ToString(onlyExplicitlyIncluded = true)
public class Vehicle {

  @Id
  private String licensePlate;
  @ManyToOne
  private Brand brand;
  @ManyToOne
  private Model model;
  private Double avgConsumption;
  private String fuel;

}
