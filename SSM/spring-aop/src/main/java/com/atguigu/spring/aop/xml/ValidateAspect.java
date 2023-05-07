package com.atguigu.spring.aop.xml;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Drew
 * @create 2023-03
 */

@Component
public class ValidateAspect {


    public void beforeMethod(){
        System.out.println("ValidateAspect-->前置通知");
    }

}
