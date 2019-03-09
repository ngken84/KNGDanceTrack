package joker.persona.ngrocken.kngdancetrack.danceview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.danceview.fragments.CreateNoteFragment;
import joker.persona.ngrocken.kngdancetrack.database.DanceObjectDBTasks;
import joker.persona.ngrocken.kngdancetrack.database.contracts.NoteContract;
import joker.persona.ngrocken.kngdancetrack.model.DanceConcept;
import joker.persona.ngrocken.kngdancetrack.model.Drill;
import joker.persona.ngrocken.kngdancetrack.model.Move;
import joker.persona.ngrocken.kngdancetrack.util.ActivityTemplate;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

public class CreateNoteActivity extends ActivityTemplate {

    private CreateNoteFragment createNoteFragment;

    private DanceConcept concept;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_note);

        createNoteFragment = new CreateNoteFragment();

        showFragment(R.id.create_note_fragment_container, createNoteFragment);

        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        long id = intent.getLongExtra("id", 0);

        if(id == 0 || type == null) {
            setResult(RESULT_CANCELED);
            finish();
        }

        switch(type) {
            case NoteContract.NOTE_TYPE_DRILL:
                DanceObjectDBTasks.getDrillById(this, id, new DanceConsumer<Drill>() {
                    @Override
                    public void consume(Drill drill) {
                        setConcept(drill);
                    }

                    @Override
                    public void handleError() {
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                });
                break;
            case NoteContract.NOTE_TYPE_MOVE:
                DanceObjectDBTasks.getDanceMoveById(this, id, new DanceConsumer<Move>() {
                    @Override
                    public void consume(Move move) {
                        setConcept(move);
                    }

                    @Override
                    public void handleError() {
                        setResult(RESULT_CANCELED);
                        finish();
                    }
                });
                break;
        }
    }

    public void setConcept(DanceConcept concept) {
        this.concept = concept;
        createNoteFragment.setConcept(concept);

    }
}
