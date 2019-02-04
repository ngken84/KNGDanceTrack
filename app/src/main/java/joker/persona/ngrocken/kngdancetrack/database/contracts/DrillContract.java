package joker.persona.ngrocken.kngdancetrack.database.contracts;

import android.provider.BaseColumns;

public class DrillContract implements BaseColumns {

    public static final String TABLE_NAME = "drill_table";
    public static final String FB_ID = "fb_id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_DRILL = "drill";
    public static final String COLUMN_NAME_TAGS = "tags";
    public static final String COLUMN_NAME_DATE_CREATED = "date_created";
    public static final String COLUMN_NAME_STARRED = "stars";

    public static String getCreateDrillDatabase() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                FB_ID + " TEXT, " +
                COLUMN_NAME_NAME + " TEXT NOT NULL, " +
                COLUMN_NAME_DRILL + " TEXT, " +
                COLUMN_NAME_TAGS + " TEXT NOT NULL, " +
                COLUMN_NAME_DATE_CREATED + " INT, " +
                COLUMN_NAME_STARRED + " INTEGER)";
    }
}
