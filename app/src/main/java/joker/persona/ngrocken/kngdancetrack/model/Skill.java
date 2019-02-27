package joker.persona.ngrocken.kngdancetrack.model;

import java.util.Date;

public class Skill extends DanceConcept {

    private int importance;
    private int skillLevel;

    public Skill(String name, long danceId, String danceName) {
        super(0, name, danceId, danceName, new Date());
    }

    public Skill(long id, String name, long dance, String danceName, Date dateCreated) {
        super(id, name, dance, danceName, dateCreated);
    }

    public Skill(long id, String name, long dance, String danceName, int dateCreated) {
        super(id, name, dance, danceName, dateCreated);
    }

    @Override
    public ConceptType getConceptType() {
        return ConceptType.SKILL;
    }
}
