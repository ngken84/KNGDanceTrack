package joker.persona.ngrocken.kngdancetrack.danceview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.danceview.fragments.CreateDrillFragment;
import joker.persona.ngrocken.kngdancetrack.util.ActivityTemplate;

public class CreateDrillActivity extends ActivityTemplate {

    private CreateDrillFragment createDrillFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_drill);

        createDrillFragment = new CreateDrillFragment();

        showFragment(R.id.create_drill_fragment_container, createDrillFragment);




    }
}
