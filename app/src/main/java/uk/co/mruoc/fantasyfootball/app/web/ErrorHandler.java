package uk.co.mruoc.fantasyfootball.app.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uk.co.mruoc.fantasyfootball.api.ErrorData;
import uk.co.mruoc.fantasyfootball.api.ErrorDocument;
import uk.co.mruoc.fantasyfootball.api.InternalServerErrorData;
import uk.co.mruoc.fantasyfootball.api.JsonApiException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDocument> handleError(Throwable e) {
        final ErrorDocument document = toErrorDocument(e);
        return ResponseEntity.status(NOT_FOUND).body(document);
    }

    private static ErrorDocument toErrorDocument(Throwable e) {
        final ErrorData errorData = toErrorData(e);
        return new ErrorDocument(errorData);
    }

    private static ErrorData toErrorData(Throwable e) {
        if (e instanceof JsonApiException) {
            final JsonApiException jsonApiException = (JsonApiException) e;
            return jsonApiException.getErrorData();
        }
        return new InternalServerErrorData(e.getMessage());
    }

}
