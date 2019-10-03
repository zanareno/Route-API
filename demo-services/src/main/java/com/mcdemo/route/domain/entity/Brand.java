package com.mcdemo.route.domain.entity;

import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.ToString;

/**
 * Created by João on 2019-09-30.
 */
@Data
@ToString(onlyExplicitlyIncluded = true)
@Entity
public class Brand {

  @Id
  private String name;

  @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
  private List<Model> models;

  @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
  private Collection<Vehicle> vehicles;


}
