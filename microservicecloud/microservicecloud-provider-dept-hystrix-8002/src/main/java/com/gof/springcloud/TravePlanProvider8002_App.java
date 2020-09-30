package com.gof.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //Register into eureka after initiation
@EnableDiscoveryClient
@EnableCircuitBreaker //import hystrix for fusing mechanism
@EnableCaching
public class TravePlanProvider8002_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(TravePlanProvider8002_App.class, args);
	}
}
