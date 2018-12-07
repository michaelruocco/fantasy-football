package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LastPageCalculatorTest {

    @Test
    public void lastPageShouldBeZeroIfTotalPagesIsZero() {
        final int totalPages = -1;

        final int lastPage = LastPageCalculator.calculate(totalPages);

        assertThat(lastPage).isEqualTo(0);
    }

    @Test
    public void lastPageShouldBeZeroIfTotalPagesIsLessThanZero() {
        final int totalPages = 0;

        final int lastPage = LastPageCalculator.calculate(totalPages);

        assertThat(lastPage).isEqualTo(0);
    }

    @Test
    public void lastPageShouldTotalPagesMinusOne() {
        final int totalPages = 5;

        final int lastPage = LastPageCalculator.calculate(totalPages);

        assertThat(lastPage).isEqualTo(totalPages - 1);
    }
    
}
