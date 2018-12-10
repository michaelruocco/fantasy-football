package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.Valid;
import javax.validation.constraints.Email;
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
        data.attributes.firstName = builder.firstName;
        data.attributes.lastName = builder.lastName;
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
    public String getFirstName() {
        return data.getFirstName();
    }

    @JsonIgnore
    public String getLastName() {
        return data.getLastName();
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

    public static class UserData {

        @Email
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
        public String getFirstName() {
            return attributes.firstName;
        }

        @JsonIgnore
        public String getLastName() {
            return attributes.lastName;
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

        @NotNull
        private String firstName;

        @NotNull
        private String lastName;

        @NotNull
        @Pattern(regexp = "^(ADMIN|STANDARD)$")
        private String type;

        @Email
        @NotNull
        private String email;

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
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
        private String firstName;
        private String lastName;
        private String type;
        private String email;

        private String selfLink;

        public UserDocumentBuilder setId(final long id) {
            this.id = id;
            return this;
        }

        public UserDocumentBuilder setFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserDocumentBuilder setLastName(final String lastName) {
            this.lastName = lastName;
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
