package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Drew
 * @create 2023-03
 * 向域对象共享数据：
 * 1，通过ModelAndersonView向请求域共享数据
 * 使用ModelAndView时，可以使用Model功能向请求域共享数据
 * 使用View功能设置逻辑视图，但是控制器方法一定要将ModelAndView作为方法的返回值
 * 2，使用Model功能向请求域共享数据
 * 3，使用ModelMap向请求域共享数据
 * 4,使用Map功能向请求域共享数据
 * 5,Model和ModelMap和map的关系
 * 其实在底层中，这些类型的形参最终都是通过BindingAwareModelMap创建
 */
@Controller
public class TestScopeController {

    @RequestMapping("/test/mav")
    public ModelAndView testMAV(){
        /**
         * ModelAndView包含Model和View的功能
         * Model:向请求域中共享数据
         * View:设置逻辑视图实现页面跳转
         */
        ModelAndView mav = new ModelAndView();
        //想请求区域中共享数据
        mav.addObject("testRequestScope","hello,ModelAndView");
        mav.setViewName("success");
        return mav;
    }

    @RequestMapping("/test/model")
    public String testModel(Model model){
        model.addAttribute("testRequestScope","hello,Model");
        return "success";
    }

    @RequestMapping("/test/modelMap")
    public String testModelMap(ModelMap modelMap){
        modelMap.addAttribute("testRequestScope","hello,ModelMap");
        return "success";
    }

    @RequestMapping("/test/map")
    public String testMap(Map<String,Object> map){
        map.put("testRequestScope","hello,Map");
        return "success";
    }

    @RequestMapping("/test/session")
    public String testSession(HttpSession session){
        session.setAttribute("testSessionScope","hello,Session");
        return "success";
    }

    @RequestMapping("/test/application")
    public String testApplication(HttpSession session){
        ServletContext servletContext = session.getServletContext();
        servletContext.setAttribute("testApplicationScope","hello,application");
        return "success";
    }
}
