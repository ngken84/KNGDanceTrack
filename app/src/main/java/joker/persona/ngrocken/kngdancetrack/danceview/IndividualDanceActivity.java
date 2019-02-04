package joker.persona.ngrocken.kngdancetrack.danceview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Consumer;
import android.widget.Button;
import android.widget.Toast;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.danceview.fragments.IndividualDanceViewFragment;
import joker.persona.ngrocken.kngdancetrack.database.DanceDBTasks;
import joker.persona.ngrocken.kngdancetrack.model.Dance;
import joker.persona.ngrocken.kngdancetrack.util.ActivityTemplate;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

public class IndividualDanceActivity extends ActivityTemplate {

    private IndividualDanceViewFragment indFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_individual_dance);

        long danceId = getIntent().getLongExtra("danceId", 0L);
        indFragment = new IndividualDanceViewFragment();
        showFragment(R.id.individual_dance_view_container, indFragment);

        DanceDBTasks.getDanceById(this, new DanceConsumer<Dance>() {
            @Override
            public void consume(Dance dance) {
                indFragment.setDance(dance);
            }

            @Override
            public void handleError() {

            }
        }, danceId);
    }
}
