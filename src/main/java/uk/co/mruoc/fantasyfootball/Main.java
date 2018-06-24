package uk.co.mruoc.fantasyfootball;

public class Main {

    public static void main(String[] args) {
        Competition competition = new WorldCup2018();

        System.out.println("id: " + competition.getId());
        System.out.println("name: " + competition.getName());
        System.out.println("start date: " + competition.getStartDate());
        System.out.println("end date: " + competition.getEndDate());
        System.out.println();

        for (Team team : competition.getTeams()) {
            System.out.println("team id: " + team.getId() + " name: " + team.getName());
        }
        System.out.println();

        for(Fixture fixture : competition.getFixtures()) {
            System.out.println("fixture id: " + fixture.getId() + " " + fixture.getTeam1().getName() + " v " + fixture.getTeam2().getName() + " " + fixture.getKickOff());
        }
    }
}
