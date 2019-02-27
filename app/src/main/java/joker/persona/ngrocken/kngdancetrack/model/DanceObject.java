package joker.persona.ngrocken.kngdancetrack.model;

import java.util.Calendar;
import java.util.Date;

public abstract class DanceObject {

    private long id;
    private String name;
    private boolean starred;
    private Date dateCreated;

    public DanceObject(long id, String name, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.dateCreated = dateCreated;
    }

    public DanceObject(long id, String name, long dateCreated) {
        this.id = id;
        this.name = name;
        setLongDateCreated(dateCreated);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public long getLongDateCreated() {
        if(dateCreated != null) {
            return dateCreated.getTime();
        }
        return 0;
    }

    public void setLongDateCreated(long date) {
        dateCreated =  new Date(date);
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
