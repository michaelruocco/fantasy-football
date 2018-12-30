package uk.co.mruoc.fantasyfootball.app.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.co.mruoc.fantasyfootball.api.JsonApiDocument;

import java.net.URI;
import java.net.URISyntaxException;

public class ResponseBuilder<T extends JsonApiDocument> {

    public ResponseEntity<T> buildCreatedResponse(T document) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(buildLocationUri(document));
        return new ResponseEntity<>(document, headers, HttpStatus.CREATED);
    }

    public ResponseEntity<T> buildUpdatedResponse(T document) {
        return new ResponseEntity<>(document, new HttpHeaders(), HttpStatus.OK);
    }

    public ResponseEntity<Void> buildDeletedResponse() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private URI buildLocationUri(T document) {
        try {
            return new URI(document.getSelfLink());
        } catch (URISyntaxException e) {
            throw new InvalidLinkException(e);
        }
    }

}
