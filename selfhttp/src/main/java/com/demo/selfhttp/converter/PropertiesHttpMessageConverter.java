package com.demo.selfhttp.converter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties> {
    public PropertiesHttpMessageConverter() {
        super(new MediaType("text", "properties"));
    }

    @Override
    protected void writeInternal(Properties properties, Type type, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        HttpHeaders headers = httpOutputMessage.getHeaders();
        MediaType contentType = headers.getContentType();
        Charset charset = null;
        if(null != contentType) {
            charset = contentType.getCharset();
        }
        charset = null == charset ? Charset.forName("UTF-8") : charset;
        OutputStream body = httpOutputMessage.getBody();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(body, charset);
        properties.store(outputStreamWriter, "Serialized by PropertiesHttpMessageConverter#writeInternal");
    }

    @Override
    protected Properties readInternal(Class<? extends Properties> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        Properties properties = new Properties();
        HttpHeaders headers = httpInputMessage.getHeaders();
        MediaType contentType = headers.getContentType();
        Charset charset = null;
        if(null != contentType) {
            charset = contentType.getCharset();
        }
        charset = null == charset ? Charset.forName("UTF-8") : charset;

        InputStream body = httpInputMessage.getBody();
        InputStreamReader inputStreamReader = new InputStreamReader(body, charset);
        properties.load(inputStreamReader);
        return properties;
    }

    @Override
    public Properties read(Type type, Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(null, httpInputMessage);
    }
}
