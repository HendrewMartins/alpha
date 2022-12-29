package org.alpha.controllers;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.alpha.dto.UserLogin;
import org.alpha.interfaces.UserInterface;

import com.oracle.svm.core.annotate.Inject;

@Path("/api/login")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON) 
public class LoginController {

    @Inject

    private final UserInterface userInterface;

    public LoginController(UserInterface userInterface) {
        this.userInterface = userInterface;
    }
    
    @POST
    @PermitAll
    @Path("/auth")
    public Response login(@Valid UserLogin userLogin ) throws Exception{
        return userInterface.generateToken(userLogin);
    }
    
    
}
