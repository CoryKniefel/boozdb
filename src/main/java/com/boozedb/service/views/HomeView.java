package com.boozedb.service.views;

import io.dropwizard.views.View;

public class HomeView extends View {

    final public BoozeDbMeta meta;

    public HomeView(BoozeDbMeta meta) {
        super("index.mustache");
        this.meta = meta;
    }
}
