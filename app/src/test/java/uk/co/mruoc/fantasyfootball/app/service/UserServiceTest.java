package uk.co.mruoc.fantasyfootball.app.service;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.app.dao.User;
import uk.co.mruoc.fantasyfootball.app.dao.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class UserServiceTest {

    private static final long ID = 1234;
    private static final String EMAIL = "michael.ruocco@hotmail.com";

    private final UserRepository repository = mock(UserRepository.class);

    private final UserService service = new UserService(repository);

    @Test
    public void shouldReturnUserIfFoundWithId() {
        final User expectedUser = mock(User.class);
        given(repository.findById(ID)).willReturn(Optional.of(expectedUser));

        final User user = service.read(ID);

        assertThat(user).isEqualTo(expectedUser);
    }

    @Test
    public void shouldThrowExceptionIfUserNotFoundWithId() {
        given(repository.findById(ID)).willReturn(Optional.empty());
        final String expectedMessage = String.format("user with id %s not found", ID);

        final Throwable thrown = catchThrowable(() -> service.read(ID));

        assertThat(thrown).isInstanceOf(UserIdNotFoundException.class)
                .hasNoCause()
                .hasMessage(expectedMessage);
    }

    @Test
    public void shouldReturnUserIfFoundWithEmail() {
        final User expectedUser = mock(User.class);
        given(repository.findByEmail(EMAIL)).willReturn(Optional.of(expectedUser));

        final User user = service.readByEmail(EMAIL);

        assertThat(user).isEqualTo(expectedUser);
    }

    @Test
    public void shouldThrowExceptionIfUserNotFoundWithEmail() {
        given(repository.findByEmail(EMAIL)).willReturn(Optional.empty());
        final String expectedMessage = String.format("user with email %s not found", EMAIL);

        final Throwable thrown = catchThrowable(() -> service.readByEmail(EMAIL));

        assertThat(thrown).isInstanceOf(UserEmailNotFoundException.class)
                .hasNoCause()
                .hasMessage(expectedMessage);
    }

    @Test
    public void shouldCreateUser() {
        final User user = mock(User.class);
        final User expectedUser = mock(User.class);
        given(repository.save(user)).willReturn(expectedUser);

        final User resultUser = service.create(user);

        assertThat(resultUser).isEqualTo(expectedUser);
    }

    @Test
    public void shouldThrowExceptionIfUserUpdatedWithNoEmail() {
        final User user = mock(User.class);

        final Throwable thrown = catchThrowable(() -> service.update(user));

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasNoCause()
                .hasMessage("cannot update user without email");
    }

    @Test
    public void shouldThrowExceptionIfUserToUpdateDoesNotExist() {
        final User user = mock(User.class);
        given(user.hasEmail()).willReturn(true);
        given(user.getEmail()).willReturn(EMAIL);
        given(repository.existsByEmail(EMAIL)).willReturn(false);
        final String expectedMessage = String.format("user with email %s not found", EMAIL);

        final Throwable thrown = catchThrowable(() -> service.update(user));

        assertThat(thrown).isInstanceOf(UserEmailNotFoundException.class)
                .hasNoCause()
                .hasMessage(expectedMessage);
    }

    @Test
    public void shouldUpdateUser() {
        final User user = mock(User.class);
        final User expectedUser = mock(User.class);
        given(user.hasEmail()).willReturn(true);
        given(user.getEmail()).willReturn(EMAIL);
        given(repository.existsByEmail(EMAIL)).willReturn(true);
        given(repository.save(user)).willReturn(expectedUser);

        final User resultUser = service.update(user);

        assertThat(resultUser).isEqualTo(expectedUser);
    }

    @Test
    public void shouldReturnTrueIfUserExistsWithEmail() {
        given(repository.existsByEmail(EMAIL)).willReturn(true);

        assertThat(service.existsByEmail(EMAIL)).isTrue();
    }

    @Test
    public void shouldReturnFalseIfUserDoesNotExistWithEmail() {
        given(repository.existsByEmail(EMAIL)).willReturn(false);

        assertThat(service.existsByEmail(EMAIL)).isFalse();
    }

}