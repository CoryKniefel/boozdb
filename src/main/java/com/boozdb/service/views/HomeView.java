package com.boozdb.service.views;

import io.dropwizard.views.View;

public class HomeView extends View {

    final public BoozDbMeta meta;

    public HomeView(BoozDbMeta meta) {
        super("index.mustache");
        this.meta = meta;
    }
}
