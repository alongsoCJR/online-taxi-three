package com.mashibing.consumer.config;

import com.mashibing.consumer.interceptor.LoggingClientHttpRequestInterceptor;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConsumerConfig {

    @Bean
    // 开启负载均衡
    @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        RestTemplate restTemplate = builder.build();
        restTemplate.getInterceptors().add(new LoggingClientHttpRequestInterceptor());
        return restTemplate;
    }

//    @Bean
//    public IRule getRule(){
////        return new RandomRule();
//        return new RoundRobinRule();
//    }
}