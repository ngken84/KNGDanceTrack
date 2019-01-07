package joker.persona.ngrocken.kngdancetrack;

import android.os.Bundle;
import android.support.annotation.Nullable;

import joker.persona.ngrocken.kngdancetrack.util.ActivityTemplate;

public class DanceActivity extends ActivityTemplate {

    private DanceViewFragment danceViewFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dance);

        danceViewFragment = new DanceViewFragment();
        showFragment(R.id.dance_fragment_container, danceViewFragment);


    }
}
