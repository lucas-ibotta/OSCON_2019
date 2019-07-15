package com.oscon.demo.controllers;

import com.oscon.demo.models.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // it will serialize JSON <> POJO automatically
public class HelloRestController {

    @GetMapping("/rest")
    public Greeting greet(@RequestParam(required = false,
            defaultValue = "World") String name) {
        return new Greeting(String.format("Hello, %s!", name));
    }
}
