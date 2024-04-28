package com.fackstoreintract.fackstoreinteract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FackStoreInteractApplication {

    public static void main(String[] args) {
        SpringApplication.run(FackStoreInteractApplication.class, args);
    }

}
