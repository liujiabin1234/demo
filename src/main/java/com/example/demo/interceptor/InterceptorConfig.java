package com.example.demo.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 注册自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加各个拦截器拦截的地址
        registry.addInterceptor(new InterceptorDemo()).addPathPatterns("/test");
        registry.addInterceptor(new InterceptorDemo2()).addPathPatterns("/test");
    }
}

