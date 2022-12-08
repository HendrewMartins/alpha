package org.alpha.repositories;

import java.util.List;

import org.alpha.entities.User;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class UserRepository implements PanacheRepository<User> {

    public List<User> FindByUserName(String name) {
        return find("user_name = :user_name", name).list();
        // =: is the identity of my parameter
        // what is goig to return me? The name

    }
}
