package org.alpha.controllers;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.alpha.entities.User;
import org.alpha.exeption.ExceptionHandler;
import org.alpha.exeption.notFoundMessageExeption;
import org.alpha.interfaces.UserInterface;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.oracle.svm.core.annotate.Delete;
import com.oracle.svm.core.annotate.Inject;

@Path("/api/user")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON) // I'll get an information then return a JSON object
@Consumes(MediaType.APPLICATION_JSON) // I need to check what it means

public class UserController {

    @Inject
    private final UserInterface userInterface;

    public UserController(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    @POST // method get or post
    @PermitAll
    @Path("/register")
    @Operation(summary = "Add User", description = "Add a new user to the System")
    // returns i wanna give from my API
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Sucess", 
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = User.class))) })
    public Response register(@Valid User user) {
        userInterface.saveUser(user);
        return Response.ok(null).build();
    }

    @PUT
    @PermitAll
    @Path("/{id}")
    @Operation(summary = "Update an User", description = "Update User via Id")
    @APIResponses(value = {
        @APIResponse(responseCode = "200", description = "Success", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
        @APIResponse(responseCode = "404", description = "User not found", 
        content = @Content(mediaType = "application/json", 
        schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public User updateUsuario(@PathParam("id") Long id, @Valid User usuario) throws notFoundMessageExeption {
        return userInterface.updateUser(id, usuario);
    }

    @GET
    @PermitAll
    @Operation(summary = "List all Users", description = "List all users")
    @APIResponses(value = {
        @APIResponse(responseCode = "200", description = "Sucess", content = @Content(mediaType = "application/json",schema = @Schema(implementation = User.class))) })
    public List<User> getUsers(){
        return userInterface.allUsers();
    }
    
    @GET
    @PermitAll
    @Path("/{id}")
    @Operation(summary = "Search for a user", description = "Search for User by id")
    @APIResponses(value = {
        @APIResponse(responseCode = "200", description = "Success", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
        @APIResponse(responseCode = "404", description = "User not found", 
        content = @Content(mediaType = "application/json", 
        schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public User getUserById(@PathParam("id") Long id){
        try {
            return userInterface.getUserById(id);
        } catch (notFoundMessageExeption e) {
            e.printStackTrace();
        }
        return null;
    }

    @DELETE
    @PermitAll
    @Path("/{id}")
    @Operation(summary = "Delete an User", description = "Delete an User by ID")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @APIResponse(responseCode = "404", description = "Usuário não localizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public boolean updateUsuario(@PathParam("id") Long id) throws notFoundMessageExeption {
        userInterface.deleteUser(id);
        return true;
    }

    @GET
    @PermitAll
    @Path("/search-name/{user_name}")
    @Operation(summary = "Search for User by name", description = "Search User by name")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @APIResponse(responseCode = "404", description = "Usuário não localizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionHandler.ErrorResponseBody.class))) })
    public List<User> getUsuarioByNome(@PathParam("user_name") String name)  throws notFoundMessageExeption {
        return userInterface.findUserByName(name);
    }
}