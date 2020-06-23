package com.demo.selfhttp.resolver;

import org.springframework.http.MediaType;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertiesHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Properties.class.equals(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        ServletWebRequest servletWebRequest = (ServletWebRequest) nativeWebRequest;
        HttpServletRequest request = servletWebRequest.getRequest();
        String contentType = request.getHeader("Content-Type");
        MediaType mediaType = MediaType.parseMediaType(contentType);
        Charset charset = mediaType.getCharset() == null ? Charset.forName("UTF-8") : mediaType.getCharset();
        InputStream inputStream = request.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);
        Properties properties = new Properties();
        properties.load(inputStreamReader);
        return properties;
    }
}
