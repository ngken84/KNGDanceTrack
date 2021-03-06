package joker.persona.ngrocken.kngdancetrack.model;


import java.util.Date;

public class Dance extends DanceObject {

    private String category;
    private String description;

    public Dance(String name, String category, String description) {
        super(0, name, new Date());
        this.category = category;
        this.description = description;
    }

    public Dance(long id, String name, String category, String description, Date dateCreated) {
        super(id, name, dateCreated);
        this.category = category;
        this.description = description;
    }

    public Dance(long id, String name, String category, String description, long dateCreated) {
        super(id, name, dateCreated);
        this.category = category;
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() { return description; }
}


