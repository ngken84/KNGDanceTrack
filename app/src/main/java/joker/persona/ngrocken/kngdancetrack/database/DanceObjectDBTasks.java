package joker.persona.ngrocken.kngdancetrack.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import joker.persona.ngrocken.kngdancetrack.database.contracts.DanceMoveContract;
import joker.persona.ngrocken.kngdancetrack.database.contracts.DrillContract;
import joker.persona.ngrocken.kngdancetrack.database.helpers.DanceSQLHelper;
import joker.persona.ngrocken.kngdancetrack.model.DanceConcept;
import joker.persona.ngrocken.kngdancetrack.model.Drill;
import joker.persona.ngrocken.kngdancetrack.model.Move;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

public class DanceObjectDBTasks extends TaskTemplate {

    private DanceObjectDBTasks() {}


    public static void getAllDanceConceptsForDance(Context context, long danceId, final DanceConsumer<List<DanceConcept>> consumer) {
        class GetAllDanceConcForDance extends MyAsyncTask<Long, Void, List<DanceConcept>> {

            private GetAllDanceConcForDance(Context context) {
                super(context);
            }

            @Override
            protected List<DanceConcept> doInBackground(Long... longs) {
                DanceSQLHelper helper = new DanceSQLHelper(getContext());
                SQLiteDatabase db = helper.getReadableDatabase();

                if(longs == null || longs[0] == null || longs[0] < 1) {
                    consumer.setError(ERROR_NULL_VALUE_PASSED);
                    return null;
                }

                List<DanceConcept> retList = new LinkedList<>();
                retList.addAll(getAllMovesFromDB(db, longs[0]));

                retList.addAll(getAllDrillsFromDB(db, longs[0]));

                return retList;
            }

            @Override
            protected void onPostExecute(List<DanceConcept> danceConcepts) {
                consumer.consume(danceConcepts);
            }
        }

        GetAllDanceConcForDance task = new GetAllDanceConcForDance(context);
        task.execute(danceId);
    }

    //DANCE MOVES
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
                values.put(DanceMoveContract.COLUMN_NAME_DATE_CREATED, move.getLongDateCreated());
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

                List<Move> retList = getAllMovesFromDB(db, longs[0]);

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

