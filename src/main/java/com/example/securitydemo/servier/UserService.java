package com.example.securitydemo.servier;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author
 * @version 1.0
 * @date 2020/6/3 0003 16:52
 * @description
 **/
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

//        throw new UsernameNotFoundException("未找到用户名");

        return null;
    }

}
