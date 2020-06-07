/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yash.pickeliver.services;

import com.yash.pickeliver.models.Order;
import com.yash.pickeliver.models.User;
import com.yash.pickeliver.repositories.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author yashpatel
 */

@Service
public class OrderService {
    
    @ Autowired
    private OrderRepository orderRepo;
    
    
    
    public List<Order> getMyOrders(User user){
       return orderRepo.findByUser(user);
    }
    
    
   public Order save(Order order){
       
       return orderRepo.save(order);
   }
    
    
    
}
