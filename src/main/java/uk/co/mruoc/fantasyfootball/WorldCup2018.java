package uk.co.mruoc.fantasyfootball;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.UUID;

import static uk.co.mruoc.fantasyfootball.KickOffTimes.FIVE_PM;
import static uk.co.mruoc.fantasyfootball.KickOffTimes.FOUR_PM;
import static uk.co.mruoc.fantasyfootball.KickOffTimes.NINE_PM;
import static uk.co.mruoc.fantasyfootball.KickOffTimes.ONE_PM;
import static uk.co.mruoc.fantasyfootball.KickOffTimes.SEVEN_PM;
import static uk.co.mruoc.fantasyfootball.KickOffTimes.SIX_PM;

public class WorldCup2018 implements Competition {

    private static final int YEAR = 2018;

    private static final UUID ID = UUID.randomUUID();
    private static final Teams TEAMS = createTeams();
    private static final Fixtures FIXTURES = createFixtures();

    @Override
    public UUID getId() {
        return ID;
    }

    @Override
    public String getName() {
        return "2018 FIFA World Cup Russia";
    }

    public Teams getTeams() {
        return TEAMS;
    }

    @Override
    public Fixtures getFixtures() {
        return FIXTURES;
    }

    @Override
    public LocalDate getStartDate() {
        return FIXTURES.getStartDate();
    }

    @Override
    public LocalDate getEndDate() {
        return FIXTURES.getEndDate();
    }

    private static Teams createTeams() {
        return new Teams(Arrays.asList(
                createTeam("Russia"),
                createTeam("Saudi Arabia"),
                createTeam("Egypt"),
                createTeam("Uruguay"),
                createTeam("Morocco"),
                createTeam("IR Iran"),
                createTeam("Portugal"),
                createTeam("Spain"),
                createTeam("France"),
                createTeam("Australia"),
                createTeam("Argentina"),
                createTeam("Iceland"),
                createTeam("Peru"),
                createTeam("Denmark"),
                createTeam("Croatia"),
                createTeam("Nigeria"),
                createTeam("Costa Rica"),
                createTeam("Serbia"),
                createTeam("Germany"),
                createTeam("Mexico"),
                createTeam("Brazil"),
                createTeam("Switzerland"),
                createTeam("Sweden"),
                createTeam("Korea Republic"),
                createTeam("Belgium"),
                createTeam("Panama"),
                createTeam("Tunisia"),
                createTeam("England"),
                createTeam("Columbia"),
                createTeam("Japan"),
                createTeam("Poland"),
                createTeam("Senegal")
        ));
    }

    private static Team createTeam(String name) {
        return new Team.TeamBuilder()
                .setName(name)
                .build();
    }

    private static Fixtures createFixtures() {
        return new Fixtures(Arrays.asList(
                createFixture("Russia", "Saudi Arabia", buildKickOff(6, 14, SIX_PM)),

                createFixture("Egypt", "Uruguay", buildKickOff(6, 15, FIVE_PM)),
                createFixture("Morocco", "IR Iran", buildKickOff(6, 15, SIX_PM)),
                createFixture("Portugal", "Spain", buildKickOff(6, 15, NINE_PM)),

                createFixture("France", "Australia", buildKickOff(6, 16, ONE_PM)),
                createFixture("Argentina", "Iceland", buildKickOff(6, 16, FOUR_PM)),
                createFixture("Peru", "Denmark", buildKickOff(6, 16, SEVEN_PM)),
                createFixture("Croatia", "Nigeria", buildKickOff(6, 16, NINE_PM)),

                createFixture("Costa Rica", "Serbia", buildKickOff(6, 17, FOUR_PM)),
                createFixture("Germany", "Mexico", buildKickOff(6, 17, SIX_PM)),
                createFixture("Brazil", "Switzerland", buildKickOff(6, 17, NINE_PM))
        ));
    }

    private static Fixture createFixture(String team1, String team2, LocalDateTime kickOff) {
        return new Fixture.FixtureBuilder()
                .setTeam1(TEAMS.get(team1))
                .setTeam2(TEAMS.get(team2))
                .setKickOff(kickOff)
                .build();
    }

    private static LocalDateTime buildKickOff(int month, int dayOfMonth, LocalTime time) {
        return LocalDateTime.of(LocalDate.of(YEAR, month, dayOfMonth), time);
    }

}
