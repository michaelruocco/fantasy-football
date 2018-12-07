package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.co.mruoc.fantasyfootball.api.ErrorData;
import uk.co.mruoc.fantasyfootball.api.ErrorDocument;
import uk.co.mruoc.fantasyfootball.app.service.AbstractNotFoundException;
import uk.co.mruoc.fantasyfootball.app.service.PlayerNotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class ErrorHandlerTest {

    private static final long ID = 10;

    private final ErrorHandler handler = new ErrorHandler();

    @Test
    public void shouldReturnNotFoundStatusCodeForNotFoundException() {
        AbstractNotFoundException exception = new PlayerNotFoundException(ID);

        ResponseEntity<ErrorDocument> entity = handler.handleError(exception);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldReturnNotFoundErrorDocument() {
        AbstractNotFoundException exception = new PlayerNotFoundException(ID);

        ResponseEntity<ErrorDocument> entity = handler.handleError(exception);

        ErrorDocument document = entity.getBody();
        List<ErrorData> errors = document.getErrors();
        assertThat(errors).hasSize(1);

        ErrorData error = errors.get(0);
        assertThat(error.getId()).isNotNull();
        assertThat(error.getCode()).isEqualTo("PLAYER_NOT_FOUND");
        assertThat(error.getTitle()).isEqualTo("Player not found");
        assertThat(error.getDetail()).isEqualTo(String.format("player with id %s not found", ID));
        assertThat(error.getMeta()).containsOnly(entry("id", ID));
    }

}
