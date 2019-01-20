package joker.persona.ngrocken.kngdancetrack.database.contracts;

import android.provider.BaseColumns;

public class DanceContract {

    private DanceContract() {}

    public static class DanceEntry implements BaseColumns {
        static final String TABLE_NAME = "dance_table";
        static final String FB_ID = "fb_id";
        static final String COLUMN_NAME_NAME = "name";
        static final String COLUMN_NAME_DESCRIPTION = "description";
        static final String COLUMN_NAME_CATEGORY = "category";
    }

    public static String getCreateDanceDatabase() {
        return "CREATE TABLE " + DanceEntry.TABLE_NAME + " (" +
                DanceEntry._ID + " INTEGER PRIMARY KEY, " +
                DanceEntry.FB_ID + " TEXT, " +
                DanceEntry.COLUMN_NAME_NAME + " TEXT NOT NULL, " +
                DanceEntry.COLUMN_NAME_DESCRIPTION + " TEXT, " +
                DanceEntry.COLUMN_NAME_CATEGORY + " TEXT NOT NULL)";
    }

    public static String getDeleteDanceDatabase() {
        return "DROP TABLE IF EXISTS " + DanceEntry.TABLE_NAME;
    }

}
