package joker.persona.ngrocken.kngdancetrack.danceview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Date;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.danceview.fragments.CreateDrillFragment;
import joker.persona.ngrocken.kngdancetrack.model.Dance;
import joker.persona.ngrocken.kngdancetrack.util.ActivityTemplate;

public class CreateDrillActivity extends ActivityTemplate {

    private CreateDrillFragment createDrillFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_drill);

        createDrillFragment = new CreateDrillFragment();

        long danceId = getIntent().getLongExtra("danceId", 0L);
        String danceName = getIntent().getStringExtra("danceName");

        Dance dance = new Dance(danceId, danceName, "", "", new Date());
        createDrillFragment.setDance(dance);

        showFragment(R.id.create_drill_fragment_container, createDrillFragment);




    }
}
