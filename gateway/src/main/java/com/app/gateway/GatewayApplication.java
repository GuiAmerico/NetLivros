package com.app.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/api/v1/books/**").uri("lb://msnetlivros"))
				.route(r -> r.path("/api/v1/authors/**").uri("lb://msnetlivros"))
				.route(r -> r.path("/api/v1/auth/**").uri("lb://msauth"))
				.route(r -> r.path("/api/v1/publisher/**").uri("lb://msavaliadorcredito"))
				.build();
	}
}
