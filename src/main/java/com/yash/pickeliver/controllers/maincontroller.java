/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yash.pickeliver.controllers;

import com.yash.pickeliver.models.Order;
import com.yash.pickeliver.models.User;
import com.yash.pickeliver.services.OrderService;
import com.yash.pickeliver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author yashpatel
 */

@Controller
public class maincontroller {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OrderService orderService;
    
    
    public int farekm=2;
    //private Order pkorder;
    
    @GetMapping("/")
    public String showHomePage(@AuthenticationPrincipal User user, ModelMap model){
        if(user != null ) model.put("user", user);
        model.put("pkorder", new Order());
        return "index";
    }
    
    @PostMapping("/pkorder")
    public String saveOrderResponce(@ModelAttribute Order order, @AuthenticationPrincipal User user){
//        if(user!=null) order.setUser(user);
//        System.out.println("settting pk order... "+order);
//        pkorder=order;
        return "redirect:/farecalculator";
    }
    
    
    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(){
        return "adminpage";
    }
    
    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }
    
    @GetMapping("/myaccount")
    public String showMyAccountPage(@AuthenticationPrincipal User user, ModelMap model){
        if(user != null) model.put("user", user);
        return "myaccount";
    }
    
    
    @PostMapping("/updatemyinfo")
    public String updateInfo(@ModelAttribute User updatedUser, @AuthenticationPrincipal User user){
      System.out.println("Updated info..."+updatedUser);
      userService.updateUserDetails(user, updatedUser);
      return "redirect:/myaccount";
    }
    
    @GetMapping("/register")
    public String showRegisterPage(ModelMap model){
        model.put("user", new User());
        return "register";
    }
    
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user){
        System.out.println(user);
        userService.saveUser(user);
        return "redirect:/login";
    }
    
    @GetMapping("/choosesubscription")
    public String selectSubscription(@AuthenticationPrincipal User user, ModelMap model){
        if(user != null) model.put("user", user);
        return "choosesubscription";
    }
    
    
    @GetMapping("/farecalculator")
    public String showFareCalculator(@AuthenticationPrincipal User user, ModelMap model){
        if(user != null) model.put("user", user);
        model.put("order", new Order());
        model.put("farekm", farekm);
        
        // if user dont have subscription
        // redirect to subscription page
        if(userService.findByUserId(user.getId()).getSubscription()==null){
            return "redirect:/choosesubscription";
        }
        return "farecalculator";
    }
    
    
    @GetMapping("/myorders")
    public String showMyOrders(@AuthenticationPrincipal User user, ModelMap model){
        if(user != null) model.put("user", user);
        model.put("myorders", orderService.getMyOrders(user));
        return "myorders";
    }
    
    @PostMapping("/saveord")
    public String saveOrder(@AuthenticationPrincipal User user,@ModelAttribute Order order){
        // set user for order
        order.setUser(user);
        System.out.println("saving Order... :"+order);
        orderService.save(order);
        return "redirect:/myorders";
    }
    
    @PostMapping("/setsubscription")
    public String setSubscription(@AuthenticationPrincipal User user ,@RequestParam("subtype") String subtype){
        System.out.println("choosen subscription..."+subtype+user);
        userService.setUserSubscription(user, subtype);
        return "redirect:/farecalculator";
    }
    
}
