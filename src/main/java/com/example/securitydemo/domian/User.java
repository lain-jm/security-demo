package com.example.securitydemo.domian;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2020/6/3 0003 16:54
 * @description
 **/
@Entity
@Table(name = "t_user")
@Data
public class User {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

//    @ManyToMany(fetch = FetchType.LAZY)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "t_user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    private List<Role> roles;

}
