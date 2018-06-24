package uk.co.mruoc.fantasyfootball;

import java.time.LocalDate;
import java.util.UUID;

public interface Competition {

    UUID getId();

    String getName();

    Teams getTeams();

    Fixtures getFixtures();

    LocalDate getStartDate();

    LocalDate getEndDate();

}
