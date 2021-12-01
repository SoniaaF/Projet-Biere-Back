package fr.fs.endpoints;

import fr.fs.entities.Fabricant;
import fr.fs.repositories.FabricantRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/fabricants")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FabricantEndPoints {

    @Inject
    FabricantRepository fabricantRepository;

    @GET
    @Operation(summary = "Makers", description = "Get all makers")
    @APIResponse(responseCode = "200", description = "Ok, makers found !")
    @APIResponse(responseCode = "204", description = "No maker !")

    public Response getAll(){
        List <Fabricant> fabricants = fabricantRepository.listAll();
        if (fabricants.isEmpty())
            return Response.noContent().build();
        else
            return Response.ok(fabricants).build();
    }

    @Path("{id}")
    public Response getById (@PathParam("id") Integer id) {
        Fabricant fabricant = fabricantRepository.findById(id);
        if (fabricant == null)
            return Response.noContent().build();
        else
            return Response.ok(fabricant).build();
    }

    @Transactional
    @POST
    @APIResponse(responseCode = "201", description = "Le fabricant à été créé")
    public Response create(String fabricantACreer, @Context UriInfo uriInfo) {
        Fabricant fabricant = new Fabricant();
        fabricant.setNom(fabricantACreer);
        fabricantRepository.persist(fabricant);
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        uriBuilder.path(fabricant.getId().toString());
        return Response.created(uriBuilder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public Response delete (@PathParam("id") Integer id) {
        if (fabricantRepository.deleteById(id))
            return Response.noContent().build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

}
