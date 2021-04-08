package com.boozedb.api.model.links;

import com.boozedb.api.model.bottle.Bottle;
import com.boozedb.resources.BottleResource;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class BottleLinks {

    public final Map<String, Map<String, String>> internal;
    public final Map<String, String> external;

    private BottleLinks(BottleLinks.Builder builder)
    {
        this.internal = builder.internal;
        this.external = builder.external;
    }

    public static final class Builder {
        public Map<String, Map<String, String>> internal = new HashMap<>();
        public Map<String, String> external = new HashMap<>();

        public Map<String, String> search = new HashMap<>();
        public Map<String, String> self = new HashMap<>();

        private UriInfo uriInfo;
        private UriBuilder uriBuilder;

        Bottle bottle;

        public Builder(Bottle bottle, UriInfo uriInfo) {
            this.bottle = bottle;
            this.uriInfo = uriInfo;
            this.uriBuilder = uriInfo.getBaseUriBuilder();
        }

        public Builder(UriInfo uriInfo) {
            this.uriInfo = uriInfo;
            this.uriBuilder = uriInfo.getBaseUriBuilder();
        }

        public Builder addExternalLink(String key, String value)
        {
            external.put(key, value);
            return this;
        }

        public BottleLinks build() {
            self().ofSameAge().ofSameSubCategory().ofSamePriceRange().ofSameProof().ofSameSize();
            internal.put("search", search);
            internal.put("self", self);
            return new BottleLinks(this);
        }

        public BottleLinks buildForNotFound() {
            ofSameAge().ofSameSubCategory().ofSamePriceRange().ofSameProof().ofSameSize();
            return new BottleLinks(this);
        }

        private Builder ofSameSubCategory()
        {
            URI ofSameCategory = uriInfo.getBaseUriBuilder()
                    .path(BottleResource.class)
                    .path(BottleResource.class, "search")
                    .queryParam("category", bottle.category)
                    .queryParam("subCategory", bottle.subCategory)
                    .queryParam("category", bottle.category)
                    .build();
            search.put("ofSameSubCategory", ofSameCategory.toASCIIString());
            return this;
        }

        private Builder ofSameSize()
        {
            URI ofSameCategory = uriInfo.getBaseUriBuilder()
                    .path(BottleResource.class)
                    .path(BottleResource.class, "search")
                    .queryParam("category", bottle.category)
                    .queryParam("subCategory", bottle.subCategory)
                    .queryParam("minSize", bottle.size)
                    .queryParam("maxSize", bottle.size)
                    .build();
            search.put("ofSameSize", ofSameCategory.toASCIIString());
            return this;
        }

        private Builder ofSamePriceRange()
        {
            URI ofSameCategory = uriInfo.getBaseUriBuilder()
                    .path(BottleResource.class)
                    .path(BottleResource.class, "search")
                    .queryParam("category", bottle.category)
                    .queryParam("subCategory", bottle.subCategory)
                    .queryParam("minPrice", bottle.price-5) // todo substitute zero if number is negative after subtracting range
                    .queryParam("maxPrice", bottle.price+5)
                    .build();
            search.put("ofSimilarPriceRange", ofSameCategory.toASCIIString());
            return this;
        }

        private Builder ofSameProof()
        {
            URI ofSameCategory = uriInfo.getBaseUriBuilder()
                    .path(BottleResource.class)
                    .path(BottleResource.class, "search")
                    .queryParam("category", bottle.category)
                    .queryParam("subCategory", bottle.subCategory)
                    .queryParam("minProof", bottle.proof)
                    .queryParam("maxProof", bottle.proof)
                    .build();
            search.put("ofSameProof", ofSameCategory.toASCIIString());
            return this;
        }

        private Builder ofSameAge()
        {
            URI ofSameCategory = uriInfo.getBaseUriBuilder()
                    .path(BottleResource.class)
                    .path(BottleResource.class, "search")
                    .queryParam("category", bottle.category)
                    .queryParam("subCategory", bottle.subCategory)
                    .queryParam("minAge", bottle.age)
                    .queryParam("maxAge", bottle.age)
                    .build();
            search.put("ofSameAge", ofSameCategory.toASCIIString());
            return this;
        }

        public Builder self()
        {
            self.put("self", uriInfo.getAbsolutePathBuilder().build().toASCIIString());
            return this;
        }
    }
}
