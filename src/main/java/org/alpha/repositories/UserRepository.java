package org.alpha.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.alpha.entities.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public List<User> FindByUserName(String user_name) { //the name part will get this parameter
        return find("lower(user_name) like lower (:name)",Parameters.with("name", "%"+user_name+"%")).list();
        //with(name) is my parameter it will be replaced by user_name with is my parameter
        //"%"+user_name+"%" it means that any part of the name will be found
        // o user_name gonna receive nome and nome receive nome as a value
        //its useful when i need to use it in a ilike script for example
        
    }
}
