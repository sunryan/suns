package com.ryan.suns.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lr
 * @date 2018/3/22.
 */
@RestController
public class TestController {

    @RequestMapping(value = "/ceshi")
    public String ceshi(){
        return "ceshi";
    }
}
