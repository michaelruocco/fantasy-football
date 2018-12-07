package uk.co.mruoc.fantasyfootball.app.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uk.co.mruoc.fantasyfootball.api.ErrorDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerNotFoundErrorData;
import uk.co.mruoc.fantasyfootball.app.service.AbstractAlreadyExistsException;
import uk.co.mruoc.fantasyfootball.app.service.AbstractNotFoundException;
import uk.co.mruoc.fantasyfootball.app.service.IdAwareException;
import uk.co.mruoc.fantasyfootball.app.service.PlayerAlreadyExistsException;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(AbstractNotFoundException.class)
    public ResponseEntity<ErrorDocument> handleError(AbstractNotFoundException e) {
        final ErrorDocument document = toErrorDocument(e);
        return ResponseEntity.status(NOT_FOUND).body(document);
    }

    @ExceptionHandler(AbstractAlreadyExistsException.class)
    public ResponseEntity<ErrorDocument> handleError(AbstractAlreadyExistsException e) {
        final ErrorDocument document = toErrorDocument(e);
        return ResponseEntity.status(CONFLICT).body(document);
    }

    private static ErrorDocument toErrorDocument(IdAwareException e) {
        return new ErrorDocument(new PlayerNotFoundErrorData(e.getId(), e.getMessage()));
    }

}
