package joker.persona.ngrocken.kngdancetrack.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class DanceConcept extends DanceObject{


    public enum ConceptType {
        DRILL, MOVE, SKILL
    }

    private long danceId;
    private String danceName;
    private List<String> tagList = new LinkedList<>();

    public DanceConcept(long id, String name, long dance, String danceName, Date dateCreated) {
        super(id, name, dateCreated);
        this.danceId = dance;
        this.danceName = danceName;
    }

    public DanceConcept(long id, String name, long dance, String danceName, int dateCreated) {
        super(id, name, dateCreated);
        this.danceId = dance;
        this.danceName = danceName;
    }

    public String getDanceName() {
        return danceName;
    }

    public long getDanceId() {
        return danceId;
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

    public String getTagListString() {
        StringBuilder sb = new StringBuilder();
        for(String tag : tagList) {
            sb.append(Tag.DELIMINATOR).append(tag).append(Tag.DELIMINATOR);
        }
        return sb.toString();
    }

    public void setTagListFromTagListString(String tagListString) {
        String[] split = tagListString.split(Tag.DELIMINATOR);
        for(String tag : split) {
            addTag(tag);
        }
    }

    public abstract ConceptType getConceptType();


}
