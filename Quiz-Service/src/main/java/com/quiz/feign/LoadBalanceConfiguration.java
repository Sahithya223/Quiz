package com.quiz.feign;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Feign;

@Configuration
@LoadBalancerClient("question-service")
public class LoadBalanceConfiguration {

    @LoadBalanced
    @Bean
    Feign.Builder getFeignBuilder()
	{
		return Feign.builder();
	}
}
