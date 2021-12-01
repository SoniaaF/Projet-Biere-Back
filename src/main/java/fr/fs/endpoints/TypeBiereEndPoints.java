package fr.fs.endpoints;

import fr.fs.entities.TypeBiere;
import fr.fs.repositories.TypeBiereRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/types")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TypeBiereEndPoints {

    @Inject
    TypeBiereRepository typeBiereRepository;

    @GET
    @Operation(summary = "Types", description = "Get all types")
    @APIResponse(responseCode = "200", description = "Ok, types found !")
    @APIResponse(responseCode = "204", description = "No type !")

    public Response getAll(){
        List <TypeBiere> typeBieres = typeBiereRepository.listAll();
        if (typeBieres.isEmpty())
            return Response.noContent().build();
        else
            return Response.ok(typeBieres).build();
    }

    @Path("{id}")
    public Response getById (@PathParam("id") Integer id){
        TypeBiere typeBiere = typeBiereRepository.findById(id);
        if (typeBiere == null)
            return Response.noContent().build();
        else
            return Response.ok(typeBiere).build();
    }

    @Transactional
    @POST
    @APIResponse(responseCode = "201", description = "Le type à été créé")
    public Response create(String typeACreer, @Context UriInfo uriInfo) {
        TypeBiere typeBiere = new TypeBiere();
        typeBiere.setNom(typeACreer);
        typeBiereRepository.persist(typeBiere);
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        uriBuilder.path(typeBiere.getId().toString());
        return Response.created(uriBuilder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public Response delete (@PathParam("id") Integer id) {
        if (typeBiereRepository.deleteById(id))
            return Response.noContent().build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

}
