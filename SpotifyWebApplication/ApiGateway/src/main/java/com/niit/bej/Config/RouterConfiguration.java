package com.niit.bej.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RouterConfiguration {
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){
        return builder.routes().route(p->p.path("/userAuth/**").uri("http://localhost:8081/"))
                .route(p->p.path("/userTrack/**").uri("http://localhost:8082/")).build();
    }
}
