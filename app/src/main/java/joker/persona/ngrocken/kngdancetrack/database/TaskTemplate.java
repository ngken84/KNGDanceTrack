package joker.persona.ngrocken.kngdancetrack.database;

import android.content.Context;
import android.os.AsyncTask;

public abstract class TaskTemplate {

    static abstract class MyAsyncTask<R, S, T> extends AsyncTask<R, S, T> {

        private final Context context;

        public MyAsyncTask(Context context) {
            this.context = context;
        }

        public Context getContext() {
            return context;
        }

    }
}
