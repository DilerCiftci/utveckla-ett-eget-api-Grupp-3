package org.Grupp3Api.Api.Resources;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.NewCookie.SameSite;

import java.util.UUID;

import org.Grupp3Api.Api.Entity.ApiKeyDTO;
import org.Grupp3Api.Api.Entity.User;
import org.Grupp3Api.Api.Services.UserService;
import org.eclipse.microprofile.openapi.annotations.Operation;


@Path("/public/user")
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @POST
    @Path("/login")
    @Operation(summary = "Login user! :D", description = "Login in user and returns user api key")
    public Response login (User userDTO) {
        User findUser = userService.getUserByUsername(userDTO.getUsername());

        if (findUser != null && findUser.getPassword().contentEquals(userDTO.getPassword())) {

            NewCookie newCookie = new NewCookie.Builder("TOKEN")
                .path("*")
                .value(findUser.getId())
                .httpOnly(true)
                .secure(true)
                .maxAge(1000)
                .sameSite(SameSite.LAX)
                .build();

            ApiKeyDTO apiKeyDTO = new ApiKeyDTO();
            apiKeyDTO.setAPI_KEY(findUser.getApiKey());
            return Response.ok(apiKeyDTO, MediaType.APPLICATION_JSON).cookie(newCookie).build();
        }

        return Response.status(403, "Failed to either find user or user password did not match").build();
    }

    @POST
    @Path("/register")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Register user! :D", description = "Register a new user")
    public Response register (@Valid User user) {
        User findUser = userService.getUserByUsername(user.getUsername());

        if (findUser != null) {
            return Response.ok("User already exits").build();
        }

        userService.registerUser(user);
        return Response.ok("Account has been register").build();
    }

    @GET
    @Path("/generate")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Generate API-KEY! :D", description = "Generate a new api key")
    public Response getKey (@CookieParam("TOKEN") String token) {
        System.out.println(token);
        User user = userService.getUserById(token);

        if (user != null) {
            UUID apiKey = userService.generateNewApiKey(user).getApiKey();
            ApiKeyDTO apiKeyDTO = new ApiKeyDTO();
            apiKeyDTO.setAPI_KEY(apiKey);
            return Response.ok(apiKeyDTO).build();
        }

        return Response.status(403, "Failed to either find user or user password did not match").build();
    }
    
}
