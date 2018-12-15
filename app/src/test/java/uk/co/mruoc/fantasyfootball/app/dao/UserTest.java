package uk.co.mruoc.fantasyfootball.app.dao;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    private final User user = new User();

    @Test
    public void shouldReturnId() {
        final Long id = 9999L;

        user.setId(id);

        assertThat(user.getId()).isEqualTo(id);
    }

    @Test
    public void shouldReturnTrueIfUserHasId() {
        final Long id = 9999L;

        user.setId(id);

        assertThat(user.hasId()).isTrue();
    }

    @Test
    public void shouldReturnFalseIfUserDoesNotHaveId() {
        assertThat(user.hasId()).isFalse();
    }

    @Test
    public void shouldReturnEmail() {
        final String email = "michael.ruocco@hotmail.com";

        user.setEmail(email);

        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    public void shouldReturnTrueIfUserHasEmail() {
        final String email = "michael.ruocco@hotmail.com";

        user.setEmail(email);

        assertThat(user.hasEmail()).isTrue();
    }

    @Test
    public void shouldReturnFalseIfUserDoesNotHaveEmail() {
        assertThat(user.hasEmail()).isFalse();
    }

    @Test
    public void shouldReturnName() {
        final String name = "Joe Bloggs";

        user.setName(name);

        assertThat(user.getName()).isEqualTo(name);
    }

    @Test
    public void shouldReturnNickname() {
        final String nickname = "joebloggs";

        user.setNickname(nickname);

        assertThat(user.getNickname()).isEqualTo(nickname);
    }

    @Test
    public void shouldReturnPicture() {
        final String picture = "http://pictures.com/joebloggs";

        user.setPicture(picture);

        assertThat(user.getPicture()).isEqualTo(picture);
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

    @Test
    public void shouldUpdateNameFromUpdatedUser() {
        final User updatedUser = new User();
        final String name = "Joe Bloggs";
        updatedUser.setName(name);

        user.update(updatedUser);

        assertThat(user.getName()).isEqualTo(name);
    }

    @Test
    public void shouldUpdateNicknameFromUpdatedUser() {
        final User updatedUser = new User();
        final String nickname = "joebloggs";
        updatedUser.setNickname(nickname);

        user.update(updatedUser);

        assertThat(user.getNickname()).isEqualTo(nickname);
    }

    @Test
    public void shouldUpdateTypeFromUpdatedUser() {
        final User updatedUser = new User();
        final UserType type = UserType.ADMIN;
        updatedUser.setType(type);

        user.update(updatedUser);

        assertThat(user.getType()).isEqualTo(type);
    }

    @Test
    public void shouldUpdatePictureFromUpdatedUser() {
        final User updatedUser = new User();
        final String picture = "http://pictures.com/joebloggs";
        updatedUser.setPicture(picture);

        user.update(updatedUser);

        assertThat(user.getPicture()).isEqualTo(picture);
    }

    @Test
    public void shouldUpdateEmailFromUpdatedUser() {
        final User updatedUser = new User();
        final String email = "joe.bloggs@hotmail.com";
        updatedUser.setEmail(email);

        user.update(updatedUser);

        assertThat(user.getEmail()).isEqualTo(email);
    }

}
