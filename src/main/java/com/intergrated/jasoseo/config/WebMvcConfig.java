package com.intergrated.jasoseo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final List<String> endPointList = Arrays.asList("/content/**","/ann/**");
    private final List<String> excludePointList = Arrays.asList("/user/**","/auth/**");

    @Autowired
    private SpringInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns(endPointList)
                .excludePathPatterns(excludePointList);
    }
}
