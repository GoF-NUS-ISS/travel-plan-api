package com.gof.springcloud.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RetryRule;

/**
 * @Description:ConfigBean = applicationContext.xml
 * Spring Cloud Ribbon, a toolkit provided by Netflix, implementing the function of load balance
 */
@Configuration
public class ConfigBean
{
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}

	@Bean
	public IRule myRule()
	{
		//return new RoundRobinRule();
		//return new RandomRule();
		return new RetryRule();
	}
}
