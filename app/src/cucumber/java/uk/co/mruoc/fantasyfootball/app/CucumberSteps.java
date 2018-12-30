package uk.co.mruoc.fantasyfootball.app;

import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import lv.ctco.cukes.core.internal.resources.ResourceFileReader;
import lv.ctco.cukes.http.facade.HttpAssertionFacade;
import lv.ctco.cukes.http.facade.HttpRequestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CucumberSteps {

    private static final Logger LOG = LoggerFactory.getLogger(CucumberSteps.class);

    private final HttpRequestFacade requestFacade;
    private final HttpAssertionFacade assertionFacade;
    private final ResourceFileReader fileReader;

    private String baseUri;

    @Inject
    public CucumberSteps(final HttpRequestFacade facade,
                         final HttpAssertionFacade assertionFacade,
                         final ResourceFileReader fileReader) {
        this.requestFacade = facade;
        this.assertionFacade = assertionFacade;
        this.fileReader = fileReader;
    }

    @Before
    public void setUp() {
        if (!isStarted()) {
            ApplicationStarter starter = new ApplicationStarter();
            baseUri = starter.start();
        }
        requestFacade.baseUri(baseUri);
    }

    @Then("^header \"([^\"]*)\" contains \"([^\"]*)\" replacing placeholders$")
    public void header_contains_replacing_placeholders(final String headerName, final String originalValue) {
        LOG.debug("original value {}", originalValue);
        final String replacedValue = replacePlaceholders(originalValue);
        LOG.debug("replaced value {}", replacedValue);
        this.assertionFacade.headerContains(headerName, replacedValue);
    }

    @Then("^response contains properties from file \"([^\"]*)\" replacing placeholders$")
    public void response_contains_properties_from_file_replacing_placeholders(final String path) {
        final String originalContent = this.fileReader.read(path);
        LOG.debug("original content {}", originalContent);
        final String replacedContent = replacePlaceholders(originalContent);
        LOG.debug("replaced content {}", replacedContent);
        this.assertionFacade.bodyContainsPropertiesFromJson(replacedContent);
    }

    private boolean isStarted() {
        return baseUri != null;
    }

    private String replacePlaceholders(final String content) {
        return content.replaceAll("\\{\\{base-uri\\}\\}", baseUri);
    }

}
