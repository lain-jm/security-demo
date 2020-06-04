package com.example.securitydemo.pojo;

import com.example.securitydemo.domian.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author
 * @version 1.0
 * @date 2020/6/2 0002 11:12
 * @description
 **/
@Data
public class UserBean implements UserDetails {

    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private Boolean enabled;

    private List<Role> roles;

    /**
     * 返回用户角色 设置用户身份权限
     * @return Collection
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = getRoles();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }

    /**
     * 账号是否过期
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否被锁定
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
