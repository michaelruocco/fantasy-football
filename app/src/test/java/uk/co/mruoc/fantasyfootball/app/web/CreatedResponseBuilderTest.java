package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.co.mruoc.fantasyfootball.api.JsonApiDocument;

import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class CreatedResponseBuilderTest {

    private static final String URI = "http://localhost:8080/myPage";

    private final JsonApiDocument document = new FakeDocument(URI);

    private final CreatedResponseBuilder<JsonApiDocument> builder = new CreatedResponseBuilder<>();

    @Test
    public void shouldReturnEntityWithDocumentAsBody() {
        final ResponseEntity<JsonApiDocument> entity = builder.build(document);

        assertThat(entity.getBody()).isEqualTo(document);
    }

    @Test
    public void shouldReturnEntityWithCreatedHttpStatus() {
        final ResponseEntity<JsonApiDocument> entity = builder.build(document);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void shouldSetLocationHeaderWithDocumentSelfLink() {
        final ResponseEntity<JsonApiDocument> entity = builder.build(document);

        final HttpHeaders headers = entity.getHeaders();
        assertThat(headers.get("Location")).containsExactly(document.getSelfLink());
    }

    @Test
    public void shouldThrowExceptionIfUriIsInvalid() {
        final JsonApiDocument invalidUriDocument = new FakeDocument("\\invalid");

        final Throwable thrown = catchThrowable(() -> builder.build(invalidUriDocument));

        assertThat(thrown)
                .isInstanceOf(InvalidLinkException.class)
                .hasCauseInstanceOf(URISyntaxException.class);
    }

    private static class FakeDocument implements JsonApiDocument {

        private final String selfLink;

        private FakeDocument(String selfLink) {
            this.selfLink = selfLink;
        }

        @Override
        public String getSelfLink() {
            return selfLink;
        }

    }

}
