package com.atguigu.controller;

import com.atguigu.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Drew
 * @create 2023-03
 */

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;
}
