package uk.co.mruoc.fantasyfootball;

import java.util.UUID;

public class Team {

    private final UUID id;
    private final String name;

    public Team(TeamBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static class TeamBuilder {

        private UUID id = UUID.randomUUID();
        private String name;

        public TeamBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public TeamBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public Team build() {
            return new Team(this);
        }

    }

}
