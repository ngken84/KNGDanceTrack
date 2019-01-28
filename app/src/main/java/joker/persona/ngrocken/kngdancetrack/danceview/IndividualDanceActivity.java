package joker.persona.ngrocken.kngdancetrack.danceview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.danceview.fragments.IndividualDanceViewFragment;
import joker.persona.ngrocken.kngdancetrack.util.ActivityTemplate;

public class IndividualDanceActivity extends ActivityTemplate {



    private IndividualDanceViewFragment indFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_individual_dance);

        indFragment = new IndividualDanceViewFragment();
        showFragment(R.id.individual_dance_view_container, indFragment);

    }
}
