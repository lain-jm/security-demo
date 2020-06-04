package com.example.securitydemo.repository;

import com.example.securitydemo.domian.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author
 * @version 1.0
 * @date 2020/6/4 0004 9:33
 * @description
 **/
public interface UserRepository extends JpaRepository<User,Long> {

    public User findByUsername (String username);
}
