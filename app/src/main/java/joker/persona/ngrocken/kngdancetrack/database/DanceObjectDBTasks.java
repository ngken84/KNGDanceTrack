package joker.persona.ngrocken.kngdancetrack.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import joker.persona.ngrocken.kngdancetrack.database.contracts.DanceMoveContract;
import joker.persona.ngrocken.kngdancetrack.database.helpers.DanceSQLHelper;
import joker.persona.ngrocken.kngdancetrack.model.Move;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

public class DanceObjectDBTasks extends TaskTemplate {

    private DanceObjectDBTasks() {}

    public static void insertDanceMove(Context context, Move move, final DanceConsumer<Long> consumer) {
        class InsertDanceMoveTask extends MyAsyncTask<Move, Void, Long> {

            private InsertDanceMoveTask(Context context) {
                super(context);
            }

            @Override
            protected Long doInBackground(Move... moves) {
                if(moves == null || moves[0] == null) {
                    consumer.setError("Dance move pass is null");
                    return 0L;
                }
                Move move = moves[0];

                DanceSQLHelper helper = new DanceSQLHelper(getContext());
                SQLiteDatabase db = helper.getWritableDatabase();

                String[] projection = {DanceMoveContract._ID};

                String selection = DanceMoveContract.DANCE_ID + " = ? AND " +
                        "UPPER(" + DanceMoveContract.COLUMN_NAME_NAME + ") = UPPER(?)";
                String[] selectionArgs = {Long.toString(move.getDanceId()), move.getName()};

                Cursor cursor = db.query(DanceMoveContract.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null);

                if(cursor.moveToNext()) {
                    consumer.setError("Dance move already exists.");
                    cursor.close();
                    db.close();
                    return 0L;
                }

                cursor.close();

                ContentValues values = new ContentValues();
                values.put(DanceMoveContract.COLUMN_NAME_NAME, move.getName());
                values.put(DanceMoveContract.COLUMN_NAME_DESCRIPTION, move.getDescription());
                values.put(DanceMoveContract.COLUMN_NAME_DIFFICULTY, move.getDifficulty());
                values.put(DanceMoveContract.DANCE_ID, move.getDanceId());
                values.put(DanceMoveContract.DANCE_NAME, move.getDanceName());
                values.put(DanceMoveContract.COLUMN_NAME_DIFFICULTY, move.getDifficulty());
                values.put(DanceMoveContract.COLUMN_NAME_STARRED, move.isStarred()? 1 : 0);
                values.put(DanceMoveContract.COLUMN_NAME_DATE_CREATED, move.getIntDateCreated());
                values.put(DanceMoveContract.COLUMN_NAME_TAGS, "");

                long rowId = db.insert(DanceMoveContract.TABLE_NAME, null, values);

                db.close();
                return rowId;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                consumer.consume(aLong);
            }
        }
        InsertDanceMoveTask task = new InsertDanceMoveTask(context);
        task.execute(move);
    }

    public static void getDanceMovesForDanceId(Context context, long danceId, final DanceConsumer<List<Move>> consumer) {
        class GetDanceMove extends MyAsyncTask<Long, Void, List<Move>> {

            public GetDanceMove(Context context) {
                super(context);
            }

            @Override
            protected List<Move> doInBackground(Long... longs) {
                DanceSQLHelper helper = new DanceSQLHelper(getContext());
                SQLiteDatabase db = helper.getReadableDatabase();

                String selection = DanceMoveContract.DANCE_ID + " = ?";
                String[] selectionArgs = {Long.toString(longs[0])};

                String sortOrder = DanceMoveContract.COLUMN_NAME_NAME + " ASC";

                Cursor cursor = db.query(DanceMoveContract.TABLE_NAME,
                        DanceMoveContract.getFullProjection(),
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                List<Move> retList = new LinkedList<>();

                while(cursor.moveToNext()) {
                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(DanceMoveContract._ID));
                    long danceId = cursor.getLong(cursor.getColumnIndexOrThrow(DanceMoveContract.DANCE_ID));
                    String danceName = cursor.getString(cursor.getColumnIndexOrThrow(DanceMoveContract.DANCE_NAME));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_NAME));
                    String description = cursor.getString(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_DESCRIPTION));
                    String tags = cursor.getString(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_TAGS));
                    boolean starred = cursor.getInt(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_STARRED)) == 1;
                    int rating = cursor.getInt(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_RATING));
                    int difficult = cursor.getInt(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_DIFFICULTY));
                    int dateCreated = cursor.getInt(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_DATE_CREATED));

                    Move move = new Move(id, name, danceId, danceName, dateCreated, description, rating, difficult);
                    move.setStarred(starred);
                    move.setTagListFromTagListString(tags);

                    retList.add(move);
                }

                cursor.close();
                db.close();

                return retList;
            }

            @Override
            protected void onPostExecute(List<Move> moves) {
                consumer.accept(moves);
            }
        }
        GetDanceMove task = new GetDanceMove(context);
        task.execute(danceId);
    }

    public static void getDanceMoveById(Context context, long danceMoveId, final DanceConsumer<Move> consumer) {
        class GetDanceMoveTask extends MyAsyncTask<Long, Void, Move> {

            public GetDanceMoveTask(Context context) {
                super(context);
            }

            @Override
            protected Move doInBackground(Long... longs) {
                if(longs == null || longs[0] == null) {
                    consumer.setError("No ID passed");
                    return null;
                }

                long id = longs[0];

                DanceSQLHelper helper = new DanceSQLHelper(getContext());
                SQLiteDatabase db = helper.getReadableDatabase();

                String[] projection = DanceMoveContract.getFullProjection();
                String selection = DanceMoveContract._ID + " = ?";
                String selectionArgs[] = {Long.toString(id)};

                Cursor cursor = db.query(DanceMoveContract.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null);

                Move move = null;

                if(cursor.moveToNext()) {
                    long danceId = cursor.getLong(cursor.getColumnIndexOrThrow(DanceMoveContract.DANCE_ID));
                    String danceName = cursor.getString(cursor.getColumnIndexOrThrow(DanceMoveContract.DANCE_NAME));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_NAME));
                    String description = cursor.getString(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_DESCRIPTION));
                    String tags = cursor.getString(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_TAGS));
                    boolean starred = cursor.getInt(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_STARRED)) == 1;
                    int rating = cursor.getInt(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_RATING));
                    int difficult = cursor.getInt(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_DIFFICULTY));
                    int dateCreated = cursor.getInt(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_DATE_CREATED));

                    move = new Move(id, name, danceId, danceName, dateCreated, description, rating, difficult);
                    move.setStarred(starred);
                    move.setTagListFromTagListString(tags);
                }

                cursor.close();
                db.close();
                return move;
            }

            @Override
            protected void onPostExecute(Move move) {
                consumer.consume(move);
            }
        }
        GetDanceMoveTask task = new GetDanceMoveTask(context);
        task.execute(danceMoveId);
    }

}
