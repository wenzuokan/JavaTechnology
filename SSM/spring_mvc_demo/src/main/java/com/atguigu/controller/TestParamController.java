package com.atguigu.controller;

import com.atguigu.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Drew
 * @create 2023-03
 *
 * 获取请求参数的方式：
 * 1,通过servlet获取
 * 只需要在控制器方法的形参位置设置HttpServletRequest类型的形参，
 * 就可以在控制器方法中使用request中获取参数
 * 2,通过控制器方法的形参获取
 * 只需要在控制方法的形参位置，设置一个形参,形参的名字和请求参数的名字一夜即可
 * 3，@RequestParam注解的三个属性：value，required，defaultValue
 * value：设置和形参绑定的请求参数的名字
 * required：设置是否必须传输value所对应的请求参数
 * 默认值true，表示value所对应的请求参数必须传输
 * 若设置false，则表示value所对应的请求参数不是必须传输，若为传输，则形参为null
 * defaultValue：设置当没有传输value所对应的请求参数时，为形参设置的默认值，此时和required属性值一样
 * 4，@RequestHeader：将请求头信息和控制器方法的形参绑定
 * 5，@CookieValue：将cookie数据和控制器方法形参绑定
 * 6，通过控制器方法的实体类类型的形参获取请求参数
 * 需要在控制器方法的形参位置设置实体类类型的形参，要保证实体类中的属性的属性名和请求参数的名字一致
 * 可以通过实体类类型的形参获取请求参数
 * 7，解决获取请求此参数的乱码问题
 * 在web.xml中配置Spring的编码过滤器CharacterEncodingFilter
 */
public class TestParamController {


    @RequestMapping("/param/servletAPI")
    public String getParamByServletAPI(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username:"+username+",password:"+password);
        return "success";
    }

    @RequestMapping("/param")
    public String getParam(
            @RequestParam(value = "userName",required = false,defaultValue = "hello") String username,
                           String password
    ){
        System.out.println("username:"+username+",password:"+password);
        return "success";
    }

    @RequestMapping("/param/pojo")
    public String getParamByPojo(User user){
        System.out.println(user);
        return "success";
    }
}
