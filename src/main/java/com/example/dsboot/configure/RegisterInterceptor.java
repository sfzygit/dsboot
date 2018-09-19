package com.example.dsboot.configure;

import com.example.dsboot.interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
public class RegisterInterceptor implements WebMvcConfigurer {

    @Bean
    public SecurityInterceptor securityInterceptor(){
        return new SecurityInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor()).addPathPatterns(Arrays.asList("/test/**","/user/**"))
                                                      .excludePathPatterns("/user/login")
                                                      .excludePathPatterns(Arrays.asList("/static/**","/error"));


    }
}
