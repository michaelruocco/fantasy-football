package uk.co.mruoc.fantasyfootball;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.Fixture.FixtureBuilder;

import java.util.Arrays;
import java.util.Iterator;

import static java.time.LocalDateTime.*;
import static org.assertj.core.api.Assertions.assertThat;

public class FixturesTest {

    private final Fixture fixture1 = new FixtureBuilder().setKickOff(now()).build();
    private final Fixture fixture2 = new FixtureBuilder().setKickOff(now().plusHours(2)).build();
    private final Fixture fixture3 = new FixtureBuilder().setKickOff(now().plusHours(4)).build();

    private final Fixtures fixtures = new Fixtures(Arrays.asList(fixture1, fixture2, fixture3));

    @Test
    public void shouldBeIterableAndInInsertionOrder() {
        Iterator<Fixture> iterator = fixtures.iterator();

        assertThat(iterator.next()).isEqualTo(fixture1);
        assertThat(iterator.next()).isEqualTo(fixture2);
        assertThat(iterator.next()).isEqualTo(fixture3);
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void shouldReturnFirstFixtureDate() {
        assertThat(fixtures.getStartDate()).isEqualTo(fixture1.getDate());
    }

    @Test
    public void shouldReturnLastFixtureDate() {
        assertThat(fixtures.getEndDate()).isEqualTo(fixture3.getDate());
    }

}
