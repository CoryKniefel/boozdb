package com.boozedb;

import com.boozedb.resources.BottleResource;
import com.boozedb.resources.BottleResourceView;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.jdbi.v3.core.Jdbi;

public class Main extends Application<MainConfiguration> {

    public static void main(final String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public String getName() {
        return "Main";
    }

    @Override
    public void initialize(final Bootstrap<MainConfiguration> bootstrap) {

        bootstrap.addBundle(new AssetsBundle("/assets/"));
        bootstrap.addBundle(new ViewBundle<>());

    }

    @Override
    public void run(final MainConfiguration configuration,
                    final Environment environment) {


        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        final BottleResource bottleResource = new BottleResource(jdbi);
        final BottleResourceView bottleResourceView = new BottleResourceView(jdbi);

        environment.jersey().register(bottleResource);
        environment.jersey().register(bottleResourceView);
    }

}
