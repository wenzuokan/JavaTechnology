package com.atguigu.spring.test;

import com.atguigu.spring.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Drew
 * @create 2023-03
 *
 * FactoryBean是一个接口，需要创建一个类实现该接口
 * 其中有三个方法：
 * getObject():通过一个对象交给IOC容器
 * getObjectType():设置所提供对象的类型
 * isSingleton():所提供对象是否是单例
 * 当把FactoryBean的实现类配置为bean时，会将当前类getObject()所返回的对象交给IOC容器管理
 *
 */
public class FactoryBeanTest {

    @Test
    public void testFactoryBean(){
        ApplicationContext ioc=new ClassPathXmlApplicationContext("spring-factory.xml");
        User user = ioc.getBean(User.class);
        System.out.println(user);
    }
}
