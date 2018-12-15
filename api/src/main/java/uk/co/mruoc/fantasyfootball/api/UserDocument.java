package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserDocument implements JsonApiDocument {

    @NotNull
    @Valid
    private UserData data;

    public UserDocument() {
        // required by jackson
    }

    public UserDocument(UserDocumentBuilder builder) {
        data = new UserData();
        data.id = builder.id;

        data.attributes = new UserAttributes();
        data.attributes.name = builder.name;
        data.attributes.nickname = builder.nickname;
        data.attributes.picture = builder.picture;
        data.attributes.type = builder.type;
        data.attributes.email = builder.email;

        data.links = new Links();
        data.links.self = builder.selfLink;
    }

    public UserData getData() {
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
    public String getName() {
        return data.getName();
    }

    @JsonIgnore
    public String getNickname() {
        return data.getNickname();
    }

    @JsonIgnore
    public String getPicture() {
        return data.getPicture();
    }

    @JsonIgnore
    public String getUserType() {
        return data.getUserType();
    }

    @JsonIgnore
    public String getEmail() {
        return data.getEmail();
    }

    @Override
    @JsonIgnore
    public String getSelfLink() {
        return data.links.self;
    }

    @JsonIgnore
    public boolean hasEmail() {
        return data != null && data.getEmail() != null;
    }

    public static class UserData {

        @Min(1)
        private Long id;

        @Pattern(regexp = "^users")
        private final String type = "users";

        @NotNull
        @Valid
        private UserAttributes attributes;

        @Valid
        private Links links;

        public Long getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public UserAttributes getAttributes() {
            return attributes;
        }

        public Links getLinks() {
            return links;
        }

        @JsonIgnore
        public String getName() {
            return attributes.name;
        }

        @JsonIgnore
        public String getNickname() {
            return attributes.nickname;
        }

        @JsonIgnore
        public String getPicture() {
            return attributes.picture;
        }

        @JsonIgnore
        public String getUserType() {
            return attributes.type;
        }

        @JsonIgnore
        public String getEmail() {
            return attributes.email;
        }

    }

    private static class UserAttributes {

        private String name;

        private String nickname;

        private String picture;

        @Pattern(regexp = "^(ADMIN|STANDARD)$")
        private String type;

        @Email
        private String email;

        public String getName() {
            return name;
        }

        public String getNickname() {
            return nickname;
        }

        public String getPicture() {
            return picture;
        }

        public String getType() {
            return type;
        }

        public String getEmail() {
            return email;
        }

    }

    public static class Links {

        private String self;

        public String getSelf() {
            return self;
        }

    }

    public static class UserDocumentBuilder {

        private Long id;
        private String name;
        private String nickname;
        private String picture;
        private String type;
        private String email;

        private String selfLink;

        public UserDocumentBuilder setId(final long id) {
            this.id = id;
            return this;
        }

        public UserDocumentBuilder setName(final String name) {
            this.name = name;
            return this;
        }

        public UserDocumentBuilder setNickname(final String nickname) {
            this.nickname = nickname;
            return this;
        }

        public UserDocumentBuilder setPicture(final String picture) {
            this.picture = picture;
            return this;
        }

        public UserDocumentBuilder setType(final String type) {
            this.type = type;
            return this;
        }

        public UserDocumentBuilder setEmail(final String email) {
            this.email = email;
            return this;
        }

        public UserDocumentBuilder setSelfLink(final String selfLink) {
            this.selfLink = selfLink;
            return this;
        }

        public UserDocument build() {
            return new UserDocument(this);
        }

    }

}
