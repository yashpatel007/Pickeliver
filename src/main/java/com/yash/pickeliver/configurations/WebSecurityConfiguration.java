/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yash.pickeliver.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author yashpatel
 */

@Configuration
public class WebSecurityConfiguration  extends  WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
     public PasswordEncoder getPasswordEncoder(){
        return  new BCryptPasswordEncoder();
     }
     
     @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         
      auth.userDetailsService(userDetailsService)
                .passwordEncoder(getPasswordEncoder());
//       auth.inMemoryAuthentication()
//                .passwordEncoder(getPasswordEncoder())
//                .withUser("patelyash1311@gmail.com")
//                .password("asdf")
//                .roles("USER");
      
         
      }
     
     @Override
      protected void configure(HttpSecurity http) throws Exception {
                    http  
                    .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/images/**").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/register").permitAll()
                    .antMatchers("/**").hasRole("ADMIN")
                    .anyRequest().hasRole("USER").and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/farecalculator")
                    .permitAll()
                    .and()
                    .logout().logoutUrl("/logout")
                    .permitAll(); 
                    
    }
     
    
}
