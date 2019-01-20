package joker.persona.ngrocken.kngdancetrack.database.contracts;

import android.provider.BaseColumns;

public class DanceMoveContract {

    private DanceMoveContract() {}

    public static class DanceMoveEntry implements BaseColumns {
        public static final String TABLE_NAME = "dance_move_tbl";
        public static final String DANCE_ID = "dance_id";
        public static final String FB_ID = "fb_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_DIFFICULTY = "difficulty";
        public static final String COLUMN_NAME_DATE_CREATED = "date_created";
    }

    public static String getCreateDatabaseString() {
        return "CREATE TABLE " + DanceMoveEntry.TABLE_NAME + " (" +
                DanceMoveEntry._ID + " INTEGER PRIMARY KEY, " +
                DanceMoveEntry.FB_ID + " TEXT, " +
                DanceMoveEntry.DANCE_ID + " INTEGER, " +
                DanceMoveEntry.COLUMN_NAME_NAME + " TEXT NOT NULL, " +
                DanceMoveEntry.COLUMN_NAME_DESCRIPTION + " TEXT, " +
                DanceMoveEntry.COLUMN_NAME_RATING + " INTEGER, " +
                DanceMoveEntry.COLUMN_NAME_DIFFICULTY + " INTEGER, " +
                DanceMoveEntry.COLUMN_NAME_DATE_CREATED + " DATE)";
    }

    public static String getDeleteDatabaseString() {
        return "DROP TABLE IF EXISTS " + DanceMoveEntry.TABLE_NAME;
    }
}
