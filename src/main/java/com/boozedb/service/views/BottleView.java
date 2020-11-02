package com.boozedb.service.views;

import com.boozedb.api.model.bottle.BottleResponse;
import io.dropwizard.views.View;

public class BottleView extends View {

    public final BottleResponse bottleResponse;

    public BottleView(BottleResponse bottleResponse) {
        super("index.mustache");
        this.bottleResponse = bottleResponse;
    }

    //todo verify this is needed
    public BottleResponse getBottleResponse()
    {
        return this.bottleResponse;
    }
}
