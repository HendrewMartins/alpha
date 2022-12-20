package org.alpha.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.alpha.entities.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public List<User> FindByUserName(String user_name) {
        return find("user_name", user_name).list();
        // o user_name gonna receive nome and nome receive nome as a value
        //its useful when i need to use it in a ilike script for example
        
    }
}
