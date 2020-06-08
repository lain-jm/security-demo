package com.example.securitydemo.user;

import com.example.securitydemo.domian.User;
import com.example.securitydemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    @Test
    public void encoder(){
        String encode = encoder.encode("122323");
        System.out.println(encode);
        String encode1 = encoder.encode("122323");
        System.out.println(encode1);
        boolean matches = encoder.matches(encode1, encode);
        System.out.println(matches);

    }

    @Test
    public void connectionleak(){
        Object lock = new Object();
        long min = 60000L;
        long tensec = 10000;
        for (int i=0;i<12;i++){
            System.out.println("start "+(i+1)+":"+LocalDateTime.now());
            try {
                userRepository.findByUsername("admin");
                synchronized (lock) {
                    lock.wait((long)(5.5*min)+(i+1)*tensec);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("end "+(i+1)+":"+LocalDateTime.now());
        }




    }

}
