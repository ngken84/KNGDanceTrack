package joker.persona.ngrocken.kngdancetrack.danceview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Consumer;
import android.widget.Toast;

import joker.persona.ngrocken.kngdancetrack.danceview.fragments.DanceViewFragment;
import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.database.DanceDBTasks;
import joker.persona.ngrocken.kngdancetrack.model.Dance;
import joker.persona.ngrocken.kngdancetrack.util.ActivityTemplate;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

public class DanceActivity extends ActivityTemplate {

    private DanceViewFragment danceViewFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dance);

        danceViewFragment = new DanceViewFragment();
        showFragment(R.id.dance_fragment_container, danceViewFragment);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case CreateDanceActivity.CREATE_DANCE:
                if(data != null) {
                    long id = data.getLongExtra("id", 0);
                    if (id != 0) {
                        DanceDBTasks.getDanceById(this, new DanceConsumer<Dance>() {

                            @Override
                            public void consume(Dance dance) {
                                danceViewFragment.addDance(dance);
                            }

                            @Override
                            public void handleError() {

                            }
                        }, data.getLongExtra("id", 0));
                    }
                }
                break;
        }
    }
}
