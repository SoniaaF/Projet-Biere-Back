package fr.fs.endpoints;

import fr.fs.entities.Marque;
import fr.fs.repositories.MarqueRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/marques")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarqueEndPoints {

    @Inject
    MarqueRepository marqueRepository;

    @GET
    @Operation(summary = "Brand", description = "Get all brands")
    @APIResponse(responseCode = "200", description = "Ok, brands found !")
    @APIResponse(responseCode = "204", description = "No brand !")

    public Response getAll(){
        List <Marque> marques = marqueRepository.listAll();
        if (marques.isEmpty())
            return Response.noContent().build();
        else
            return Response.ok(marques).build();
    }

    @Path("{id}")
    public Response getById (@PathParam("id") Integer id){
        Marque marque = marqueRepository.findById(id);
        if (marque == null)
            return Response.noContent().build();
        else
            return Response.ok(marque).build();
    }

    @Transactional
    @POST
    @APIResponse(responseCode = "201", description = "La marque à été créé")
    public Response create(String marqueACreer, @Context UriInfo uriInfo) {
        Marque marque = new Marque();
        marque.setNom(marqueACreer);
        marqueRepository.persist(marque);
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        uriBuilder.path(marque.getId().toString());
        return Response.created(uriBuilder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public Response delete (@PathParam("id") Integer id) {
        if (marqueRepository.deleteById(id))
            return Response.noContent().build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

}
