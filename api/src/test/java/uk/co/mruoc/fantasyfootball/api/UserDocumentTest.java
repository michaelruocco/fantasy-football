package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.UserDocument.UserDocumentBuilder;
import uk.co.mruoc.file.ContentLoader;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDocumentTest {

    private static final String JSON_PATH = "/userDocument.json";
    private static final String JSON = ContentLoader.loadContentFromClasspath(JSON_PATH);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final long ID = 1133;
    private static final String NAME = "Joe Bloggs";
    private static final String NICKNAME = "joebloggs";
    private static final String TYPE = "ADMIN";
    private static final String EMAIL = "joe.bloggs@hotmail.com";
    private static final String PICTURE = "http://pictures.com/joebloggs";
    private static final String SELF_LINK = String.format("/users/%s", ID);

    @Test
    public void shouldSerializeToJsonCorrectly() throws JsonProcessingException {
        final UserDocument document = buildUserDocument();

        final String json = MAPPER.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(JSON);
    }

    @Test
    public void shouldDeserializeFromJsonCorrectly() throws IOException {
        final UserDocument expectedDocument = buildUserDocument();

        final UserDocument document = MAPPER.readValue(JSON, UserDocument.class);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldReturnTrueIfHasId() {
        final UserDocument document = buildUserDocument();

        assertThat(document.hasId()).isTrue();
    }

    @Test
    public void shouldReturnFalseIfDoesNotHaveId() {
        final UserDocument document = new UserDocument();

        assertThat(document.hasId()).isFalse();
    }

    @Test
    public void shouldReturnId() {
        final UserDocument document = buildUserDocument();

        assertThat(document.getId()).isEqualTo(ID);
    }

    @Test
    public void shouldReturnName() {
        final UserDocument document = buildUserDocument();

        assertThat(document.getName()).isEqualTo(NAME);
    }

    @Test
    public void shouldReturnNickname() {
        final UserDocument document = buildUserDocument();

        assertThat(document.getNickname()).isEqualTo(NICKNAME);
    }

    @Test
    public void shouldReturnPicture() {
        final UserDocument document = buildUserDocument();

        assertThat(document.getPicture()).isEqualTo(PICTURE);
    }

    @Test
    public void shouldReturnUserType() {
        final UserDocument document = buildUserDocument();

        assertThat(document.getUserType()).isEqualTo(TYPE);
    }

    @Test
    public void shouldReturnEmail() {
        final UserDocument document = buildUserDocument();

        assertThat(document.getEmail()).isEqualTo(EMAIL);
    }

    @Test
    public void shouldReturnTrueIfHasEmail() {
        final UserDocument document = buildUserDocument();

        assertThat(document.hasEmail()).isTrue();
    }

    @Test
    public void shouldReturnFalseIfDoesNotHaveEmail() {
        final UserDocument document = new UserDocument();

        assertThat(document.hasEmail()).isFalse();
    }

    @Test
    public void shouldReturnSelfLink() {
        final UserDocument document = buildUserDocument();

        assertThat(document.getSelfLink()).isEqualTo(SELF_LINK);
    }

    private static UserDocument buildUserDocument() {
        return new UserDocumentBuilder()
                .setId(ID)
                .setName(NAME)
                .setNickname(NICKNAME)
                .setPicture(PICTURE)
                .setType(TYPE)
                .setEmail(EMAIL)
                .setSelfLink(SELF_LINK)
                .build();
    }

}