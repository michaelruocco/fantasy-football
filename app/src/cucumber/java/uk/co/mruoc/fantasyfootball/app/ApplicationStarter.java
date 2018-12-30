package uk.co.mruoc.fantasyfootball.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class ApplicationStarter {

    private static final Logger LOG = LoggerFactory.getLogger(ApplicationStarter.class);
    private static final int PORT = PortProvider.getFreePort();

    public String start() {
        return getProvidedBaseUri().orElseGet(this::startApplication);
    }

    private Optional<String> getProvidedBaseUri() {
        return Optional.ofNullable(System.getProperty("base-uri"));
    }

    private String startApplication() {
        final String[] properties = createProperties();
        LOG.info("starting application with properties " + Arrays.toString(properties));
        Application.main(properties);
        return getStartedApplicationUri();
    }

    private String[] createProperties() {
        final List<String> properties = new ArrayList<>();
        properties.add("--server.port=" + PORT);
        properties.add("--spring.profiles.active=test");
        return properties.toArray(new String[0]);
    }

    private String getStartedApplicationUri() {
        return String.format("http://localhost:%d", PORT);
    }

}
