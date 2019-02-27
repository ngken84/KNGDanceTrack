package joker.persona.ngrocken.kngdancetrack.database.contracts;

import android.provider.BaseColumns;

public class NoteContract implements BaseColumns {

    public static final String TABLE_NAME = "dance_note_tbl";
    public static final String REF_ID = "ref_id";
    public static final String REF_TYPE = "ref_type";
    public static final String NOTE = "note";
    public static final String DATE_CREATED = "date_created";

    public static final String NOTE_TYPE_DRILL = "drill";
    public static final String NOTE_TYPE_MOVE = "move";

    public static String[] getFullProjection()  {
        String[] projection =  {_ID, REF_ID, REF_TYPE, NOTE, DATE_CREATED};
        return projection;
    }

    public static String getCreateDatabaseString() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                REF_ID + " INTEGER, " +
                REF_TYPE + " TEXT, " +
                NOTE + " TEXT, " +
                DATE_CREATED + " INTEGER)";
    }

    public static String createDeleteDatabaseString() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }


}
