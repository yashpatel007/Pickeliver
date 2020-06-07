/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yash.pickeliver.repositories;

import com.yash.pickeliver.models.Authority;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author yashpatel
 */

@Repository
public interface AuthorityRepository extends MongoRepository<Authority, String> {
    List<Authority> findByAuthority(String authority);   
}
