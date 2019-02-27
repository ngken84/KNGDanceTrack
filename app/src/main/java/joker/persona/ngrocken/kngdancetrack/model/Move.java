package joker.persona.ngrocken.kngdancetrack.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Move extends DanceConcept {

    public Move(String name, long danceId, String danceName, String description) {
        super(0, name, danceId, danceName, new Date());
        this.description = description;
    }


    public Move(long id, String name, long danceId, String danceName, Date dateCreated, String description) {
        super(id, name, danceId, danceName, dateCreated);
        this.description = description;
    }

    public Move(long id, String name, long danceId, String danceName, Date dateCreated, String description, int rating, int difficulty) {
        super(id, name, danceId, danceName, dateCreated);
        this.description = description;
        this.rating = rating;
        this.difficulty = difficulty;
    }

    public Move(long id, String name, long danceId, String danceName, int dateCreated, String description) {
        super(id, name, danceId, danceName, dateCreated);
        this.description = description;
    }

    public Move(long id, String name, long danceId, String danceName, int dateCreated, String description, int rating, int difficulty) {
        super(id, name, danceId, danceName, dateCreated);
        this.description = description;
        this.rating = rating;
        this.difficulty = difficulty;
    }

    private String description;
    private int rating;
    private int difficulty;
    private List<MoveVariation> variations = new LinkedList<MoveVariation>();

    public String getDescription() {
        return description;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public ConceptType getConceptType() {
        return ConceptType.MOVE;
    }
}


