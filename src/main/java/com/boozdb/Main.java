package com.boozdb;

import com.boozdb.resources.BottleResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
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

    }

    @Override
    public void run(final MainConfiguration configuration,
                    final Environment environment) {


        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        final BottleResource bottleResource = new BottleResource(jdbi);

        environment.jersey().register(bottleResource);
    }

}
