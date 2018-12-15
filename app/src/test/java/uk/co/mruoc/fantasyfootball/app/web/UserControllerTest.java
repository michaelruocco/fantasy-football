package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.co.mruoc.fantasyfootball.api.UserDocument;
import uk.co.mruoc.fantasyfootball.api.UserDocument.UserDocumentBuilder;
import uk.co.mruoc.fantasyfootball.app.dao.User;
import uk.co.mruoc.fantasyfootball.app.dao.UserType;
import uk.co.mruoc.fantasyfootball.app.service.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class UserControllerTest {

    private static final long ID = 1133;
    private static final String NAME = "Joe Bloggs";
    private static final String NICKNAME = "joebloggs";
    private static final UserType TYPE = UserType.ADMIN;
    private static final String EMAIL = "joe.bloggs@hotmail.com";
    private static final String PICTURE = "http://picture.com/joebloggs";
    private static final String SELF_LINK = String.format("/users/%d", ID);

    private final UserService service = mock(UserService.class);
    private final UserConverter converter = mock(UserConverter.class);

    private final UserDocument document = buildUserDocument();
    private final User user = new User();

    private final UserController controller = new UserController(service, converter);

    @Test
    public void shouldCreateUser() {
        given(converter.toUser(document)).willReturn(user);
        final User createdUser = new User();
        given(service.create(user)).willReturn(createdUser);
        final UserDocument createdDocument = buildUserDocument();
        given(converter.toDocument(createdUser)).willReturn(createdDocument);

        final ResponseEntity<UserDocument> entity = controller.create(document);

        assertThat(entity.getBody()).isEqualTo(createdDocument);
    }

    @Test
    public void shouldReturnCreatedStatusCodeOnCreate() {
        given(converter.toUser(document)).willReturn(user);
        given(service.create(user)).willReturn(user);
        given(converter.toDocument(user)).willReturn(document);

        final ResponseEntity<UserDocument> entity = controller.create(document);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void shouldReturnOkStatusCodeOnCreateWithIdThatAlreadyExists() {
        given(converter.toUser(document)).willReturn(user);
        given(service.existsByEmail(EMAIL)).willReturn(true);
        given(service.update(user)).willReturn(user);
        given(converter.toDocument(user)).willReturn(document);

        final ResponseEntity<UserDocument> entity = controller.create(document);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnLocationHeaderWithNewResourceUrlOnCreate() {
        given(converter.toUser(document)).willReturn(user);
        given(service.create(user)).willReturn(user);
        given(converter.toDocument(user)).willReturn(document);

        final ResponseEntity<UserDocument> entity = controller.create(document);

        final HttpHeaders headers = entity.getHeaders();
        assertThat(headers.get("Location")).containsExactly("/users/1133");
    }

    @Test
    public void shouldUpdateUser() {
        given(converter.toUser(ID, document)).willReturn(user);
        final User updatedUser = new User();
        given(service.update(user)).willReturn(updatedUser);
        final UserDocument updatedDocument = buildUserDocument();
        given(converter.toDocument(updatedUser)).willReturn(updatedDocument);

        final ResponseEntity<UserDocument> entity = controller.update(ID, document);

        assertThat(entity.getBody()).isEqualTo(updatedDocument);
    }

    @Test
    public void shouldReturnOkStatusOnUpdate() {
        given(converter.toUser(document)).willReturn(user);
        given(service.update(user)).willReturn(user);
        given(converter.toDocument(user)).willReturn(document);

        final ResponseEntity<UserDocument> entity = controller.update(document.getId(), document);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReadUser() {
        given(service.read(document.getId())).willReturn(user);
        final UserDocument readDocument = new UserDocument();
        given(converter.toDocument(user)).willReturn(readDocument);

        final UserDocument resultDocument = controller.read(document.getId());

        assertThat(resultDocument).isEqualTo(readDocument);
    }

    @Test
    public void shouldReadUserByEmail() {
        given(service.readByEmail(document.getEmail())).willReturn(user);
        final UserDocument readDocument = new UserDocument();
        given(converter.toDocument(user)).willReturn(readDocument);

        final UserDocument resultDocument = controller.read(document.getEmail());

        assertThat(resultDocument).isEqualTo(readDocument);
    }

    private static UserDocument buildUserDocument() {
        return new UserDocumentBuilder()
                .setId(ID)
                .setName(NAME)
                .setNickname(NICKNAME)
                .setPicture(PICTURE)
                .setType(TYPE.name())
                .setEmail(EMAIL)
                .setSelfLink(SELF_LINK)
                .build();
    }

}
