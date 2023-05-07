package com.atguigu.controller;

import com.atguigu.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Drew
 * @create 2023-03
 * 1,@RequestBody:将请求体中的内容和控制器方法的形参进行绑定
 * 2,使用@RequestBody注解将json格式的请求参数转换为Java对象
 * a>导入jackson的依赖
 * b>在SpringMVC的配置文件中设置<mvc:annotation-driven/>
 * c>在处理请求的控制器方法的形参位置，直接设置json格式的请求参数要转换的Java类型的形参，使用@RequestBody注解
 * 3,@ResponseBody:将所标识的控制器方法的返回值作为响应报文的响应到浏览器
 * 4,使用@ResponseBody注解响应浏览器json格式的数据
 * a>导入jackson的依赖
 * b>在SpringMVC的配置文件中设置<mvc:annotation-driven/>
 * c>将需要转换为json字符串的Java对象直接作为控制器方法的返回值，使用注解@ResponseBody标识控制器方法
 * 就可以将Java对象直接转换为json字符串，并响应到浏览器
 *
 * 常用的Java对象转换为json的结果：
 * 实体类-->json对象
 * map-->json对象
 * list-->json对象
 */
@Controller
//@RestController @Controller+@ResponseBody
public class TestAjaxController {


    @RequestMapping("/test/ajax")
    public void testAjax(Integer id, HttpServletResponse response,@RequestBody String requestBody) throws IOException {
        System.out.println("requestBody:"+requestBody);
        System.out.println("id:"+id);
        response.getWriter().write("hello,axios");
    }

    @RequestMapping("/test/RequestBody/json")
    public void testRequestBody(HttpServletResponse response,@RequestBody Map<String,Object> map) throws IOException{
        System.out.println(map);
        response.getWriter().write("hello,RequestBody");
    }

    public void testRequestBody(HttpServletResponse response,@RequestBody User user) throws IOException{
        System.out.println(user);
        response.getWriter().write("hello,RequestBody");
    }

    @RequestMapping("/test/ResponseBody")
    @ResponseBody
    public String testResponseBody(){
        return "success";
    }

    @RequestMapping("/test/ResponseBody/json")
    @ResponseBody
    public User testResponseBodyJson(){
        User user=new User(1001,"admin","123456",20,"男");
        return user;
    }
//    public User testResponseBodyJson(){
//        User user=new User(1001,"admin","123456",20,"男");
//        return user;
//    }
}
