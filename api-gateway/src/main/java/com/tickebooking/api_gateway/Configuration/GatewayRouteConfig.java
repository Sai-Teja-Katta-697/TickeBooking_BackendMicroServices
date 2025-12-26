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
                .build();
    }
}
