package joker.persona.ngrocken.kngdancetrack.danceview.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import joker.persona.ngrocken.kngdancetrack.R;

public class CreateNoteFragment extends Fragment implements View.OnClickListener {

    private EditText noteEdit;
    private Button addNoteBtn;
    private Button cancelBtn;

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
        }
    }
}
