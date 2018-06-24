package uk.co.mruoc.fantasyfootball;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Fixture {

    private final UUID id;
    private final Team team1;
    private final Team team2;
    private final LocalDateTime kickOff;

    public Fixture(FixtureBuilder builder) {
        this.id = builder.id;
        this.team1 = builder.team1;
        this.team2 = builder.team2;
        this.kickOff = builder.kickOff;
    }

    public UUID getId() {
        return id;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public LocalDateTime getKickOff() {
        return kickOff;
    }

    public LocalDate getDate() {
        return kickOff.toLocalDate();
    }

    public static class FixtureBuilder {

        private UUID id = UUID.randomUUID();
        private Team team1;
        private Team team2;
        private LocalDateTime kickOff;

        public FixtureBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public FixtureBuilder setTeam1(Team team1) {
            this.team1 = team1;
            return this;
        }

        public FixtureBuilder setTeam2(Team team2) {
            this.team2 = team2;
            return this;
        }

        public FixtureBuilder setKickOff(LocalDateTime kickOff) {
            this.kickOff = kickOff;
            return this;
        }

        public Fixture build() {
            return new Fixture(this);
        }

    }

}
