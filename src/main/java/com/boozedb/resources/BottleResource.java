package com.boozedb.resources;

import com.codahale.metrics.annotation.Timed;
import com.boozedb.api.model.bottle.BottleListResponse;
import com.boozedb.api.model.bottle.BottleResponse;
import com.boozedb.service.BottleService;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Optional;

@Path("/api/bottle")
@Produces(MediaType.APPLICATION_JSON)
public class BottleResource {

    private final BottleService bottleService;

    @Context
    UriInfo uriInfo;

    public BottleResource(Jdbi jdbi)
    {
        this.bottleService = new BottleService(jdbi);
    }

    @GET
    @Timed
    @Path("/{id}")
    public Response getBottle(@PathParam("id") String id, @Context UriInfo requestUri)
    {
        Response result;

        Optional<BottleResponse> bottle =  bottleService.getBottleById(id, requestUri);

        if(bottle.isPresent())
        {
            result = Response.ok().entity(bottle.get()).build();
        }
        else
        {
            throw new NotFoundException();
        }

        return result;
    }

    @GET
    @Timed
    @Path("/search")
    public BottleListResponse search(@Context UriInfo requestUri,
                                     @QueryParam("category") Optional<String> category,
                                     @QueryParam("subCategory") Optional<String> subCategory,
                                     @QueryParam("minPrice") Optional<Double> minPrice,
                                     @QueryParam("maxPrice") Optional<Double> maxPrice,
                                     @QueryParam("minProof") Optional<Double> minProof,
                                     @QueryParam("maxProof") Optional<Double> maxProof,
                                     @QueryParam("minSize") Optional<Double> minSize,
                                     @QueryParam("maxSize") Optional<Double> maxSize,
                                     @QueryParam("minAge") Optional<Integer> minAge,
                                     @QueryParam("maxAge") Optional<Integer> maxAge,
                                     @QueryParam("page") Optional<Integer> page,
                                     @QueryParam("pageSize") Optional<Integer> pageSize
                                 )
    {

        BottleListResponse result =  bottleService.search(requestUri, category, subCategory, minPrice, maxPrice, minProof, maxProof, minSize, maxSize, minAge, maxAge, page, pageSize);

        return result;
    }

}
