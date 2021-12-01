package fr.fs.endpoints;

import fr.fs.entities.Continent;
import fr.fs.repositories.ContinentRepository;
import fr.fs.repositories.CountryRepository;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("Continents")
public class ContinentEndPoints {

    @Inject
    ContinentRepository continentRepository;

    @GET
    public Response getAll(){

        return Response.ok(continentRepository.findAllContinents()).build();
    }

}
