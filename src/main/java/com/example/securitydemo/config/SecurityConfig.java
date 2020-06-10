package com.example.securitydemo.config;

import com.example.securitydemo.module.security.MyAuthenticationFailureHandler;
import com.example.securitydemo.module.security.MyAuthenticationSuccessHandler;
import com.example.securitydemo.servier.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

import javax.annotation.Resource;

/**
 * @author
 * @version 1.0
 * @date 2020/6/1 0001 16:07
 * @description
 **/
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Bean
    UserDetailsService getServiceDetail() {
        return new UserService();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //SpringSecurity会默认在身份前加上前缀，这里设置去除
        web.expressionHandler(new DefaultWebSecurityExpressionHandler() {
            @Override
            protected SecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, FilterInvocation fi) {
                WebSecurityExpressionRoot root = (WebSecurityExpressionRoot) createSecurityExpressionRoot(authentication, fi);
                //remove the prefix ROLE_
                root.setDefaultRolePrefix("");
                return root;
            }
        });
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        //SpringSecurity5.0.6必须使用加密算法，此处注入加密方法
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入自定义认证类
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getServiceDetail());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*匹配所有路径的*/
        http
                .formLogin()
                .successHandler(myAuthenticationSuccessHandler)
//                .failureHandler(myAuthenticationFailureHandler)
                .and()
                /*禁用CSRF保护*/
                .csrf()
                .disable()
                .authorizeRequests()  // 授权配置
                .anyRequest()         // 所有请求
                .authenticated();     // 都需要认证

    }

    //    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http.httpBasic()                // HttpBasic
////        http.formLogin()              // 表单方式
////                .and()
////                .authorizeRequests()  // 授权配置
////                .anyRequest()         // 所有请求
////                .authenticated();     // 都需要认证
//
//        /*匹配所有路径的*/
//        http.authorizeRequests()
//                /*无条件允许访问*/
//                .antMatchers("/").permitAll()
//                /*level1路径下需要VIP1身份才能访问*/
//                .antMatchers("/level1/**").hasRole("VIP1")
//                /*level1路径下需要VIP2身份才能访问*/
//                .antMatchers("/level2/**").hasRole("VIP2")
//                /*level1路径下需要VIP3身份才能访问*/
//                .antMatchers("/level3/**").hasRole("VIP3")
//                .and()
//                /*
//                1.formLogin系统会自动配置/login页面用于登录
//                2.假如登录失败会重定向到login/error/
//                 */
//                .formLogin()
//                /*
//                定制自己的登录界面
//                默认username字段提交用户名，可以通过usernameParameter自定义
//                默认password字段提交密码，可以用过passwordParameter自定义
//                定制了登录页面后
//                登录页面地址的POST请求就是登录请求，可以用过loginProcessingUrl自定义
//                */
////                .loginPage("/userlogin")
//                .and()
//                /*开启注销功能,访问/logout并清空session*/
//                .logout()
//                /*注销成功去首页*/
//                .logoutSuccessUrl("/")
//                .and()
//                /*开启记住我功能，登录会添加Cookie,点击注销会删除Cookie*/
//                .rememberMe()
//                /*设置记住我参数*/
//                .rememberMeParameter("remember");
//
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                /*从内存中读取认证*/
//                .inMemoryAuthentication()
//                /*Spring Security 5.0开始必须要设置加密方式*/
//                .passwordEncoder(new BCryptPasswordEncoder())
//                .withUser("user1").password(new BCryptPasswordEncoder().encode("123")).roles("VIP1")
//                .and()
//                .withUser("user2").password(new BCryptPasswordEncoder().encode("123")).roles("VIP2")
//                .and()
//                .withUser("user3").password(new BCryptPasswordEncoder().encode("123")).roles("VIP3");
//
//    }



}
