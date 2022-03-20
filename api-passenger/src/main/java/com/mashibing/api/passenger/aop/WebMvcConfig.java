package com.mashibing.api.passenger.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginIntercepter loginIntercepter;

    @Autowired
    private LoginIntercepter2 loginIntercepter2;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntercepter);
        registry.addInterceptor(loginIntercepter2);
    }

    @Bean
    public FilterRegistrationBean baseFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        BaseFilter contextFilter = new BaseFilter();
        registrationBean.setFilter(contextFilter);
        registrationBean.setOrder(55);
        registrationBean.addUrlPatterns("/*");
        log.info("initBaseFilter succ.");
        return registrationBean;
    }
}