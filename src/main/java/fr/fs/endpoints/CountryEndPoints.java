package fr.fs.endpoints;

import fr.fs.repositories.CountryRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("countries")
public class CountryEndPoints {

    @Inject
    CountryRepository countryRepository;

    @GET
    public Response getAll(){

        return Response.ok(countryRepository.findAllCountries()).build();
    }

}
