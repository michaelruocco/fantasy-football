package uk.co.mruoc.fantasyfootball.app.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "club")
public class Club {

    @Id
    private Long id;
    private String name;

    public Club() {
        // required by spring data
    }

    public Club(final Long id) {
        this(id, "");
    }

    public Club(final Long id, final String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public boolean hasId() {
        return id != null;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
