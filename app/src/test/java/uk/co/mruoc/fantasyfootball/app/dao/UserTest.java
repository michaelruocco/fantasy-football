package uk.co.mruoc.fantasyfootball.app.dao;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    private final User user = new User();

    @Test
    public void shouldReturnEmail() {
        final String email = "michael.ruocco@hotmail.com";

        user.setEmail(email);

        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    public void shouldReturnFirstName() {
        final String firstName = "Joe";

        user.setFirstName(firstName);

        assertThat(user.getFirstName()).isEqualTo(firstName);
    }

    @Test
    public void shouldReturnLastName() {
        final String lastName = "Bloggs";

        user.setLastName(lastName);

        assertThat(user.getLastName()).isEqualTo(lastName);
    }

    @Test
    public void shouldReturnUserType() {
        final UserType type = UserType.ADMIN;

        user.setType(type);

        assertThat(user.getType()).isEqualTo(type);
    }

    @Test
    public void shouldReturnTrueIfUserIsAdmin() {
        final UserType type = UserType.ADMIN;

        user.setType(type);

        assertThat(user.isAdmin()).isTrue();
    }

    @Test
    public void shouldReturnFalseIfUserIsNotAdmin() {
        final UserType type = UserType.STANDARD;

        user.setType(type);

        assertThat(user.isAdmin()).isFalse();
    }

}
