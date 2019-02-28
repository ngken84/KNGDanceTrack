package joker.persona.ngrocken.kngdancetrack.danceview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.danceview.fragments.CreateNoteFragment;
import joker.persona.ngrocken.kngdancetrack.model.DanceConcept;
import joker.persona.ngrocken.kngdancetrack.util.ActivityTemplate;

public class CreateNoteActivity extends ActivityTemplate {

    private CreateNoteFragment createNoteFragment;

    private DanceConcept concept;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_note);

        createNoteFragment = new CreateNoteFragment();

        showFragment(R.id.create_note_fragment_container, createNoteFragment);
    }

    public void setConcept(DanceConcept concept) {
        this.concept = concept;
    }
}
