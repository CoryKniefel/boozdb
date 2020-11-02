package com.boozedb.api.model.bottle;

import com.boozedb.api.model.links.BottleLinks;

public class BottleResponse {

    public final Bottle bottle;
    public final BottleLinks bottleLinks;

    private  BottleResponse(BottleResponse.Builder builder)
    {
        this.bottle = builder.bottle;
        this.bottleLinks = builder.bottleLinks;
    }


    public static final class Builder {
        Bottle bottle;
        BottleLinks bottleLinks;

        public Builder() {
        }

        public Builder bottle(Bottle bottle) {
            this.bottle = bottle;
            return this;
        }

        public Builder links(BottleLinks bottleLinks) {
            this.bottleLinks = bottleLinks;
            return this;
        }

        public BottleResponse build() {
            return new BottleResponse(this);
        }
    }
}
