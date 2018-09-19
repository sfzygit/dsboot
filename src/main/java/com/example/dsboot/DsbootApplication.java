package com.example.dsboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DsbootApplication {
    private final static Logger logger = LoggerFactory.getLogger(DsbootApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(DsbootApplication.class, args);
        logger.info("spring boot application stated ...");
    }
}
