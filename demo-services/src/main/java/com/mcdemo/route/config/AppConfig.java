package com.mcdemo.route.config;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by João on 2019-09-30.
 */
@Data
@Configuration
public class AppConfig {

  @Bean
  public ModelMapper modelMapper() {
    final ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
    modelMapper.getConfiguration().setSkipNullEnabled(true);
    return modelMapper;
  }

}
