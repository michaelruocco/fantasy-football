package uk.co.mruoc.fantasyfootball;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Teams implements Iterable<Team> {

    private final Map<String, Team> teams = new TreeMap<>();

    public Teams(List<Team> teamList) {
        teamList.forEach(this::add);
    }

    @Override
    public Iterator<Team> iterator() {
        return teams.values().iterator();
    }

    public Team get(String name) {
        if (teams.containsKey(name)) {
            return teams.get(name);
        }
        throw new TeamNotFoundException(name);
    }

    private void add(Team team) {
        teams.put(team.getName(), team);
    }

}
