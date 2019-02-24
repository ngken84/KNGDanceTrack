package joker.persona.ngrocken.kngdancetrack.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

import joker.persona.ngrocken.kngdancetrack.database.contracts.CategoryContract;
import joker.persona.ngrocken.kngdancetrack.database.contracts.DanceContract;
import joker.persona.ngrocken.kngdancetrack.database.helpers.DanceSQLHelper;
import joker.persona.ngrocken.kngdancetrack.model.Category;
import joker.persona.ngrocken.kngdancetrack.model.Dance;
import joker.persona.ngrocken.kngdancetrack.model.Tag;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

public class DanceDBTasks extends TaskTemplate{

    private DanceDBTasks() {}

    public static void getAllDances(Context context, final DanceConsumer<List<Dance>> danceListConsumer) {
        class DanceListAsyncTask extends MyAsyncTask<Void, Void, List<Dance>> {

            private DanceListAsyncTask(Context context) {
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
                    Dance dance = new Dance(id, name, category, description,  0);
                    retList.add(dance);
                }
                cursor.close();
                db.close();
                return retList;
            }

            @Override
            protected void onPostExecute(List<Dance> dances) {
                danceListConsumer.consume(dances);
            }
        }

        DanceListAsyncTask task = new DanceListAsyncTask(context);
        task.execute();
    }

    public static void insertDance(Context context, final DanceConsumer<Long> consumer, Dance... dances) {
        class InsertDanceAsyncTask extends MyAsyncTask<Dance, Void, Long> {

            private InsertDanceAsyncTask(Context context) {
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
                        cursor.close();
                        db.close();
                        consumer.setError("Dance name already in use.");
                        return -1L;
                    }

                    ContentValues values = new ContentValues();
                    values.put(DanceContract.COLUMN_NAME_NAME, dance.getName());
                    values.put(DanceContract.COLUMN_NAME_CATEGORY, dance.getCategory());
                    values.put(DanceContract.COLUMN_NAME_DESCRIPTION, dance.getDescription());
                    values.put(DanceContract.COLUMN_NAME_STARRED, 0);
                    values.put(DanceContract.COLUMN_NAME_TAGS, "");
                    values.put(DanceContract.COLUMN_NAME_DATE_CREATED, dance.getIntDateCreated());
                    rowId = db.insert(DanceContract.TABLE_NAME, null, values);
                    cursor.close();
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

    public static void getDanceById(Context context, final DanceConsumer<Dance> consumer, long id)   {
        class GetDanceAsync extends MyAsyncTask<Long, Void, Dance> {

            private GetDanceAsync(Context context) {
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
                    int date = cursor.getInt(cursor.getColumnIndexOrThrow(DanceContract.COLUMN_NAME_DATE_CREATED));
                    boolean starred = cursor.getInt(cursor.getColumnIndexOrThrow(DanceContract.COLUMN_NAME_STARRED)) == 1;
                    dance = new Dance(id, name, category, description, date);
                    dance.setStarred(starred);
                }
                cursor.close();
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

    public static void clearDanceTable(Context context, final DanceConsumer<Void> consumer) {
        class ClearDanceTask extends MyAsyncTask<Void, Void, Void> {

            private ClearDanceTask(Context context) {
                super(context);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                DanceSQLHelper helper = new DanceSQLHelper(getContext());
                SQLiteDatabase db =  helper.getWritableDatabase();

                db.execSQL("DELETE FROM " + DanceContract.TABLE_NAME);

                db.close();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                consumer.consume(aVoid);
            }
        }
        ClearDanceTask task = new ClearDanceTask(context);
        task.execute();
    }

    public static void getCategories(Context context, final DanceConsumer<List<String>> consumer) {
        class GetCategoriesAsync extends MyAsyncTask<Void, Void, List<String>> {

            private GetCategoriesAsync(Context context) {
                super(context);
            }

            @Override
            protected List<String> doInBackground(Void... voids) {
                DanceSQLHelper helper = new DanceSQLHelper(getContext());
                SQLiteDatabase db = helper.getReadableDatabase();

                String[] projection = {CategoryContract.COLUMN_NAME_NAME};

                String sortOrder = CategoryContract.COLUMN_NAME_NAME + " ASC";
                Cursor cursor = db.query(CategoryContract.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        sortOrder);


                List<String> retList = new LinkedList<>();
                while(cursor.moveToNext()) {
                    retList.add(cursor.getString(cursor.getColumnIndexOrThrow(CategoryContract.COLUMN_NAME_NAME)));
                }
                retList.add("No Category");

                cursor.close();
                db.close();
                return retList;
            }

            @Override
            protected void onPostExecute(List<String> strings) {
                consumer.accept(strings);
            }
        }

        GetCategoriesAsync task = new GetCategoriesAsync(context);
        task.execute();
    }

    public static void insertCategory(Context context, final DanceConsumer<Long> consumer, Category category) {
        class InsertCategoryAsync extends MyAsyncTask<Category, Void, Long> {

            private InsertCategoryAsync(Context context) {
                super(context);
            }


            @Override
            protected Long doInBackground(Category... categories) {
                if(categories == null || categories[0] == null) {
                    consumer.setError(ERROR_NULL_VALUE_PASSED);
                    return 0L;
                }
                long rowId = 0;
                Category category = categories[0];

                if(category.getName() == null || category.getName().trim().length() == 0) {
                    consumer.setError(ERROR_NULL_NAME_VALUE_PASSED);
                    return 0L;
                }

                category.setName(category.getName().trim());

                if("No Category".equals(category.getName())) {
                    consumer.setError("Category must not be named \"No Category\"");
                    return 0L;
                }

                DanceSQLHelper helper = new DanceSQLHelper(getContext());
                SQLiteDatabase db = helper.getWritableDatabase();

                String[] projection = {
                        CategoryContract._ID,
                };

                String selection = CategoryContract.COLUMN_NAME_NAME + " = ?";
                String[] selectionArgs = {category.getName()};

                Cursor cursor = db.query(CategoryContract.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null);

                if(cursor.moveToNext()) {
                    cursor.close();
                    db.close();
                    return 0L;
                }

                cursor.close();

                ContentValues cv = new ContentValues();
                cv.put(CategoryContract.COLUMN_NAME_NAME, category.getName());
                cv.put(CategoryContract.COLUMN_NAME_DESCRIPTION, category.getDescription());
                rowId = db.insert(CategoryContract.TABLE_NAME, null, cv);

                db.close();
                return rowId;

            }


            @Override
            protected void onPostExecute(Long aLong) {
                consumer.consume(aLong);
            }
        }
        InsertCategoryAsync task = new InsertCategoryAsync(context);
        task.execute(category);
    }

    public static void insertTag(Context context, final DanceConsumer<Long> consumer, Tag tag) {
        class InsertTagTask extends MyAsyncTask<Tag, Void, Long> {

            private InsertTagTask(Context context) {
                super(context);
            }

            @Override
            protected Long doInBackground(Tag... tags) {
                return null;
            }
        }
    }

    public static void getAllTags(Context context, final DanceConsumer<List<Tag>> consumer) {
        class GetTagsTask extends MyAsyncTask<Void, Void, List<Tag>> {

            public GetTagsTask(Context context) {
                super(context);
            }

            @Override
            protected List<Tag> doInBackground(Void... voids) {
                return null;
            }
        }
    }

}
