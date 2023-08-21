package com.headlightbackend.config;

import com.headlightbackend.components.HttpLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogConfig {
    @Bean
    public FilterRegistrationBean<HttpLoggingFilter> loggingFilter() {
        FilterRegistrationBean<HttpLoggingFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new HttpLoggingFilter());
        registrationBean.addUrlPatterns("/*");

        return registrationBean;
    }
}
