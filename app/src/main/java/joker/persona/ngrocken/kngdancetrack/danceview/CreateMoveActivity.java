package joker.persona.ngrocken.kngdancetrack.danceview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.danceview.fragments.CreateMoveFragment;
import joker.persona.ngrocken.kngdancetrack.model.Dance;
import joker.persona.ngrocken.kngdancetrack.util.ActivityTemplate;

public class CreateMoveActivity extends ActivityTemplate {

    private CreateMoveFragment createMoveFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_dance_move);

        createMoveFragment = new CreateMoveFragment();

        long danceId = getIntent().getLongExtra("danceId", 0L);
        String danceName = getIntent().getStringExtra("danceName");

        Dance dance = new Dance(danceId, danceName, null, null, null);

        createMoveFragment.setDance(dance);

        showFragment(R.id.create_dance_move_fragment_container, createMoveFragment);

    }
}
