package org.alpha.repositories;

import java.util.List;

import org.alpha.entities.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class UserRepository implements PanacheRepository<User> {

    public List<User> FindByUserName(String name) {
        return find("user_name = :nome", name).list();
        // o user_name gonna receive nome and nome receive nome as a value
        //its useful when i need to use it in a ilike script for example
        
    }
}
