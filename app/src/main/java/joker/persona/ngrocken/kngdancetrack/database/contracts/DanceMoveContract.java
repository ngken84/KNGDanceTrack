package joker.persona.ngrocken.kngdancetrack.database.contracts;

import android.provider.BaseColumns;

public class DanceMoveContract implements BaseColumns {

    private DanceMoveContract() {}

    public static final String TABLE_NAME = "dance_move_tbl";
    public static final String DANCE_ID = "dance_id";
    public static final String DANCE_NAME = "dance_name";
    public static final String FB_ID = "fb_id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_RATING = "rating";
    public static final String COLUMN_NAME_DIFFICULTY = "difficulty";
    public static final String COLUMN_NAME_DATE_CREATED = "date_created";
    public static final String COLUMN_NAME_TAGS = "tags";
    public static final String COLUMN_NAME_STARRED = "starred";

    public static String getCreateDatabaseString() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                FB_ID + " TEXT, " +
                DANCE_ID + " INTEGER, " +
                DANCE_NAME + " TEXT, " +
                COLUMN_NAME_NAME + " TEXT NOT NULL, " +
                COLUMN_NAME_DESCRIPTION + " TEXT, " +
                COLUMN_NAME_RATING + " INTEGER, " +
                COLUMN_NAME_DIFFICULTY + " INTEGER, " +
                COLUMN_NAME_TAGS + " TEXT, " +
                COLUMN_NAME_STARRED + " INTEGER, " +
                COLUMN_NAME_DATE_CREATED + " DATE)";
    }

    public static String getDeleteDatabaseString() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static String[] getFullProjection() {
        String[] projection = {
                _ID,
                FB_ID,
                DANCE_ID,
                DANCE_NAME,
                COLUMN_NAME_NAME,
                COLUMN_NAME_DESCRIPTION,
                COLUMN_NAME_RATING,
                COLUMN_NAME_DIFFICULTY,
                COLUMN_NAME_TAGS,
                COLUMN_NAME_STARRED,
                COLUMN_NAME_DATE_CREATED
        };
        return projection;
    }
}
