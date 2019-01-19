package joker.persona.ngrocken.kngdancetrack.database.contracts;

import android.provider.BaseColumns;

public class DanceContract {

    private DanceContract() {}

    public static class DanceEntry implements BaseColumns {
        public static final String TABLE_NAME = "dance_table";
        public static final String _ID = "id";
        public static final String FB_ID = "fb_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }

}
