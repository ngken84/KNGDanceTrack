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
import android.widget.TextView;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.danceview.CreateNoteActivity;
import joker.persona.ngrocken.kngdancetrack.danceview.DrillViewActivity;
import joker.persona.ngrocken.kngdancetrack.model.Drill;

public class DrillViewFragment extends Fragment implements View.OnClickListener{

    private TextView nameText;
    private TextView descText;
    private Button addNoteBtn;
    private Button setSchedBtn;


    private Drill drill;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drill_view, null);

        nameText = view.findViewById(R.id.fdv_drill_name);
        descText = view.findViewById(R.id.fdv_drill_description);

        addNoteBtn = view.findViewById(R.id.fdv_add_note_btn);
        addNoteBtn.setOnClickListener(this);

        setSchedBtn = view.findViewById(R.id.fdv_add_schedule_btn);

        return view;
    }

    public void setDrill(Drill drill) {
        this.drill = drill;
        nameText.setText(drill.getName());
        descText.setText(drill.getDrill());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fdv_add_note_btn:
                Intent intent = new Intent(getActivity(), CreateNoteActivity.class);
                startActivityForResult(intent, DrillViewActivity.RESULT_CREATE_NOTE);
                break;
        }
    }
}