    private static List<Move> getAllMovesFromDB(SQLiteDatabase db, long danceId) {
        String selection = DanceMoveContract.DANCE_ID + " = ?";
        String[] selectionArgs = {Long.toString(danceId)};

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
            Move move = extractDanceMoveFromCursor(cursor);
            retList.add(move);
        }
        cursor.close();
        return retList;
    }

    private static Move extractDanceMoveFromCursor(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndexOrThrow(DanceMoveContract._ID));
        long danceId = cursor.getLong(cursor.getColumnIndexOrThrow(DanceMoveContract.DANCE_ID));
        String danceName = cursor.getString(cursor.getColumnIndexOrThrow(DanceMoveContract.DANCE_NAME));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_NAME));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_DESCRIPTION));
        String tags = cursor.getString(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_TAGS));
        boolean starred = cursor.getInt(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_STARRED)) == 1;
        int rating = cursor.getInt(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_RATING));
        int difficult = cursor.getInt(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_DIFFICULTY));
        long dateCreated = cursor.getLong(cursor.getColumnIndexOrThrow(DanceMoveContract.COLUMN_NAME_DATE_CREATED));

        Move move = new Move(id, name, danceId, danceName, dateCreated, description, rating, difficult);
        move.setStarred(starred);
        move.setTagListFromTagListString(tags);

        return move;
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
                    move = extractDanceMoveFromCursor(cursor);
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

    //DRILLS
    public static void insertDrill(Context context, Drill drill, final DanceConsumer<Long> consumer) {
        class InsertDrillTask extends MyAsyncTask<Drill, Void, Long> {

            private InsertDrillTask(Context context) {
                super(context);
            }

            @Override
            protected Long doInBackground(Drill... drills) {
                if(drills == null || drills[0] == null) {
                    consumer.setError(ERROR_NULL_VALUE_PASSED);
                    return 0L;
                }

                Drill drill = drills[0];
                if(drill.getName() != null && drill.getName().trim().equals("")) {
                    consumer.setError(ERROR_NULL_NAME_VALUE_PASSED);
                    return 0L;
                }

                drill.setName(drill.getName().trim());

                DanceSQLHelper helper = new DanceSQLHelper(getContext());
                SQLiteDatabase db = helper.getWritableDatabase();

                String[] projection = {DrillContract._ID};
                String selection = DrillContract.COLUMN_NAME_NAME + " = ? AND " + DrillContract.DANCE_ID + " = ?";
                String[] selectionArgs = {drill.getName(), Long.toString(drill.getDanceId())};

                Cursor cursor = db.query(DrillContract.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null);

                if(cursor.moveToNext()) {
                    consumer.setError(ERROR_INSERT_ALREADY_EXISTS);
                    cursor.close();
                    db.close();
                    return 0L;
                }
                cursor.close();

                ContentValues values = new ContentValues();
                values.put(DrillContract.COLUMN_NAME_NAME, drill.getName());
                values.put(DrillContract.DANCE_ID, drill.getDanceId());
                values.put(DrillContract.DANCE_NAME, drill.getDanceName());
                values.put(DrillContract.COLUMN_NAME_DRILL, drill.getDrill());
                values.put(DrillContract.COLUMN_NAME_DATE_CREATED, drill.getLongDateCreated());
                values.put(DrillContract.COLUMN_NAME_STARRED, false);
                values.put(DrillContract.COLUMN_NAME_IMPORTANCE, drill.getImportance());
                values.put(DrillContract.COLUMN_NAME_DURATION, drill.getDuration());
                values.put(DrillContract.COLUMN_NAME_DURATION_TYPE, drill.getDurationType());
                values.put(DrillContract.COLUMN_NAME_TAGS, "");
                values.put(DrillContract.COLUMN_NAME_COUNT, 0);

                long rowId = db.insert(DrillContract.TABLE_NAME, null, values);
                db.close();
                return rowId;
            }

            @Override
            protected void onPostExecute(Long aLong) {
                consumer.consume(aLong);
            }
        }
        InsertDrillTask task = new InsertDrillTask(context);
        task.execute(drill);
    }

    public static void getDrillsForDanceId(Context context, long danceId, final DanceConsumer<List<Drill>> consumer) {
        class GetDrillTask extends MyAsyncTask<Long, Void, List<Drill>> {

            private GetDrillTask(Context context) {
                super(context);
            }

            @Override
            protected List<Drill> doInBackground(Long... longs) {
                if(longs == null || longs[0] == null) {
                    consumer.setError(ERROR_NULL_VALUE_PASSED);
                    return null;
                }

                DanceSQLHelper sql = new DanceSQLHelper(getContext());
                SQLiteDatabase db = sql.getReadableDatabase();

                List<Drill> retList = getAllDrillsFromDB(db, longs[0]);
                db.close();

                return retList;
            }

            @Override
            protected void onPostExecute(List<Drill> drills) {
                consumer.consume(drills);
            }
        }
        GetDrillTask task = new GetDrillTask(context);
        task.execute(danceId);

    }

    private static List<Drill> getAllDrillsFromDB(SQLiteDatabase db, long danceId) {
        List<Drill> retList = new LinkedList<>();

        String[] projection = DrillContract.getFullProjection();
        String selection = DrillContract.DANCE_ID + " = ?";
        String[] selectionArgs = {Long.toString(danceId)};
        String sortOrder = DrillContract.COLUMN_NAME_DRILL + " ASC";

        Cursor cursor = db.query(DrillContract.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        while(cursor.moveToNext()) {
            Drill drill = extractDrillFromCursor(cursor);
            retList.add(drill);
        }

        cursor.close();
        return retList;
    }

    private static Drill extractDrillFromCursor(Cursor cursor) {
        long drillId = cursor.getLong(cursor.getColumnIndexOrThrow(DrillContract._ID));
        String drillName = cursor.getString(cursor.getColumnIndexOrThrow(DrillContract.COLUMN_NAME_NAME));
        long danceId = cursor.getLong(cursor.getColumnIndexOrThrow(DrillContract.DANCE_ID));
        String danceName = cursor.getString(cursor.getColumnIndexOrThrow(DrillContract.DANCE_NAME));
        String drillText = cursor.getString(cursor.getColumnIndexOrThrow(DrillContract.COLUMN_NAME_DRILL));
        long date = cursor.getLong(cursor.getColumnIndexOrThrow(DrillContract.COLUMN_NAME_DATE_CREATED));
        int importance = cursor.getInt(cursor.getColumnIndexOrThrow(DrillContract.COLUMN_NAME_IMPORTANCE));
        int count = cursor.getInt(cursor.getColumnIndexOrThrow(DrillContract.COLUMN_NAME_COUNT));
        String tags = cursor.getString(cursor.getColumnIndexOrThrow(DrillContract.COLUMN_NAME_TAGS));
        boolean starred = cursor.getInt(cursor.getColumnIndexOrThrow(DrillContract.COLUMN_NAME_STARRED)) == 1;
        int duration = cursor.getInt(cursor.getColumnIndexOrThrow(DrillContract.COLUMN_NAME_DURATION));
        String durationType = cursor.getString(cursor.getColumnIndexOrThrow(DrillContract.COLUMN_NAME_DURATION_TYPE));
        Drill drill = new Drill(drillId, drillName, danceId, danceName, date, drillText, importance, duration, durationType);
        drill.setTagListFromTagListString(tags);
        drill.setStarred(starred);
        drill.setCount(count);
        return drill;
    }

    public static void getDrillById(Context context, long drillId, final DanceConsumer<Drill> consumer) {
        class DrillIdTask extends MyAsyncTask<Long, Void, Drill> {

            public DrillIdTask(Context context) {
                super(context);
            }

            @Override
            protected Drill doInBackground(Long... longs) {
                if(longs == null || longs[0] == null || longs[0] < 1) {
                    consumer.setError(ERROR_NULL_VALUE_PASSED);
                    return null;
                }

                DanceSQLHelper helper = new DanceSQLHelper(getContext());
                SQLiteDatabase db = helper.getReadableDatabase();

                String[] projection = DrillContract.getFullProjection();
                String selection = DrillContract._ID + " = ?";
                String[] selectionArgs = { Long.toString(longs[0])};

                Cursor cursor = db.query(
                        DrillContract.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null
                );

                Drill drill = null;

                if(cursor.moveToNext()) {
                    drill = extractDrillFromCursor(cursor);
                }

                cursor.close();
                db.close();
                return drill;
            }

            @Override
            protected void onPostExecute(Drill drill) {
                consumer.consume(drill);
            }
        }
        DrillIdTask task = new DrillIdTask(context);
        task.execute(drillId);
    }


}
