package joker.persona.ngrocken.kngdancetrack.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import joker.persona.ngrocken.kngdancetrack.R;

public abstract class ActivityTemplate extends AppCompatActivity {

    protected void showFragment(int layoutId, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(fragment.isAdded()) {
            return;
        }
        transaction.replace(layoutId, fragment);
        transaction.commit();
    }
}
