package org.Grupp3Api.Api.Resources;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.Grupp3Api.Api.Entity.App;
import org.Grupp3Api.Api.Entity.AppDTO;
import org.Grupp3Api.Api.Services.AppService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/api/app")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppResource {


    @Inject 
    AppService appService;

    @GET
    @Operation(summary = "Show all Apps", description = "Gets and show all the apps that exist in the database")
    @APIResponse(
        responseCode = "200",
        description = "All apps"
    )
    @APIResponse(
        responseCode = "204",
        description = "There are no apps"
    )
    public Response getApps() {
        List<App> apps = appService.findAll();
        if(apps.isEmpty()) {
            return Response.noContent().build();
        }
        return Response.ok(apps).build();
    }

    @GET
    @Path("/{id}")
    public Response getAppsById(@PathParam("id") @Min(1) Long id){
        App app = appService.find(id);
        if (app == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("App not found")
                    .build();
    }
        return Response.ok(app).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/count")
    public Response countApps(){
        Long count = appService.countAll();
        return Response.ok(count).build();
    }

    @POST
    public Response createApp(@Valid App app) throws URISyntaxException{
        app = appService.create(app);
        URI createdUri = new URI(app.getId().toString());
        return Response.created(createdUri).entity(app).build();

    }

    @DELETE
    @Path("/{id}")
    public Response deleteAppById(@PathParam("id") @Min(1) Long id) {
        appService.delete(id);
        return Response.noContent().build();
    }


    @PATCH
    @Path("/{id}/version")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAppVersion(@PathParam("id") Long id, @Valid AppDTO appDTO) {
        App existingApp = appService.find(id);

        if (existingApp == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("App with such id not found")
                    .build();
        }
        if (appDTO == null || appDTO.getAppVersion() == null || appDTO.getAppVersion().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("appVersion can't be empty")
                    .build();
    }
        existingApp.setAppVersion(appDTO.getAppVersion());
        appService.update(existingApp);
        return Response.ok(existingApp).build();
}
    @PATCH
    @Path("/{id}/description")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAppDescription(@PathParam("id") Long id, AppDTO appDTO) {
        App existingApp = appService.find(id);
        existingApp.setAppDescription(appDTO.getAppDescription());
        appService.update(existingApp);
        return Response.ok(existingApp).build();
}
    @PATCH
    @Path("/{id}/image")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAppImage(@PathParam("id") Long id, AppDTO appDTO) {
        App existingApp = appService.find(id);
        existingApp.setAppImage(appDTO.getAppImage());
        appService.update(existingApp);
        return Response.ok(existingApp).build();
}

    
}

