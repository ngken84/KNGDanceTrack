package joker.persona.ngrocken.kngdancetrack.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class DanceConcept {

    private String id;
    private String name;
    private String dance;
    private boolean starred;
    private Date dateCreated;
    private List<String> tagList = new LinkedList<>();

    public DanceConcept(String id, String name, String dance, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.dance = dance;
        this.dateCreated = dateCreated;
    }

    public void setId(String id) {
        this.id = id;
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

    public void addTag(Tag tag) {
        addTag(tag.getName());
    }

    public void addTag(String tag) {
        if(!tagList.contains(tag)) {
            tagList.add(tag);
        }
    }

    public void addTags(Tag... tags) {
        for(Tag tag : tags) {
            addTag(tag);
        }
    }

    public void addTags(String... tags) {
        for(String tag : tags) {
            addTag(tag);
        }
    }



}
