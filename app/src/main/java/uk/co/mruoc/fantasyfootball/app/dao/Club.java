package uk.co.mruoc.fantasyfootball.app.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Club() {
        // required by spring data
    }

    public Club(Long id) {
        this(id, "");
    }

    public Club(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(long id) {
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
