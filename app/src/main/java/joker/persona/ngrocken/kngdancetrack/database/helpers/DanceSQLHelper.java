package joker.persona.ngrocken.kngdancetrack.database.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import joker.persona.ngrocken.kngdancetrack.database.contracts.CategoryContract;
import joker.persona.ngrocken.kngdancetrack.database.contracts.DanceContract;
import joker.persona.ngrocken.kngdancetrack.database.contracts.DanceMoveContract;
import joker.persona.ngrocken.kngdancetrack.database.contracts.DrillContract;
import joker.persona.ngrocken.kngdancetrack.database.contracts.NoteContract;
import joker.persona.ngrocken.kngdancetrack.database.contracts.TagContract;

public class DanceSQLHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Dance.db";

    public DanceSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DanceContract.getCreateDanceDatabase());
        sqLiteDatabase.execSQL(DanceMoveContract.getCreateDatabaseString());
        sqLiteDatabase.execSQL(CategoryContract.getCreateCategoryDatabase());
        sqLiteDatabase.execSQL(TagContract.getCreateDatabaseString());
        sqLiteDatabase.execSQL(DrillContract.getCreateDrillDatabase());
        sqLiteDatabase.execSQL(NoteContract.getCreateDatabaseString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DanceContract.getDeleteDanceDatabase());
        sqLiteDatabase.execSQL(DanceContract.getCreateDanceDatabase());

        sqLiteDatabase.execSQL(DanceMoveContract.getDeleteDatabaseString());
        sqLiteDatabase.execSQL(DanceMoveContract.getCreateDatabaseString());

        sqLiteDatabase.execSQL(CategoryContract.getDeleteTableSQL());
        sqLiteDatabase.execSQL(CategoryContract.getCreateCategoryDatabase());

        sqLiteDatabase.execSQL(TagContract.createDeleteDatabaseString());
        sqLiteDatabase.execSQL(TagContract.getCreateDatabaseString());

        sqLiteDatabase.execSQL(DrillContract.getDeleteDrilLDatabase());
        sqLiteDatabase.execSQL(DrillContract.getCreateDrillDatabase());

        sqLiteDatabase.execSQL(NoteContract.getDeleteDatabaseString());
        sqLiteDatabase.execSQL(NoteContract.getCreateDatabaseString());

    }
}
