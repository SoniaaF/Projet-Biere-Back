package fr.fs.endpoints;

import fr.fs.entities.Article;
import fr.fs.repositories.ArticleRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/articles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArticleEndPoints {

    @Inject
    ArticleRepository articleRepository;

    @GET
    @Operation(summary = "Items", description = "Get all items")
    @APIResponse(responseCode = "200", description = "Ok, items found !")
    @APIResponse(responseCode = "204", description = "No item !")

    public Response getAll() {
        List <Article> articles = articleRepository.listAll();
        if (articles.isEmpty())
            return Response.noContent().build();
        else
            return Response.ok(articles).build();
    }

    @Path("{id}")
    public Response getById (@PathParam("id") Integer id){
        Article article = articleRepository.findById(id);
        if (article == null)
            return Response.noContent().build();
        else
            return Response.ok(article).build();
    }

    @Transactional
    @POST
    @APIResponse(responseCode = "201", description = "L'article à été créé")
    public Response create(String articleACreer, @Context UriInfo uriInfo) {
        Article article = new Article();
        article.setNom(articleACreer);
        articleRepository.persist(article);
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        uriBuilder.path(article.getId().toString());
        return Response.created(uriBuilder.build()).build();
    }

    @Transactional
    @DELETE
    @Path("{id}")
    public Response delete (@PathParam("id") Integer id) {
        if (articleRepository.deleteById(id))
            return Response.noContent().build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

}
