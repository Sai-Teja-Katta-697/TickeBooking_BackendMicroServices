package com.tickebooking.api_gateway.Configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRouteConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("user-service", r -> r
                        .path("/user-service/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .prefixPath("/user-service/v1")
                        )
                        .uri("lb://USER-SERVICE")
                )
                .route("bus-service",r->r
                        .path("/bus-service/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .prefixPath("/bus-service/v1")
                        )
                        .uri("lb://BUS-SERVICE"))
                .route("booking-service",r->r
                        .path("/booking-service/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .prefixPath("/booking-service/v1")
                        )
                        .uri("lb://BOOKING-SERVICE"))//booking-service
                .build();
    }
}
