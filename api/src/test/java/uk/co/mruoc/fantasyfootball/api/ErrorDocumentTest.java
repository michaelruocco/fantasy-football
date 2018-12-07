package uk.co.mruoc.fantasyfootball.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrorDocumentTest {

    @Test
    public void shouldReturnErrors() {
        final ErrorData error1 = new PlayerNotFoundErrorData(1, "error1");
        final ErrorData error2 = new ClubNotFoundErrorData(2, "error2");

        final ErrorDocument document = new ErrorDocument(error1, error2);

        assertThat(document.getErrors()).containsExactly(error1, error2);
    }
    
}
