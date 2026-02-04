package org.Grupp3Api.Api.Filter;

import org.Grupp3Api.Api.Services.UserService;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

//Källa:
//https://stackoverflow.com/questions/27137593/authenticating-with-an-api-key-in-jax-rs

@Provider
@Priority(Priorities.AUTHENTICATION)
public class ApiKeyFilter implements ContainerRequestFilter {
    private static final String API_KEY_HEADER = "X-API-KEY";
    
    @Inject
    UserService userService;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        
        //Gets the information stored in x-api-key header and saves it.
        String apiKey = requestContext.getHeaderString(API_KEY_HEADER);

        String path = requestContext.getUriInfo().getPath();

        //Checking if the path starts with /public, if it is it will allow access without
        //forcing a apikey.
        if(path.startsWith("/public")){
            return;
        }
        //Checking if the apikey is null or if it doesnt contains in apikeylist
        // if any statement is true, aborts connection with 401 Unautherized.
        if (apiKey == null || !userService.getApiKeyList().contains(apiKey)) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Invalid or missing API key")
                            .build());
        }

    }
}