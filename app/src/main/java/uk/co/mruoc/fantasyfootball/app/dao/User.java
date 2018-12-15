package uk.co.mruoc.fantasyfootball.app.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "user", indexes = {@Index(name = "email_index",  columnList="email", unique = true)})
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String nickname;
    private String picture;
    private UserType type;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public boolean hasId() {
        return id != null;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public boolean hasName() {
        return name != null;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public boolean hasNickname() {
        return nickname != null;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(final String picture) {
        this.picture = picture;
    }

    public boolean hasPicture() {
        return picture != null;
    }

    public UserType getType() {
        return type;
    }

    public void setType(final UserType type) {
        this.type = type;
    }

    public boolean hasType() {
        return type != null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public boolean hasEmail() {
        return email != null;
    }

    public boolean isAdmin() {
        return UserType.ADMIN.equals(type);
    }

    public void update(User user) {
        if (user.hasName()) {
            setName(user.getName());
        }
        if (user.hasNickname()) {
            setNickname(user.getNickname());
        }
        if (user.hasType()) {
            setType(user.getType());
        }
        if (user.hasPicture()) {
            setPicture(user.getPicture());
        }
        if (user.hasEmail()) {
            setEmail(user.getEmail());
        }
    }

}
