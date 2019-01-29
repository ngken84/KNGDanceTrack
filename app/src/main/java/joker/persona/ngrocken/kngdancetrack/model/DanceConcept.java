package joker.persona.ngrocken.kngdancetrack.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class DanceConcept extends DanceObject{

    private String dance;
    private List<String> tagList = new LinkedList<>();

    public DanceConcept(long id, String name, String dance, Date dateCreated) {
        super(id, name, dateCreated);
        this.dance = dance;
    }

    public String getDance() {
        return dance;
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
