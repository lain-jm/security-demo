package com.example.securitydemo.module.security;

import com.example.securitydemo.domian.User;
import com.example.securitydemo.pojo.UserBean;
import com.example.securitydemo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author
 * @version 1.0
 * @date 2020/6/9 0009 17:48
 * @description
 **/
@Component
public class CustomUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userRepository.findByUsername(username);
        UserBean userBean = new UserBean();
        BeanUtils.copyProperties(user, userBean);

        boolean isPassword = BCrypt.checkpw(password, user.getPassword());
        if (username.equals(user.getUsername()) && isPassword) {
            return new UsernamePasswordAuthenticationToken(username, password, userBean.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid username / password");
        }

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
