package fr.fs.endpoints;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@Tag (name = "Hello Jax-RS Resource", description = "Just say Hello with Jax-RS")
public class ExampleEndPoints {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Say Hello" , description = "Simply say Hello !")
    @APIResponse(responseCode = "200" , description = "Ok, that's fine !")

    public String hello() {
        return "Hello Quarkus";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Say Hello you" , description = "Simply say Hello to the user !")
    @Path("{name}")
    public String Hello (@PathParam("name") String nom){
        return "Hello " +nom;
    }

}