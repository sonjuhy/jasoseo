package com.intergrated.jasoseo.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class defaultController {

    @GetMapping("/hi")
    public List<String> hi(){
        return Arrays.asList("hi?", "안녕?");
    }
}
