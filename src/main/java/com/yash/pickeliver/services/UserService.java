/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yash.pickeliver.services;

import com.yash.pickeliver.models.User;
import com.yash.pickeliver.repositories.AuthorityRepository;
import com.yash.pickeliver.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author yashpatel
 */

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    @Autowired
    private AuthorityRepository authorityRepo;
   
    
    public List<User> findall(){
        return userRepo.findAll();
    }
    
    public User findByUserId(String userid){
        Optional<User> user = userRepo.findById(userid);
        if(user.isPresent()) return user.get();
        
        throw new UsernameNotFoundException("user not found...");
        
    }
    
    
    public User setUserSubscription(User user, String subtype){
        
        user.setSubscription(subtype);
        return userRepo.save(user);
        
        
    }
    
    
    public User updateUserDetails(User user, User updateduser){
       String encodedPass = passwordEncoder.encode(updateduser.getPassword());
       user.setPassword(encodedPass);
       user.setName(updateduser.getName());
       user.setSubscription(updateduser.getSubscription());
       
       return userRepo.save(user);
    
    }
    
    
    public User saveUser(User user){
       
       
       String encodedPass = passwordEncoder.encode(user.getPassword());
       user.setPassword(encodedPass);  
       
        //System.out.println(authorityRepo.findByAuthority("ROLE_USER").get(0));
       user.getAuthorities().add(authorityRepo.findByAuthority("ROLE_USER").get(0));
       
        System.out.println("saving..."+ user);
       return userRepo.save(user);
       
    }
}
