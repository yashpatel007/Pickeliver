/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yash.pickeliver.repositories;

import com.yash.pickeliver.models.Order;
import com.yash.pickeliver.models.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yashpatel
 */

@Repository
public interface OrderRepository extends MongoRepository<Order, String>{
    
    List<Order> findByUser(User user);
    
    
    Optional<Order> findById(String id);
}
