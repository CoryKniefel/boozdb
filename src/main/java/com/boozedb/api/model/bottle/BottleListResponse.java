package com.boozedb.api.model.bottle;

import com.boozedb.api.model.links.BottleListLinks;

import java.util.List;

public class BottleListResponse {

    public final List<Bottle> results;
    public final BottleListLinks bottleListLinks;

    private BottleListResponse(BottleListResponse.Builder builder)
    {
        this.bottleListLinks = builder.bottleListLinks;
        this.results = builder.results;
    }

    public static final class Builder {
        List<Bottle> results;
        BottleListLinks bottleListLinks;

        public Builder(){

        }

        public BottleListResponse build()
        {
            return new BottleListResponse(this);
        }

        public Builder results(List<Bottle> results)
        {
            this.results = results;
            return this;
        }

        public Builder links(BottleListLinks bottleListLinks)
        {
            this.bottleListLinks = bottleListLinks;
            return this;
        }


    }
}
