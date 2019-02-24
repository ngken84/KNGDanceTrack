package joker.persona.ngrocken.kngdancetrack;

import android.util.Log;

public class InsTestTemplate {

    public void logTitle(String title) {
        Log.d("===============", "===============");
        Log.d("# TITLE", title);
        Log.d("===============", "===============");
    }

    public void logMessage(String message) {
        Log.d("# Message", message);
    }


    protected void waitUntilFree() {
        for(int i = 0 ; i < 100; ++i) {
            try {
                Thread.sleep(15L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
