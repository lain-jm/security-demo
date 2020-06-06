package com.example.securitydemo.controller;

import com.example.securitydemo.domian.User;
import com.example.securitydemo.pojo.UserBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @version 1.0
 * @date 2020/6/1 0001 15:33
 * @description
 **/
@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello SpringSecurity!";
    }

    @GetMapping("/level1/hello")
    public String hello2(){
        return "level1";
    }
    @GetMapping("/level2/hello")
    public String hello3(){
        return "level2";
    }

    @GetMapping("/level3/hello")
    public String hello4(){
        return "level3";
    }

    @GetMapping("/user/hello")
    public String hello5(){
        return "user";
    }

    @GetMapping("/current-user")
    public String getUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String result;
        String anonymousUser = "anonymousUser";
        if(anonymousUser.equals(principal)) {
            result = anonymousUser;
        } else {
            UserBean user = (UserBean) principal;
            result = user.toString();
        }
        System.out.println(result);
        return result;
    }

}
