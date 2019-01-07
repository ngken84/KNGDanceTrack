package joker.persona.ngrocken.kngdancetrack.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Move extends DanceConcept {

    public Move(String id, String name, String dance, Date dateCreated, String description,
                int count, int rating, int difficulty, String location) {
        super(id, name, dance, dateCreated);
        this.description = description;
        this.count = count;
        this.rating = rating;
        this.difficulty = difficulty;
        this.location = location;
    }

    private String description;
    private int count;
    private int rating;
    private int difficulty;
    private String location;
    private List<MoveVariation> variations = new LinkedList<MoveVariation>();

    public String getDescription() {
        return description;
    }

    public int getCount() {
        return count;
    }

    public int getRating() {
        return rating;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getLocation() {
        return location;
    }
}


