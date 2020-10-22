package com.boozdb.service.views;

import com.boozdb.api.model.bottle.BottleListResponse;
import io.dropwizard.views.View;

public class BottleListView extends View {

    public final BottleListResponse bottleListResponse;

    public BottleListView(BottleListResponse bottleListResponse) {
        super("index.mustache");
        this.bottleListResponse = bottleListResponse;
    }
}
