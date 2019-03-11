package joker.persona.ngrocken.kngdancetrack.model;

import java.util.Date;

public class Drill extends DanceConcept {

    public Drill(String name, long danceId, String danceName, String drill, int importance, int duration, String durationType) {
        super(0, name, danceId, danceName, new Date());
        this.importance = importance;
        this.count = 0;
        this.duration = duration;
        this.duration_type = durationType;
        this.drill = drill;
    }

    public Drill(long id, String name, long danceId, String danceName, String drill, int importance, int duration, String durationType) {
        super(id, name, danceId, danceName, new Date());
        this.importance = importance;
        this.count = 0;
        this.duration = duration;
        this.duration_type = durationType;
        this.drill = drill;
    }

    public Drill(long id, String name, long danceId, String danceName, long dateCreated, String drill, int importance, int duration, String durationType) {
        super(id, name, danceId, danceName, dateCreated);
        this.importance = importance;
        this.count = 0;
        this.duration = duration;
        this.duration_type = durationType;
        this.drill = drill;
    }

    private int count;
    private int importance;
    private int duration;
    private String duration_type;
    private String drill;

    public int getCount() {
        return count;
    }

    public void setCount(int count) { this.count = count; }

    public int getImportance() {
        return importance;
    }

    public String getDrill() {
        return drill;
    }

    public int getDuration() {
        return duration;
    }

    public String getDurationType() {
        return duration_type;
    }

    @Override
    public ConceptType getConceptType() {
        return ConceptType.DRILL;
    }
}