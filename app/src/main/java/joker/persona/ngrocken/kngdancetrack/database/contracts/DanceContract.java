package joker.persona.ngrocken.kngdancetrack.database.contracts;

import android.provider.BaseColumns;

public class DanceContract implements BaseColumns {

    private DanceContract() {}

    public static final String TABLE_NAME = "dance_table";
    public static final String FB_ID = "fb_id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_CATEGORY = "category";

    public static String getCreateDanceDatabase() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                FB_ID + " TEXT, " +
                COLUMN_NAME_NAME + " TEXT NOT NULL, " +
                COLUMN_NAME_DESCRIPTION + " TEXT, " +
                COLUMN_NAME_CATEGORY + " TEXT NOT NULL)";
    }

    public static String getDeleteDanceDatabase() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static String[] getProjection() {
        String[] projection = {
                DanceContract._ID,
                DanceContract.COLUMN_NAME_NAME,
                DanceContract.COLUMN_NAME_CATEGORY,
                DanceContract.COLUMN_NAME_DESCRIPTION
        };
        return projection;
    }

}
