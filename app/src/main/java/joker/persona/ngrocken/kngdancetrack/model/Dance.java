package joker.persona.ngrocken.kngdancetrack.model;


public class Dance {

    private String id;
    private String name;
    private String category;

    public Dance(String id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
}


