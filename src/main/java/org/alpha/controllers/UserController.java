package org.alpha.controllers;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.RequestScoped;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.alpha.entities.User;
import org.alpha.interfaces.UserInterface;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.oracle.svm.core.annotate.Inject;

@Path("/api/user")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON) //I'll get an information then return a JSON object
@Consumes(MediaType.APPLICATION_JSON) //I need to check what it means


public class UserController {
    
    @Inject
    private final UserInterface userInterface;

    public UserController(UserInterface userInterface){
        this.userInterface = userInterface;
    }
    
    @POST //method get or post
    @PermitAll
    @Path("/register")
    @Operation(
        summary = "Add User",
        description = "Add a new user to the System"
    )
    @APIResponses( //returns i wanna give from my API
        value = {@APIResponse(
            responseCode = "200",
            description = "Sucess",
            content = @Content(mediaType = "application/json",
            schema=@Schema(implementation = User.class)) //Is goig to return me what i have in my User Class
        )
        }
    )
    //save user
    public Response register(@Valid User user){
        userInterface.saveUser(user);
        return Response.ok(null).build();
    }

    //update user
    @PUT
    @RolesAllowed("ADMIN")
    @Path("/{id}") //!<--front will show me Id than i follow this class
    @Operation(summary = "Update user",description = "Update user through id")
    @APIResponses(
        value = {@APIResponse(
            responseCode = "200",
            description = "Sucess",
            content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = User.class)
        ),
        @APIResponse(
            responseCode = "404",
            description = "ERROR IN UPDATING USER",
            content = @Content(mediaType = "application/json", 
            schema = @Schema(implementation = User.class)
            )
        )
        }
    )
        
    )
}
