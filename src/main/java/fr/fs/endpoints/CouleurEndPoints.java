package fr.fs.endpoints;

import fr.fs.entities.Couleur;
import fr.fs.repositories.CouleurRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/couleurs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CouleurEndPoints {

    @Inject
    CouleurRepository couleurRepository;

@GET
@Operation(summary = "Colors", description = "Get all colors")
@APIResponse(responseCode = "200", description = "Ok, colors found !")
@APIResponse(responseCode = "204", description = "No color !")

    public Response getAll(){
    List <Couleur> couleurs = couleurRepository.listAll();
    if (couleurs.isEmpty())
        return Response.noContent().build();
    else
        return Response.ok(couleurs).build();
}

    @Path("{id}")
    public Response getById (@PathParam("id") Integer id){
    Couleur couleur = couleurRepository.findById(id);
    if (couleur == null)
        return Response.noContent().build();
    else
        return Response.ok(couleur).build();
    }

    @Transactional
    @POST
    @APIResponse(responseCode = "201", description = "La couleur à été créé")
    public Response create(String couleurACreer, @Context UriInfo uriInfo) {
        Couleur couleur = new Couleur();
        couleur.setNom(couleurACreer);
        couleurRepository.persist(couleur);
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        uriBuilder.path(couleur.getId().toString());
        return Response.created(uriBuilder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public Response delete (@PathParam("id") Integer id) {
    if (couleurRepository.deleteById(id))
        return Response.noContent().build();
    else
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
