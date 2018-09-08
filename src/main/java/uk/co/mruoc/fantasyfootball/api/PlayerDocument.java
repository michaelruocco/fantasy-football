package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Optional;

public class PlayerDocument implements JsonApiDocument {

    @NotNull
    @Valid
    private PlayerData data;

    public PlayerDocument() {
        // required by spring
    }

    private PlayerDocument(PlayerDocumentBuilder builder) {
        data = new PlayerData();
        data.id = builder.id;

        data.attributes = new PlayerAttributes();
        data.attributes.firstName = builder.firstName;
        data.attributes.lastName = builder.lastName;
        data.attributes.position = builder.position;
        data.attributes.value = builder.value;

        data.links = new Links();
        data.links.self = PlayerLinkBuilder.build(builder.id);

        if (!builder.hasClubId()) {
            return;
        }

        data.relationships = new PlayerRelationships();
        data.relationships.club = new Relation();
        data.relationships.club.links = new RelationLinks();
        data.relationships.club.links.related = ClubLinkBuilder.build(builder.clubId);
        data.relationships.club.data = new RelationData();
        data.relationships.club.data.type = "clubs";
        data.relationships.club.data.id = builder.clubId;
    }

    public PlayerData getData() {
        return data;
    }

    @JsonIgnore
    public Long getId() {
        return data.id;
    }

    @JsonIgnore
    public String getFirstName() {
        return data.attributes.firstName;
    }

    @JsonIgnore
    public String getLastName() {
        return data.attributes.lastName;
    }

    @JsonIgnore
    public String getPosition() {
        return data.attributes.position;
    }

    @JsonIgnore
    public Integer getValue() {
        return data.attributes.value;
    }

    @JsonIgnore
    public Optional<Long> getClubId() {
        return data.getClubId();
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
        public Optional<Long> getClubId() {
            if (hasClubId()) {
                return Optional.of(relationships.club.data.id);
            }
            return Optional.empty();
        }

        private boolean hasClubId() {
            return relationships != null &&
                    relationships.club != null &&
                    relationships.club.data != null;
        }

    }

    private static class PlayerAttributes {

        @NotNull
        @Length(max = 50)
        private String firstName;

        @NotNull
        @Length(max = 50)
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

        public boolean hasClubId() {
            return clubId != null;
        }

        public PlayerDocument build() {
            return new PlayerDocument(this);
        }

    }

}
