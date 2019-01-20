package joker.persona.ngrocken.kngdancetrack.danceview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.danceview.fragments.CreateDanceFragment;
import joker.persona.ngrocken.kngdancetrack.util.ActivityTemplate;

public class CreateDanceActivity extends ActivityTemplate {

    private CreateDanceFragment createDanceFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dance);

        createDanceFragment = new CreateDanceFragment();
        showFragment(R.id.create_dance_fragment_container, createDanceFragment);
    }
}
