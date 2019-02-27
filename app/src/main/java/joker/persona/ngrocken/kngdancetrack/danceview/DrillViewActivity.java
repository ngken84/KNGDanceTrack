package joker.persona.ngrocken.kngdancetrack.danceview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.danceview.fragments.DrillViewFragment;
import joker.persona.ngrocken.kngdancetrack.database.DanceObjectDBTasks;
import joker.persona.ngrocken.kngdancetrack.model.Drill;
import joker.persona.ngrocken.kngdancetrack.util.ActivityTemplate;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

public class DrillViewActivity extends ActivityTemplate {

    DrillViewFragment drillViewFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drill_view);

        drillViewFragment = new DrillViewFragment();
        showFragment(R.id.drill_view_fragment_container, drillViewFragment);

        Intent intent = getIntent();
        long drillId = intent.getLongExtra("id", 0);
        if(drillId != 0) {
            DanceObjectDBTasks.getDrillById(this, drillId, new DanceConsumer<Drill>() {
                @Override
                public void consume(Drill drill) {
                    drillViewFragment.setDrill(drill);
                }

                @Override
                public void handleError() {

                }
            });
        }
    }
}
