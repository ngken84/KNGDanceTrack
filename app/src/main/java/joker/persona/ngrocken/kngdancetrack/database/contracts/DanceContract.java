package joker.persona.ngrocken.kngdancetrack.database.contracts;

import android.provider.BaseColumns;

public class DanceContract implements BaseColumns {

    private DanceContract() {}

    public static final String TABLE_NAME = "dance_table";
    public static final String FB_ID = "fb_id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_CATEGORY = "category";
    public static final String COLUMN_NAME_TAGS = "tags";
    public static final String COLUMN_NAME_DATE_CREATED = "date_created";
    public static final String COLUMN_NAME_STARRED = "stars";

    public static String getCreateDanceDatabase() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                FB_ID + " TEXT, " +
                COLUMN_NAME_NAME + " TEXT NOT NULL, " +
                COLUMN_NAME_DESCRIPTION + " TEXT, " +
                COLUMN_NAME_CATEGORY + " TEXT NOT NULL, " +
                COLUMN_NAME_TAGS + " TEXT NOT NULL, " +
                COLUMN_NAME_DATE_CREATED + " INT, " +
                COLUMN_NAME_STARRED + " INTEGER)";

    }

    public static String getDeleteDanceDatabase() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    public static String[] getProjection() {
        String[] projection = {
                DanceContract._ID,
                DanceContract.COLUMN_NAME_NAME,
                DanceContract.COLUMN_NAME_CATEGORY,
                DanceContract.COLUMN_NAME_DESCRIPTION,
                DanceContract.COLUMN_NAME_TAGS,
                DanceContract.COLUMN_NAME_DATE_CREATED,
                DanceContract.COLUMN_NAME_STARRED
        };
        return projection;
    }

}
