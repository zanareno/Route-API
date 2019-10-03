package com.mcdemo.route.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Model {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private int year;

  @ManyToOne
  private Brand brand;


}
