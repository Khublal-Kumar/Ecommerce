package com.press.Ecommerce.config;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AllConfig {
	
	@Bean
    public AtomicLong cartIdGenerator() {
        return new AtomicLong();
    }

}
