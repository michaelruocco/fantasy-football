package uk.co.mruoc.fantasyfootball.app.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.co.mruoc.fantasyfootball.api.JsonApiDocument;

import java.net.URI;
import java.net.URISyntaxException;

public class CreatedResponseBuilder<T extends JsonApiDocument> {

    public ResponseEntity<T> build(T document) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(buildLocationUri(document));
        return new ResponseEntity<>(document, headers, HttpStatus.CREATED);
    }

    private URI buildLocationUri(T document) {
        try {
            return new URI(document.getSelfLink());
        } catch (URISyntaxException e) {
            throw new InvalidLinkException(e);
        }
    }
}