package com.demo.aoplog.controller;

import com.demo.aoplog.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    @Log("Method One")
    @GetMapping("/one")
    public void methodOne(String name) {

    }

    @Log("Method Two")
    @GetMapping("/two")
    public void methodTwo() throws InterruptedException {
        Thread.sleep(2000);
    }

    @Log("Method Three")
    @GetMapping("/three")
    public void methodThree(String name, String arg) {

    }
}
