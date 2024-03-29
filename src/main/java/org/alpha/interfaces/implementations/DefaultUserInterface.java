package org.alpha.interfaces.implementations;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.alpha.dto.Token;
import org.alpha.dto.UserLogin;
import org.alpha.entities.User;
import org.alpha.exeption.notFoundMessageExeption;
import org.alpha.interfaces.UserInterface;
import org.alpha.repositories.UserRepository;
import org.alpha.util.PBKDF2Encoder;
import org.alpha.util.TokenUtil;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.oracle.svm.core.annotate.Inject;

import io.quarkus.panache.common.Page;

@ApplicationScoped // !Scoped means that the application will connect to a service
public class DefaultUserInterface implements UserInterface { // It means that all the UserIntercace methods need to

    // ! Why did i created this class? This is need when I avoid that other user
    // reaches my main methods it
    // !gives strengh to my software

    @ConfigProperty(name = "org.alpha.jwt.duration")
    public Long duration;
    @ConfigProperty(name = "mp.jwt.verify.issuer")
    public String issuer;

    @Inject // *Here i inject the User Respository - I injected User properties and it
            // injectos to my class*/
    private final UserRepository userRepository;

    @Inject
    private final PBKDF2Encoder encoder;

    public DefaultUserInterface(UserRepository userRepository, PBKDF2Encoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Transactional // Transactional is a insert in my DB
    @Override
    public User saveUser(User user) {
        if (!userExists(user.getUser_email())) {
            user.setUser_password(encoder.encode(user.getUser_password()));
            userRepository.persistAndFlush(user);
            return user; // Hibernate will return me the user automatically
        }
        return null;
    }

    @Transactional
    @Override
    public User updateUser(Long id, User user) throws notFoundMessageExeption {
        User update_user = getUserById(id);
        update_user.setUser_email(user.getUser_email());
        update_user.setUser_name(user.getUser_name());
        update_user.setRoles(user.getRoles());
        update_user.setUser_password(encoder.encode(user.getUser_password()));
        return update_user;
    }

    @Transactional
    @Override
    public void deleteUser(Long id) throws notFoundMessageExeption {
        userRepository.delete(getUserById(id));
    }

    @Transactional
    @Override
    public List<User> allUsers() {
        return userRepository.listAll();
    }

    @Override
    public User getUserById(Long id) throws notFoundMessageExeption {
        return userRepository.findByIdOptional(id)
                .orElseThrow(() -> new notFoundMessageExeption("O ID não foi encontrado"));
        // !here i Settled that my id is optional, it means that if the id is not found
        // i will return a message
    }

    @Override
    public List<User> findUserByName(String name) throws notFoundMessageExeption {
        return userRepository.FindByUserName(name);
    }

    public boolean userExists(String email) {
        return (userRepository.count("user_email", email) > 0);
        // Its goind to count on my db how many emails i have
        // Is it going to count everything? No just the parameter i gave it which is
        // email
        // If its bigger than 0 it means that the emails has been used already
    }

    @Override
    public List<User> AllUserPagination(int pag, int quant) {
        return userRepository.findAll().page(Page.of(pag, quant)).list();
    }

    @Override
    public Long countUser() {
        return userRepository.count();
    }

    @Override
    public Response generateToken(UserLogin userLogin) throws Exception {
        User user = userRepository.find("user_email", userLogin.getUser_email()).firstResult();
        try {
            if (user != null && verifyCryptPassword(user, userLogin)) {
                try {
                    return Response.ok(new Token(TokenUtil.generateToken(user.getUser_email(), user.getRoles(), duration, issuer))).build();
                } catch (Exception e) {
                    return Response.status(Status.UNAUTHORIZED).build();

                }
            } else {
                return Response.status(Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean verifyCryptPassword(User user, UserLogin userLogin) {
        return user.getUser_password().equals(encoder.encode(userLogin.getUser_password()));
        // encoder a classe from the PBKDF2Encoder file
    }
}
