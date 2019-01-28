package joker.persona.ngrocken.kngdancetrack.database.contracts;

import android.provider.BaseColumns;

public class DanceMoveContract implements BaseColumns {

    private DanceMoveContract() {}

    public static final String TABLE_NAME = "dance_move_tbl";
    public static final String DANCE_ID = "dance_id";
    public static final String FB_ID = "fb_id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_RATING = "rating";
    public static final String COLUMN_NAME_DIFFICULTY = "difficulty";
    public static final String COLUMN_NAME_DATE_CREATED = "date_created";

    public static String getCreateDatabaseString() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                FB_ID + " TEXT, " +
                DANCE_ID + " INTEGER, " +
                COLUMN_NAME_NAME + " TEXT NOT NULL, " +
                COLUMN_NAME_DESCRIPTION + " TEXT, " +
                COLUMN_NAME_RATING + " INTEGER, " +
                COLUMN_NAME_DIFFICULTY + " INTEGER, " +
                COLUMN_NAME_DATE_CREATED + " DATE)";
    }

    public static String getDeleteDatabaseString() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
