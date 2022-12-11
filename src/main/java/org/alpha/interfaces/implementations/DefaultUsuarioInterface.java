package org.alpha.interfaces.implementations;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.alpha.entities.User;
import org.alpha.exeption.notFoundMessageExeption;
import org.alpha.interfaces.UserInterface;
import org.alpha.repositories.UserRepository;
import org.alpha.util.PBKDF2Encoder;

import com.oracle.svm.core.annotate.Inject;

@ApplicationScoped //!Scoped means that the application will connect to a service o
public class DefaultUsuarioInterface implements UserInterface{ //It means that all the UserIntercace methods need to
    
    //! Why did i created this class? This is need when I avoid that other user reaches my main methods it 
        //!gives strengh to my software
    
    @Inject //*Here i inject the User Respository - I injected User properties and it injectos to my class*/
    private final UserRepository userRepository;

    @Inject
    private final PBKDF2Encoder encoder;

    public DefaultUsuarioInterface(UserRepository userRepository, PBKDF2Encoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Transactional //Transactional is a insert in my DB
    @Override
    public User saveUser(User user) {
        if(!userExists(user.getUser_email())){
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.persistAndFlush(user);
            return user; //Hibernate will return me the user automatically
        }
        return null;
    }

    @Transactional
    @Override
    public User updateUser(Long id,User user) throws notFoundMessageExeption {
        return null;
    }

    @Transactional
    @Override
    public void deleteUser(Long id) throws notFoundMessageExeption {
        
    }

    @Transactional
    @Override
    public List<User> allUsers() {
        return null;
    }

    @Override
    public User getUserById(Long id) throws notFoundMessageExeption {
        return null;
    }

    @Override
    public List<User> getUserByName(String name) throws notFoundMessageExeption {
        return null;
    }

    public boolean userExists(String email){
        return(userRepository.count("email",email)>0);
        //Its goind to count on my db how many emails i have
        //Is it going to count everything? No just the parameter i gave which is email
        //If its bigger than 0 it means that there is one email with that id
    
    }

}
