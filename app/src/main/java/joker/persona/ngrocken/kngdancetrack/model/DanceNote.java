package joker.persona.ngrocken.kngdancetrack.model;

import java.util.Date;

public class DanceNote extends DanceObject {

    public DanceNote(long refId, String refType, String note) {
        super(0L, null, new Date());
        this.refId = refId;
        this.refType = refType;
        this.note = note;
    }

    public DanceNote(long id, long refId, String refType, String note, Date dateCreated) {
        super(id, null, dateCreated);
        this.refId = refId;
        this.refType = refType;
        this.note = note;
    }

    private String note;
    private long refId;
    private String refType;

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
