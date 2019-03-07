package joker.persona.ngrocken.kngdancetrack.danceview.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.database.DanceNoteDBTasks;
import joker.persona.ngrocken.kngdancetrack.database.contracts.NoteContract;
import joker.persona.ngrocken.kngdancetrack.model.DanceConcept;
import joker.persona.ngrocken.kngdancetrack.model.DanceNote;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

public class CreateNoteFragment extends Fragment implements View.OnClickListener {

    private EditText noteEdit;
    private Button addNoteBtn;
    private Button cancelBtn;

    private DanceConcept concept;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_note, null);

        noteEdit = view.findViewById(R.id.fcn_note_edit);

        addNoteBtn = view.findViewById(R.id.fcn_create_btn);
        addNoteBtn.setOnClickListener(this);

        cancelBtn = view.findViewById(R.id.fcn_cancel_btn);
        cancelBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.fcn_cancel_btn:
                getActivity().setResult(getActivity().RESULT_CANCELED);
                getActivity().finish();
                break;
            case R.id.fcn_create_btn:
                createNote();
                break;
        }
    }

    private void createNote() {
        final String note = noteEdit.getText().toString().trim();
        if(note.length() == 0) {
            noteEdit.setError("Please enter a note.");
            return;
        }
        if(concept == null) {
            noteEdit.setError("No Linking Concept.");
            return;
        }
        switch (concept.getConceptType()) {
            case DRILL:
                DanceNote drillNote = new DanceNote(concept.getId(), NoteContract.NOTE_TYPE_DRILL, note);
                DanceNoteDBTasks.insertNote(getActivity(), drillNote, new DanceConsumer<Long>() {
                    @Override
                    public void consume(Long aLong) {
                        Intent intent = new Intent();
                        Log.d("#CREATE_NOTE", "Note created " + aLong);
                        intent.putExtra("id", aLong);
                        intent.putExtra("note", note);
                        getActivity().setResult(Activity.RESULT_OK, intent);
                        getActivity().finish();
                    }

                    @Override
                    public void handleError() {

                    }
                });

        }


    }


    public void setConcept(DanceConcept concept) {
        this.concept = concept;
    }


}
