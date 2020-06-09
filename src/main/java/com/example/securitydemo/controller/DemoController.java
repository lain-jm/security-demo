package com.example.securitydemo.controller;

import com.example.securitydemo.domian.User;
import com.example.securitydemo.pojo.UserBean;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.nio.charset.CharsetDecoder;
import java.util.List;

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
        System.out.println("print principal"+principal);
        System.out.println("print end");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication);

        return result;
    }

    @GetMapping("/de")
    public String de(HttpServletRequest request){
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        Cookie[] cookies = request.getCookies();
        System.out.println(cookies.length);
        Cookie cookie = cookies[0];
        System.out.println(cookie);
        byte[] serialize = genericJackson2JsonRedisSerializer.serialize(cookie);
        String s = null;
        try {
            s = new String(serialize, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(s);
//        Object deserialize = genericJackson2JsonRedisSerializer.deserialize(serialize);
//        System.out.println(deserialize);
//        HttpCookie deserialize1 = genericJackson2JsonRedisSerializer.deserialize(serialize, HttpCookie.class);
//        System.out.println(deserialize1);

        return null;
    }

}
