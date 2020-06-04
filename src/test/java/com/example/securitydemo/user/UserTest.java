package com.example.securitydemo.user;

import com.example.securitydemo.domian.User;
import com.example.securitydemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

/**
 * @author
 * @version 1.0
 * @date 2020/6/4 0004 14:55
 * @description
 **/
@SpringBootTest
public class UserTest {

    @Resource
    private UserRepository userRepository;

    @Resource
    private BCryptPasswordEncoder encoder;

    @Test
    public void save() {
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        String encode = encoder.encode("123");
        System.out.println(encode);
        user.setPassword(encode);

        userRepository.save(user);

    }

}
