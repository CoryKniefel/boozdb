package com.boozedb.resources;

import com.boozedb.api.model.bottle.BottleListResponse;
import com.boozedb.api.model.bottle.BottleResponse;
import com.boozedb.service.BottleService;
import com.boozedb.service.views.BoozeDbMeta;
import com.boozedb.service.views.BottleListView;
import com.boozedb.service.views.BottleView;
import com.boozedb.service.views.HomeView;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.views.View;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.Optional;

@Path("/")
@Produces(MediaType.TEXT_HTML)
public class BottleResourceView {

    private final BottleService bottleService;

    @Context
    private UriInfo uriInfo;

    public BottleResourceView(Jdbi jdbi)
    {
        this.bottleService = new BottleService(jdbi);
    }

    @GET
    @Timed
    @Path("/")
    public View getHomePage(@Context UriInfo requestUri)
    {
        BoozeDbMeta homePageData = bottleService.getBoozeDbMetadata();

        HomeView result = new HomeView(homePageData);

        return result;
    }

    @GET
    @Timed
    @Path("/{id}")
    public View getBottle(@PathParam("id") String id, @Context UriInfo requestUri)
    {
        View result;

        Optional<BottleResponse> bottle =  bottleService.getBottleById(id, requestUri);

        if(bottle.isPresent())
        {
            result = new BottleView(bottle.get());
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
    public View search(@Context UriInfo requestUri,
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

        BottleListResponse bottleListResponse =  bottleService.search(requestUri, category, subCategory, minPrice, maxPrice, minProof, maxProof, minSize, maxSize, minAge, maxAge, page, pageSize);

        BottleListView result = new BottleListView(bottleListResponse);

        return result;
    }
}
