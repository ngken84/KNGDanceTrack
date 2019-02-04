package joker.persona.ngrocken.kngdancetrack.database.contracts;

import android.provider.BaseColumns;

public class TagContract implements BaseColumns {

    public static final String TABLE_NAME = "tag_table";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_DESCRIPTION = "description";

    public static String[] getFullProjection() {
        String[] projection =  {_ID, COLUMN_NAME_NAME, COLUMN_NAME_DESCRIPTION} ;
        return projection;
    }

    public static String getCreateDatabaseString() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME_NAME + " TEXT NOT NULL, " +
                COLUMN_NAME_DESCRIPTION + " TEXT)";
    }

    public static String createDeleteDatabaseString() {
        return "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
