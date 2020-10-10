package com.boozdb.api.model.links;

import com.boozdb.resources.BottleResource;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BottleListLinks {

    public final Map<String, Map<String, String>> internal;
    public final Map<String, String> external;

    private BottleListLinks(BottleListLinks.Builder builder)
    {
        this.internal =  builder.internal;
        this.external = builder.external;
    }

    public static final class Builder {
        public Map<String, Map<String, String>> internal = new HashMap<>();
        public Map<String, String> external = new HashMap<>();
        private int resultSize;

        private Map<String, String> pagination = new HashMap<>();
        private Map<String, String> search = new HashMap<>();

        private final UriInfo uriInfo;
        private final UriBuilder uriBuilder;

        public Builder(UriInfo uriInfo)
        {
            this.uriInfo = uriInfo;
            this.uriBuilder = uriInfo.getBaseUriBuilder();
        }

        public BottleListLinks build() {

            // todo: must validate next and previous page links return results.
            // probably will need to make a db call. and will want to introduce di
            if(resultSize > 0)
            {
                nextPage().previousPage();
                internal.put("pagination", pagination);
            }
            else
            {
                minPrice50().maxPrice25();
                internal.put("search", search);
            }

            return new BottleListLinks(this);
        }

        public BottleListLinks.Builder resultSize(int resultSize)
        {
            this.resultSize = resultSize;
            return this;
        }

        public BottleListLinks.Builder addExternalLink(String key, String value)
        {
            external.put(key, value);
            return this;
        }

        public Builder nextPage()
        {
            String nextPageNum = uriInfo.getQueryParameters().getOrDefault("page", List.of("1")).get(0);
            int num = Integer.parseInt(nextPageNum);
            URI nextPage = uriInfo.getRequestUriBuilder().replaceQueryParam("page", ++num).build();

            pagination.put("next", nextPage.toASCIIString());

            return this;
        }

        public Builder previousPage()
        {
            String nextPageNum = uriInfo.getQueryParameters().getOrDefault("page", List.of("1")).get(0);
            int num = Integer.parseInt(nextPageNum);
            if(num > 1)
            {
                URI nextPage = uriInfo.getRequestUriBuilder().replaceQueryParam("page", --num).build();
                pagination.put("last", nextPage.toASCIIString());
            }

            return this;
        }

        private BottleListLinks.Builder maxPrice25()
        {
            URI ofSameCategory = uriInfo.getBaseUriBuilder()
                    .path(BottleResource.class)
                    .path(BottleResource.class, "search")
                    .queryParam("maxPrice", "25")
                    .build();

            search.put("maxPrice25", ofSameCategory.toASCIIString());
            return this;
        }

        private BottleListLinks.Builder minPrice50()
        {
            URI ofSameCategory = uriInfo.getBaseUriBuilder()
                    .path(BottleResource.class)
                    .path(BottleResource.class, "search")
                    .queryParam("minPrice", "50")
                    .build();

            search.put("minPrice50", ofSameCategory.toASCIIString());
            return this;
        }
    }

}
