package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.UserDocument;
import uk.co.mruoc.fantasyfootball.api.UserDocument.UserDocumentBuilder;
import uk.co.mruoc.fantasyfootball.app.dao.User;
import static org.assertj.core.api.Assertions.assertThat;

public class UserConverterTest {

    private static final long ID = 1133;
    private static final String FIRST_NAME = "Joe";
    private static final String LAST_NAME = "Bloggs";
    private static final String TYPE = "ADMIN";
    private static final String EMAIL = "joe.bloggs@hotmail.com";

    private final UserConverter converter = new UserConverter();

    @Test
    public void shouldConvertIdFromDocument() {
        final UserDocument document = buildUserDocument();

        final User user = converter.toUser(document);

        assertThat(user.getId()).isEqualTo(document.getId());
    }

    @Test
    public void shouldConvertSpecifiedId() {
        final long id = 9191;
        final UserDocument document = buildUserDocument();

        final User user = converter.toUser(id, document);

        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    public void shouldConvertNameFromDocument() {
        final UserDocument document = buildUserDocument();

        final User user = converter.toUser(document);

        assertThat(user.getFirstName()).isEqualTo(document.getFirstName());
    }

    @Test
    public void shouldConvertLastNameFromDocument() {
        final UserDocument document = buildUserDocument();

        final User user = converter.toUser(document);

        assertThat(user.getLastName()).isEqualTo(document.getLastName());
    }

    @Test
    public void shouldConvertTypeFromDocument() {
        final UserDocument document = buildUserDocument();

        final User user = converter.toUser(document);

        assertThat(user.getType().name()).isEqualTo(document.getUserType());
    }

    @Test
    public void shouldConvertEmailFromDocument() {
        final UserDocument document = buildUserDocument();

        final User user = converter.toUser(document);

        assertThat(user.getEmail()).isEqualTo(document.getEmail());
    }

    private static UserDocument buildUserDocument() {
        return new UserDocumentBuilder()
                .setId(ID)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setType(TYPE)
                .setEmail(EMAIL)
                .build();
    }

}
