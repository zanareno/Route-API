package com.mcdemo.route.gateway.dto;


import com.mcdemo.route.dto.DistanceDto;
import com.mcdemo.route.dto.DurationDto;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by João on 2019-09-30.
 */
@Data
@NoArgsConstructor
public class GoogleResultDto {

    private DistanceDto distance;
    private DurationDto duration;
    private String status;

}
