package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ApiModel(value = "ClubDocument")
public class ClubDocument implements JsonApiDocument {

    @Valid
    private ClubData data;

    public ClubDocument() {
        // required by jackson
    }

    private ClubDocument(ClubDocumentBuilder builder) {
        data = new ClubData();
        data.id = builder.id;

        data.attributes = new ClubAttributes();
        data.attributes.name = builder.name;

        data.links = new Links();
        data.links.self = builder.selfLink;

        data.relationships = new ClubRelationships();
        data.relationships.players = new Relation();
        data.relationships.players.links = new RelatedLinks();
        data.relationships.players.links.related = builder.clubPlayersLink;
    }

    public ClubData getData() {
        return data;
    }

    @JsonIgnore
    public boolean hasId() {
        return data != null && data.id != null;
    }

    @JsonIgnore
    public Long getId() {
        return data.id;
    }

    @JsonIgnore
    public String getName() {
        return data.getName();
    }

    @Override
    @JsonIgnore
    public String getSelfLink() {
        return data.links.self;
    }

    @JsonIgnore
    public String getClubPlayersLink() {
        return data.relationships.players.links.related;
    }

    public static class ClubData {

        @Min(1)
        private Long id;

        @Pattern(regexp = "^clubs")
        private final String type = "clubs";

        @NotNull
        @Valid
        private ClubAttributes attributes;

        @Valid
        private ClubRelationships relationships;

        @Valid
        private Links links;

        public Long getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public ClubAttributes getAttributes() {
            return attributes;
        }

        public ClubRelationships getRelationships() {
            return relationships;
        }

        public Links getLinks() {
            return links;
        }

        @JsonIgnore
        public String getName() {
            return attributes.getName();
        }

    }

    public static class ClubAttributes {

        @NotNull
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

    public static class ClubRelationships {

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

        private String selfLink;
        private String clubPlayersLink;

        public ClubDocumentBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public ClubDocumentBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public ClubDocumentBuilder setSelfLink(String selfLink) {
            this.selfLink = selfLink;
            return this;
        }

        public ClubDocumentBuilder setClubPlayersLink(String clubPlayersLink) {
            this.clubPlayersLink = clubPlayersLink;
            return this;
        }

        public ClubDocument build() {
            return new ClubDocument(this);
        }

    }

}
