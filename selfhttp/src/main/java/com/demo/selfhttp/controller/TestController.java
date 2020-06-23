package com.demo.selfhttp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Properties;

@Controller
public class TestController {
    @GetMapping(value = "test", consumes = "text/properties")
    @ResponseBody
    public Properties test(@RequestBody Properties properties) {
        return properties;
    }
}
