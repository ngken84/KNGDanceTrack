package joker.persona.ngrocken.kngdancetrack.model;


public class Dance {

    private long id;
    private String name;
    private String category;
    private String description;

    public Dance(long id, String name, String category, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() { return description; }
}


