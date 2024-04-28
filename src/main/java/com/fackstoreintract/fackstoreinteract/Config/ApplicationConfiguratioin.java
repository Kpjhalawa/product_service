package com.fackstoreintract.fackstoreinteract.Config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApplicationConfiguratioin {
    @Bean
    @LoadBalanced
    public RestTemplate createRestTemplate(){

        return new RestTemplate();
    }
}
