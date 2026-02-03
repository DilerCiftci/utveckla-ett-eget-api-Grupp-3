package org.Grupp3Api.Api.Filter;

import org.Grupp3Api.Api.Services.UserService;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class ApiKeyFilter implements ContainerRequestFilter {
    private static final String API_KEY_HEADER = "X-API-KEY";
    
    @Inject
    UserService userService;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        

        String apiKey = requestContext.getHeaderString(API_KEY_HEADER);

        String path = requestContext.getUriInfo().getPath();

        if(path.startsWith("/public")){
            return;
        }

        if (apiKey == null || !userService.getApiKeyList().contains(apiKey)) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Invalid or missing API key")
                            .build());
        }

    }
}