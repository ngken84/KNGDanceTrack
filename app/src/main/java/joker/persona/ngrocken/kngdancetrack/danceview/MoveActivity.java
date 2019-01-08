package joker.persona.ngrocken.kngdancetrack.danceview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.danceview.fragments.DanceMovesFragment;
import joker.persona.ngrocken.kngdancetrack.util.ActivityTemplate;

public class MoveActivity extends ActivityTemplate {

    private DanceMovesFragment movesFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dance_moves);

        movesFragment = new DanceMovesFragment();

        showFragment(R.id.dance_moves_fragment_container, movesFragment);

    }
}
