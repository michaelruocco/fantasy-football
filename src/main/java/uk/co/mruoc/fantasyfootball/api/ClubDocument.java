package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class ClubDocument implements JsonApiDocument {

    @Valid
    private Data data;

    public ClubDocument() {
        // required by spring
    }

    private ClubDocument(ClubDocumentBuilder builder) {
        data = new Data();
        data.id = builder.id;

        data.attributes = new Attributes();
        data.attributes.name = builder.name;

        data.links = new Links();
        data.links.self = builder.buildSelfLink();

        data.relationships = new Relationships();
        data.relationships.players = new Relation();
        data.relationships.players.links = new RelatedLinks();
        data.relationships.players.links.related = builder.buildClubPlayersLink();
    }

    public Data getData() {
        return data;
    }

    @JsonIgnore
    public Long getId() {
        return data.id;
    }

    @JsonIgnore
    public String getName() {
        return data.attributes.name;
    }

    @Override
    @JsonIgnore
    public String getSelfLink() {
        return data.links.self;
    }

    private static class Data {

        @Min(1)
        private Long id;

        @Pattern(regexp = "^clubs")
        private final String type = "clubs";

        @NotNull
        @Valid
        private Attributes attributes;

        @Valid
        private Relationships relationships;

        @Valid
        private Links links;

        public Long getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public Attributes getAttributes() {
            return attributes;
        }

        public Relationships getRelationships() {
            return relationships;
        }

        public Links getLinks() {
            return links;
        }

    }

    public static class Attributes {

        @NotNull
        @Length(max = 50)
        private String name;

        public String getName() {
            return name;
        }

    }

    public static class Links {

        private String self;

        public String getSelf() {
            return self;
        }

    }

    public static class Relationships {

        @Valid
        private Relation players;

        public Relation getPlayers() {
            return players;
        }

    }

    public static class Relation {

        @Valid
        private RelatedLinks links;

        public RelatedLinks getLinks() {
            return links;
        }

    }

    public static class RelatedLinks {

        private String related;

        public String getRelated() {
            return related;
        }

    }

    public static class ClubDocumentBuilder {

        private Long id;
        private String name;
        private int playersPageSize;
        private int playersPageNumber;

        public ClubDocumentBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public ClubDocumentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ClubDocumentBuilder setPlayersPageSize(int playersPageSize) {
            this.playersPageSize = playersPageSize;
            return this;
        }

        public ClubDocumentBuilder setPlayersPageNumber(int playersPageNumber) {
            this.playersPageNumber = playersPageNumber;
            return this;
        }

        public ClubDocument build() {
            return new ClubDocument(this);
        }

        private String buildSelfLink() {
            return ClubLinkBuilder.build(id);
        }

        private String buildClubPlayersLink() {
            return ClubPlayersLinkBuilder.build(id, playersPageNumber, playersPageSize);
        }

    }

}
