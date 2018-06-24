package uk.co.mruoc.fantasyfootball;

import org.junit.Test;

import java.time.LocalTime;

import static org.assertj.core.api.Assertions.assertThat;

public class KickOffTimesTest {

    @Test
    public void onePmIsCorrect() {
        assertThat(KickOffTimes.ONE_PM).isEqualTo(LocalTime.of(13, 0));
    }

    @Test
    public void threePmIsCorrect() {
        assertThat(KickOffTimes.THREE_PM).isEqualTo(LocalTime.of(15, 0));
    }

    @Test
    public void fourPmIsCorrect() {
        assertThat(KickOffTimes.FOUR_PM).isEqualTo(LocalTime.of(16, 0));
    }

    @Test
    public void fivePmIsCorrect() {
        assertThat(KickOffTimes.FIVE_PM).isEqualTo(LocalTime.of(17, 0));
    }

    @Test
    public void sixPmIsCorrect() {
        assertThat(KickOffTimes.SIX_PM).isEqualTo(LocalTime.of(18, 0));
    }

    @Test
    public void sevenPmIsCorrect() {
        assertThat(KickOffTimes.SEVEN_PM).isEqualTo(LocalTime.of(19, 0));
    }

    @Test
    public void eightPmIsCorrect() {
        assertThat(KickOffTimes.EIGHT_PM).isEqualTo(LocalTime.of(20, 0));
    }

    @Test
    public void ninePmIsCorrect() {
        assertThat(KickOffTimes.NINE_PM).isEqualTo(LocalTime.of(21, 0));
    }

}
