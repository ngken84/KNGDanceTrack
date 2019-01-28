package joker.persona.ngrocken.kngdancetrack.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.util.Consumer;

import java.util.LinkedList;
import java.util.List;

import joker.persona.ngrocken.kngdancetrack.database.contracts.DanceContract;
import joker.persona.ngrocken.kngdancetrack.database.helpers.DanceSQLHelper;
import joker.persona.ngrocken.kngdancetrack.model.Dance;

public class DanceDBTasks {

    private DanceDBTasks() {}

    public static abstract class MyAsyncTask<R, S, T> extends AsyncTask<R, S, T> {

        private final Context context;

        public MyAsyncTask(Context context) {
            this.context = context;
        }

        public Context getContext() {
            return context;
        }

    }

    public static void getAllDances(Context context, final Consumer<List<Dance>> danceListConsumer) {
        class DanceListAsyncTask extends MyAsyncTask<Void, Void, List<Dance>> {

            public DanceListAsyncTask(Context context) {
                super(context);
            }

            @Override
            protected List<Dance> doInBackground(Void... voids) {
                DanceSQLHelper helper = new DanceSQLHelper(getContext());
                SQLiteDatabase db = helper.getReadableDatabase();

                String[] projection = DanceContract.getProjection();

                String sortOrder = DanceContract.COLUMN_NAME_NAME + " ASC";
                Cursor cursor = db.query(DanceContract.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        sortOrder);


                List<Dance> retList = new LinkedList<>();
                while(cursor.moveToNext()) {
                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(DanceContract._ID));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(DanceContract.COLUMN_NAME_NAME));
                    String category = cursor.getString(cursor.getColumnIndexOrThrow(DanceContract.COLUMN_NAME_CATEGORY));
                    String description = cursor.getString(cursor.getColumnIndexOrThrow(DanceContract.COLUMN_NAME_DESCRIPTION));
                    Dance dance = new Dance(id, name, category, description);
                    retList.add(dance);
                }
                db.close();
                return retList;
            }

            @Override
            protected void onPostExecute(List<Dance> dances) {
                danceListConsumer.accept(dances);
            }
        }

        DanceListAsyncTask task = new DanceListAsyncTask(context);
        task.execute();
    }

    public static void insertDance(Context context, final Consumer<Long> consumer, Dance... dances) {
        class InsertDanceAsyncTask extends MyAsyncTask<Dance, Void, Long> {

            public InsertDanceAsyncTask(Context context) {
                super(context);
            }

            @Override
            protected Long doInBackground(Dance... dances) {
                DanceSQLHelper helper = new DanceSQLHelper(getContext());
                SQLiteDatabase db = helper.getWritableDatabase();

                long rowId = 0;

                for(Dance dance : dances) {

                    String[] projection = {
                            DanceContract._ID,
                    };

                    String selection = "UPPER(" + DanceContract.COLUMN_NAME_NAME + ") = UPPER(?)";

                    String[] selectionArgs = {dance.getName()};

                    Cursor cursor = db.query(
                            DanceContract.TABLE_NAME,
                            projection,
                            selection,
                            selectionArgs,
                            null,
                            null,
                            null
                    );

                    if(cursor.moveToNext()) {
                        return -1l;
                    }

                    ContentValues values = new ContentValues();
                    values.put(DanceContract.COLUMN_NAME_NAME, dance.getName());
                    values.put(DanceContract.COLUMN_NAME_CATEGORY, dance.getCategory());
                    values.put(DanceContract.COLUMN_NAME_DESCRIPTION, dance.getDescription());
                    rowId = db.insert(DanceContract.TABLE_NAME, null, values);

                }
                db.close();
                return rowId;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                consumer.accept(aLong);
            }
        }
        InsertDanceAsyncTask task = new InsertDanceAsyncTask(context);
        task.execute(dances);
    }

    public static void getDanceById(Context context, final Consumer<Dance> consumer, long id)   {
        class GetDanceAsync extends MyAsyncTask<Long, Void, Dance> {

            public GetDanceAsync(Context context) {
                super(context);
            }

            @Override
            protected Dance doInBackground(Long... longs) {
                DanceSQLHelper helper = new DanceSQLHelper(getContext());
                SQLiteDatabase db = helper.getReadableDatabase();

                String[] projection = DanceContract.getProjection();
                String selection = DanceContract._ID + " = ? ";
                String[] selectionArgs = {Long.toString(longs[0])};

                Cursor cursor = db.query(DanceContract.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null);
                Dance dance = null;
                if(cursor.moveToNext()) {
                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(DanceContract._ID));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(DanceContract.COLUMN_NAME_NAME));
                    String category = cursor.getString(cursor.getColumnIndexOrThrow(DanceContract.COLUMN_NAME_CATEGORY));
                    String description = cursor.getString(cursor.getColumnIndexOrThrow(DanceContract.COLUMN_NAME_DESCRIPTION));
                    dance = new Dance(id, name, category, description);
                }
                db.close();
                return dance;
            }

            @Override
            protected void onPostExecute(Dance dance) {
                consumer.accept(dance);
            }
        }
        GetDanceAsync task = new GetDanceAsync(context);
        task.execute(id);
    }

}
