package joker.persona.ngrocken.kngdancetrack.model;

import java.util.Date;

public class Drill extends DanceConcept {

    public Drill(long id, String name, long danceId, String danceName, Date dateCreated, int count, String description) {
        super(id, name, danceId, danceName, dateCreated);
        this.count = count;
        this.description = description;
    }

    private int count;
    private String description;

}