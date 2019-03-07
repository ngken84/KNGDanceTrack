package joker.persona.ngrocken.kngdancetrack.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import joker.persona.ngrocken.kngdancetrack.database.contracts.NoteContract;
import joker.persona.ngrocken.kngdancetrack.database.helpers.DanceSQLHelper;
import joker.persona.ngrocken.kngdancetrack.model.DanceNote;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

public class DanceNoteDBTasks extends TaskTemplate {

    private DanceNoteDBTasks() {}

    public static void insertNote(Context context, DanceNote note,
                                  final DanceConsumer<Long> consumer) {
        class InsertNoteTask extends MyAsyncTask<DanceNote, Void, Long> {

            private InsertNoteTask(Context context) {
                super(context);
            }

            @Override
            protected Long doInBackground(DanceNote... danceNotes) {
                if(danceNotes == null || danceNotes[0] == null) {
                    consumer.setError(ERROR_NULL_VALUE_PASSED);
                    return 0L;
                }

                DanceNote note = danceNotes[0];

                DanceSQLHelper helper = new DanceSQLHelper(getContext());
                SQLiteDatabase db = helper.getWritableDatabase();

                ContentValues cv = new ContentValues();
                cv.put(NoteContract.REF_ID, note.getRefId());
                cv.put(NoteContract.REF_TYPE, note.getRefType());
                cv.put(NoteContract.NOTE, note.getNote());
                cv.put(NoteContract.DATE_CREATED, note.getLongDateCreated());

                long rowId = db.insert(NoteContract.TABLE_NAME, null, cv);

                db.close();
                return rowId;
        }

            @Override
            protected void onPostExecute(Long aLong) {
                consumer.consume(aLong);
            }
        }
        InsertNoteTask task = new InsertNoteTask(context);
        task.execute(note);
    }

    public static void getAllNotesByIdType(Context context, long id, String type,
                                           final DanceConsumer<List<DanceNote>> consumer) {
        class GetNotesTask extends MyAsyncTask<Long, Void, List<DanceNote>> {

            private final String type;

            public GetNotesTask(Context context, String type) {
                super(context);
                this.type = type;
            }

            @Override
            protected List<DanceNote> doInBackground(Long... longs) {
                if(longs == null || type == null || longs[0] == null || longs[0] < 0L) {
                    consumer.setError(ERROR_NULL_VALUE_PASSED);
                    return null;
                }

                DanceSQLHelper helper = new DanceSQLHelper(getContext());
                SQLiteDatabase db = helper.getReadableDatabase();

                String[] projection = NoteContract.getFullProjection();
                String selection = NoteContract.REF_ID + " = ? AND " + NoteContract.REF_TYPE +
                        " = ?";

                String[] selectionArgs = {Long.toString(longs[0]), type};
                String orderBy = NoteContract.DATE_CREATED + " DESC";

                Cursor cursor = db.query(NoteContract.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        orderBy);

                List<DanceNote> retList = new LinkedList<>();

                while(cursor.moveToNext()) {
                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(NoteContract._ID));
                    long refId = cursor.getLong(cursor.getColumnIndexOrThrow(NoteContract.REF_ID));
                    String type = cursor.getString(cursor.getColumnIndexOrThrow(NoteContract.REF_TYPE));
                    String note = cursor.getString(cursor.getColumnIndexOrThrow(NoteContract.NOTE));
                    long date = cursor.getLong(cursor.getColumnIndexOrThrow(NoteContract.DATE_CREATED));

                    DanceNote newNote = new DanceNote(id, refId, type, note, new Date(date));
                    retList.add(newNote);
                }

                cursor.close();
                db.close();
                return retList;
            }

            @Override
            protected void onPostExecute(List<DanceNote> danceNotes) {
                consumer.consume(danceNotes);
            }
        }
        GetNotesTask task = new GetNotesTask(context, type);
        task.execute(id);



    }


}
