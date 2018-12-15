package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.UserDocument;
import uk.co.mruoc.fantasyfootball.api.UserDocument.UserDocumentBuilder;
import uk.co.mruoc.fantasyfootball.app.dao.User;
import uk.co.mruoc.fantasyfootball.app.dao.UserType;

import static org.assertj.core.api.Assertions.assertThat;

public class UserConverterTest {

    private static final long ID = 1133;
    private static final String NAME = "Joe Bloggs";
    private static final String NICKNAME = "joebloggs";
    private static final String TYPE = "ADMIN";
    private static final String EMAIL = "joe.bloggs@hotmail.com";
    private static final String PICTURE = "http://pictures.com/joebloggs";

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

        assertThat(user.getName()).isEqualTo(document.getName());
    }

    @Test
    public void shouldConvertNicknameFromDocument() {
        final UserDocument document = buildUserDocument();

        final User user = converter.toUser(document);

        assertThat(user.getNickname()).isEqualTo(document.getNickname());
    }

    @Test
    public void shouldConvertPictureFromDocument() {
        final UserDocument document = buildUserDocument();

        final User user = converter.toUser(document);

        assertThat(user.getPicture()).isEqualTo(document.getPicture());
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

    @Test
    public void shouldConvertIdToDocument() {
        final User user = buildUser();

        final UserDocument document = converter.toDocument(user);

        assertThat(document.getId()).isEqualTo(user.getId());
    }

    @Test
    public void shouldConvertNameToDocument() {
        final User user = buildUser();

        final UserDocument document = converter.toDocument(user);

        assertThat(document.getName()).isEqualTo(user.getName());
    }

    @Test
    public void shouldConvertNicknameToDocument() {
        final User user = buildUser();

        final UserDocument document = converter.toDocument(user);

        assertThat(document.getNickname()).isEqualTo(user.getNickname());
    }

    @Test
    public void shouldConvertPictureToDocument() {
        final User user = buildUser();

        final UserDocument document = converter.toDocument(user);

        assertThat(document.getPicture()).isEqualTo(user.getPicture());
    }

    @Test
    public void shouldConvertTypeToDocument() {
        final User user = buildUser();

        final UserDocument document = converter.toDocument(user);

        assertThat(document.getUserType()).isEqualTo(user.getType().name());
    }

    @Test
    public void shouldConvertEmailToDocument() {
        final User user = buildUser();

        final UserDocument document = converter.toDocument(user);

        assertThat(document.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void shouldConvertPopulateSelfLinkOnDocument() {
        final User user = buildUser();

        final UserDocument document = converter.toDocument(user);

        assertThat(document.getSelfLink()).isEqualTo("/users/" + user.getId());
    }

    private static UserDocument buildUserDocument() {
        return new UserDocumentBuilder()
                .setId(ID)
                .setName(NAME)
                .setNickname(NICKNAME)
                .setPicture(PICTURE)
                .setType(TYPE)
                .setEmail(EMAIL)
                .build();
    }

    private static User buildUser() {
        final User user = new User();
        user.setId(ID);
        user.setName(NAME);
        user.setNickname(NICKNAME);
        user.setPicture(PICTURE);
        user.setType(UserType.ADMIN);
        user.setEmail(EMAIL);
        return user;
    }

}
