package com.gof.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableCaching
@EnableEurekaClient //Register into eureka after initiation
@EnableDiscoveryClient
@EnableCircuitBreaker //import hystrix for fusing mechanism
public class TravelPlanProvider8001_Hystrix_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(TravelPlanProvider8001_Hystrix_App.class, args);
	}
}
