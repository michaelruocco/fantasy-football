package uk.co.mruoc.fantasyfootball;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Fixtures implements Iterable<Fixture> {

    private final List<Fixture> fixtures;

    public Fixtures(List<Fixture> fixtures) {
        this.fixtures = Collections.unmodifiableList(fixtures);
    }

    public LocalDate getStartDate() {
        Fixture firstFixture = getFirstFixture();
        return firstFixture.getDate();
    }

    public LocalDate getEndDate() {
        Fixture lastFixture = getLastFixture();
        return lastFixture.getDate();
    }

    @Override
    public Iterator<Fixture> iterator() {
        return fixtures.iterator();
    }

    private Fixture getFirstFixture() {
        return fixtures.get(0);
    }

    private Fixture getLastFixture() {
        return fixtures.get(fixtures.size() - 1);
    }

}
