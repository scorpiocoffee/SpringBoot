package com.demo.selfhttp.config;

import com.demo.selfhttp.converter.PropertiesHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @PostConstruct
    public void init() {
        List<HandlerMethodArgumentResolver> argumentResolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> newArgumentResolvers = new ArrayList<>(argumentResolvers.size() + 1);
    }

    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(0, new PropertiesHttpMessageConverter());
    }
}
