package joker.persona.ngrocken.kngdancetrack.database.contracts;

import android.provider.BaseColumns;

public class DrillContract implements BaseColumns {

    public static final String TABLE_NAME = "drill_table";
    public static final String FB_ID = "fb_id";
    public static final String DANCE_ID = "dance_id";
    public static final String DANCE_NAME = "dance_name";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_DRILL = "drill";
    public static final String COLUMN_NAME_DURATION = "duration";
    public static final String COLUMN_NAME_DURATION_TYPE = "duration_type";
    public static final String COLUMN_NAME_TAGS = "tags";
    public static final String COLUMN_NAME_DATE_CREATED = "date_created";
    public static final String COLUMN_NAME_STARRED = "stars";
    public static final String COLUMN_NAME_IMPORTANCE = "importance";
    public static final String COLUMN_NAME_COUNT = "count";

    public static String[] getFullProjection() {
        String[] retval = {
                _ID,
                FB_ID,
                DANCE_ID,
                DANCE_NAME,
                COLUMN_NAME_NAME,
                COLUMN_NAME_DRILL,
                COLUMN_NAME_TAGS,
                COLUMN_NAME_DURATION,
                COLUMN_NAME_DURATION_TYPE,
                COLUMN_NAME_DATE_CREATED,
                COLUMN_NAME_STARRED,
                COLUMN_NAME_IMPORTANCE,
                COLUMN_NAME_COUNT
        };
        return retval;
    }

    public static String getCreateDrillDatabase() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                FB_ID + " TEXT, " +
                DANCE_ID + " INTEGER, " +
                DANCE_NAME + " TEXT, " +
                COLUMN_NAME_NAME + " TEXT NOT NULL, " +
                COLUMN_NAME_DRILL + " TEXT, " +
                COLUMN_NAME_DURATION + " INTEGER, " +
                COLUMN_NAME_DURATION_TYPE + " TEXT, " +
                COLUMN_NAME_TAGS + " TEXT NOT NULL, " +
                COLUMN_NAME_DATE_CREATED + " INT, " +
                COLUMN_NAME_IMPORTANCE + " INT, " +
                COLUMN_NAME_COUNT + " INT, " +
                COLUMN_NAME_STARRED + " INTEGER)";
    }

    public static String getDeleteDrilLDatabase() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
