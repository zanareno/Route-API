package com.mcdemo.route.controller;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;

/**
 * Created by João on 2019-10-03.
 */
@Api(value = RouteController.V1_ROUTES, description = "Obtain a route cost and details", tags = "Route",
    produces = MediaType.APPLICATION_JSON_VALUE, protocols = "HTTP")
public interface RouteControllerService {

}
