package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class PlayerDocument implements JsonApiDocument {

    @NotNull
    @Valid
    private PlayerData data;

    public PlayerDocument() {
        // required by jackson
    }

    public PlayerDocument(PlayerDocumentBuilder builder) {
        data = new PlayerData();
        data.id = builder.id;

        data.attributes = new PlayerAttributes();
        data.attributes.firstName = builder.firstName;
        data.attributes.lastName = builder.lastName;
        data.attributes.position = builder.position;
        data.attributes.value = builder.value;

        data.links = new Links();
        data.links.self = builder.selfLink;

        if (!builder.hasClubId()) {
            return;
        }

        data.relationships = new PlayerRelationships();
        data.relationships.club = new Relation();
        data.relationships.club.links = new RelationLinks();
        data.relationships.club.links.related = builder.clubLink;
        data.relationships.club.data = new RelationData();
        data.relationships.club.data.type = "clubs";
        data.relationships.club.data.id = builder.clubId;
    }

    public PlayerData getData() {
        return data;
    }

    @JsonIgnore
    public boolean hasId() {
        return data != null && data.id != null;
    }

    @JsonIgnore
    public Long getId() {
        return hasId() ? data.id : null;
    }

    @JsonIgnore
    public String getFirstName() {
        return data.getFirstName();
    }

    @JsonIgnore
    public String getLastName() {
        return data.getLastName();
    }

    @JsonIgnore
    public String getPosition() {
        return data.getPosition();
    }

    @JsonIgnore
    public Integer getValue() {
        return data.getValue();
    }

    @JsonIgnore
    public Long getClubId() {
        return data == null ? null : data.getClubId();
    }

    @JsonIgnore
    public boolean hasClub() {
        return data != null && data.hasClubId();
    }

    @Override
    @JsonIgnore
    public String getSelfLink() {
        return data.links.self;
    }

    public static class PlayerData {

        @Min(1)
        private Long id;

        @Pattern(regexp = "^players$")
        private final String type = "players";

        @NotNull
        @Valid
        private PlayerAttributes attributes;

        @Valid
        private PlayerRelationships relationships;

        @Valid
        private Links links;

        public Long getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public PlayerAttributes getAttributes() {
            return attributes;
        }

        public PlayerRelationships getRelationships() {
            return relationships;
        }

        public Links getLinks() {
            return links;
        }

        @JsonIgnore
        public String getFirstName() {
            return attributes.firstName;
        }

        @JsonIgnore
        public String getLastName() {
            return attributes.lastName;
        }

        @JsonIgnore
        public String getPosition() {
            return attributes.position;
        }

        @JsonIgnore
        public Integer getValue() {
            return attributes.value;
        }

        @JsonIgnore
        public Long getClubId() {
            return hasClubId() ? relationships.club.data.id : null;
        }

        private boolean hasClubId() {
            return relationships != null &&
                    relationships.club != null &&
                    relationships.club.data != null;
        }

    }

    private static class PlayerAttributes {

        @NotNull
        private String firstName;

        @NotNull
        private String lastName;

        @NotNull
        @Pattern(regexp = "^(GOALKEEPER|DEFENDER|MIDFIELDER|STRIKER)$")
        private String position;

        @NotNull
        private Integer value;

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getPosition() {
            return position;
        }

        public Integer getValue() {
            return value;
        }

    }

    public static class Links {

        private String self;

        public String getSelf() {
            return self;
        }

    }

    public static class PlayerRelationships {

        @Valid
        private Relation club;

        public Relation getClub() {
            return club;
        }

    }

    public static class Relation {

        private RelationLinks links;

        private RelationData data;

        public RelationLinks getLinks() {
            return links;
        }

        public RelationData getData() {
            return data;
        }

    }

    public static class RelationLinks {

        private String related;

        public String getRelated() {
            return related;
        }

    }

    public static class RelationData {

        private String type;
        private long id;

        public String getType() {
            return type;
        }

        public long getId() {
            return id;
        }

    }

    public static class PlayerDocumentBuilder {

        private Long id;
        private String firstName;
        private String lastName;
        private String position;
        private Integer value;
        private Long clubId;

        private String selfLink;
        private String clubLink;

        public PlayerDocumentBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public PlayerDocumentBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public PlayerDocumentBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PlayerDocumentBuilder setPosition(String position) {
            this.position = position;
            return this;
        }

        public PlayerDocumentBuilder setValue(int value) {
            this.value = value;
            return this;
        }

        public PlayerDocumentBuilder setClubId(Long clubId) {
            this.clubId = clubId;
            return this;
        }

        public PlayerDocumentBuilder setSelfLink(String selfLink) {
            this.selfLink = selfLink;
            return this;
        }

        public PlayerDocumentBuilder setClubLink(String clubLink) {
            this.clubLink = clubLink;
            return this;
        }

        public boolean hasClubId() {
            return clubId != null;
        }

        public PlayerDocument build() {
            return new PlayerDocument(this);
        }

    }

}
