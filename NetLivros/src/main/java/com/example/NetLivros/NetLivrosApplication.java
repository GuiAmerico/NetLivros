package com.example.NetLivros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class NetLivrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetLivrosApplication.class, args);
	}

}
