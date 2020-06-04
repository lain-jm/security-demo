package com.example.securitydemo.servier;

import com.example.securitydemo.domian.User;
import com.example.securitydemo.pojo.UserBean;
import com.example.securitydemo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

/**
 * @author
 * @version 1.0
 * @date 2020/6/3 0003 16:52
 * @description
 **/
public class UserService implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("未找到用户名");
        }
        UserBean userBean = new UserBean();
        BeanUtils.copyProperties(user,userBean);
        userBean.setEnabled(true);

        return userBean;
    }

}
