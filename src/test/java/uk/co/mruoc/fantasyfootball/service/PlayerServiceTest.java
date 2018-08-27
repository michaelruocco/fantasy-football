package uk.co.mruoc.fantasyfootball.service;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.dao.Player;
import uk.co.mruoc.fantasyfootball.dao.PlayerRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class PlayerServiceTest {

    private static final long ID = 1234;

    private final PlayerRepository repository = mock(PlayerRepository.class);

    private final PlayerService service = new PlayerService(repository);

    @Test
    public void shouldReturnPlayerIfFound() {
        final Player expectedPlayer = mock(Player.class);
        given(repository.findById(ID)).willReturn(Optional.of(expectedPlayer));

        final Player player = service.read(ID);

        assertThat(player).isEqualTo(expectedPlayer);
    }

    @Test
    public void shouldThrowExceptionIfPlayerNotFound() {
        given(repository.findById(ID)).willReturn(Optional.empty());
        final String expectedMessage = String.format("player with id %d not found", ID);

        final Throwable thrown = catchThrowable(() -> service.read(ID));

        assertThat(thrown).isInstanceOf(PlayerNotFoundException.class)
                .hasNoCause()
                .hasMessage(expectedMessage);
    }

    @Test
    public void shouldUpsertPlayer() {
        final Player player = mock(Player.class);
        final Player expectedPlayer = mock(Player.class);
        given(repository.save(player)).willReturn(expectedPlayer);

        final Player resultPlayer = service.upsert(player);

        assertThat(resultPlayer).isEqualTo(expectedPlayer);
    }

    @Test
    public void shouldThrowExceptionIfPlayerUpdatedWithNoId() {
        final Player player = mock(Player.class);

        final Throwable thrown = catchThrowable(() -> service.update(player));

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasNoCause()
                .hasMessage("cannot update player without id");
    }

    @Test
    public void shouldThrowExceptionIfPlayerToUpdateDoesNotExist() {
        final Player player = mock(Player.class);
        given(player.hasId()).willReturn(true);
        given(player.getId()).willReturn(ID);
        given(repository.existsById(ID)).willReturn(false);
        final String expectedMessage = String.format("player with id %d not found", ID);

        final Throwable thrown = catchThrowable(() -> service.update(player));

        assertThat(thrown).isInstanceOf(PlayerNotFoundException.class)
                .hasNoCause()
                .hasMessage(expectedMessage);
    }

    @Test
    public void shouldUpdatePlayer() {
        final Player player = mock(Player.class);
        final Player expectedPlayer = mock(Player.class);
        given(player.hasId()).willReturn(true);
        given(player.getId()).willReturn(ID);
        given(repository.existsById(ID)).willReturn(true);
        given(repository.save(player)).willReturn(expectedPlayer);

        final Player resultPlayer = service.update(player);

        assertThat(resultPlayer).isEqualTo(expectedPlayer);
    }

}
