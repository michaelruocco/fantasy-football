package uk.co.mruoc.fantasyfootball.app.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "player")
public class Player {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private Position position;
    private int value;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean hasId() {
        return id != null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Long getClubId() {
        return hasClub() ? club.getId() : null;
    }

    public boolean hasClub() {
        return club != null;
    }

    public String getPositionName() {
        return position.name();
    }

}
