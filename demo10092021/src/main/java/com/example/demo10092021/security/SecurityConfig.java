package com.example.demo10092021.security;

import com.example.demo10092021.security.customFilter.MyAuthorityFilter;
import com.example.demo10092021.security.customFilter.MyCheckLoginFilter;
import com.example.demo10092021.security.customFilter.MyLoginHandleFilter;
import com.example.demo10092021.security.customFilter.MyUpdateUserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyCustomAuthenticationProvider provider;


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private AuthenticationManager authenticationManagerBean;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterBefore(new MyLoginHandleFilter("/login-handle", authenticationManagerBean), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new MyCheckLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new MyAuthorityFilter("/admin", authenticationManagerBean), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new MyUpdateUserFilter("/update", authenticationManagerBean), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().permitAll()
//                .and()
//                .formLogin();
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**");
    }
}
