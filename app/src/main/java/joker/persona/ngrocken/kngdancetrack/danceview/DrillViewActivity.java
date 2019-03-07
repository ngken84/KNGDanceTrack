package joker.persona.ngrocken.kngdancetrack.danceview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Date;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.danceview.fragments.DrillViewFragment;
import joker.persona.ngrocken.kngdancetrack.database.DanceObjectDBTasks;
import joker.persona.ngrocken.kngdancetrack.database.contracts.NoteContract;
import joker.persona.ngrocken.kngdancetrack.model.DanceNote;
import joker.persona.ngrocken.kngdancetrack.model.Drill;
import joker.persona.ngrocken.kngdancetrack.util.ActivityTemplate;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

public class DrillViewActivity extends ActivityTemplate {

    public static final int RESULT_CREATE_NOTE = 20;

    DrillViewFragment drillViewFragment;

    private Drill myDrill = null;

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
                    myDrill = drill;
                    drillViewFragment.setDrill(drill);
                }

                @Override
                public void handleError() {

                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case RESULT_CREATE_NOTE:
                handleCreateNote(resultCode, data);
                break;
        }

    }

    private void handleCreateNote(int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            long id = data.getLongExtra("id", 0L);
            String noteText = data.getStringExtra("note");
            if(noteText != null) {
                DanceNote note = new DanceNote(id, myDrill.getId(), NoteContract.NOTE_TYPE_DRILL, noteText, new Date());
                drillViewFragment.addNote(note);
            }
        }
    }
}
