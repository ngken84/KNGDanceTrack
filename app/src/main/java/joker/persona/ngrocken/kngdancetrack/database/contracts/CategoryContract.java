package joker.persona.ngrocken.kngdancetrack.database.contracts;

import android.provider.BaseColumns;

public class CategoryContract implements BaseColumns {

    public static final String TABLE_NAME = "category_table";
    public static final String FB_ID = "fb_id";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_DESCRIPTION = "description";

    public static String getCreateCategoryDatabase() {
        return "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY, " +
                FB_ID + " TEXT, " +
                COLUMN_NAME_NAME + " TEXT NOT NULL, " +
                COLUMN_NAME_DESCRIPTION + " TEXT)";
    }

}
