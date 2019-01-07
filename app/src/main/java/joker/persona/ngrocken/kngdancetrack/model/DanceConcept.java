package joker.persona.ngrocken.kngdancetrack.model;

import java.util.Date;

public abstract class DanceConcept {

    private String id;
    private String name;
    private String dance;
    private boolean starred;
    private Date dateCreated;

    public DanceConcept(String id, String name, String dance, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.dance = dance;
        this.dateCreated = dateCreated;
    }

    public String getName() {
        return name;
    }

    public String getDance() {
        return dance;
    }

    public String getId() {
        return id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }
}
