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
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.adapters.DanceNoteAdapter;
import joker.persona.ngrocken.kngdancetrack.danceview.CreateNoteActivity;
import joker.persona.ngrocken.kngdancetrack.danceview.DrillViewActivity;
import joker.persona.ngrocken.kngdancetrack.database.DanceNoteDBTasks;
import joker.persona.ngrocken.kngdancetrack.database.contracts.NoteContract;
import joker.persona.ngrocken.kngdancetrack.model.DanceNote;
import joker.persona.ngrocken.kngdancetrack.model.Drill;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

public class DrillViewFragment extends Fragment implements View.OnClickListener{

    private TextView nameText;
    private TextView descText;
    private Button addNoteBtn;
    private Button setSchedBtn;
    private ListView listView;

    private DanceNoteAdapter mAdapter;

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

        listView = view.findViewById(R.id.fdv_drill_lv);

        return view;
    }

    public void setDrill(Drill drill) {
        this.drill = drill;
        nameText.setText(drill.getName());
        descText.setText(drill.getDrill());

        DanceNoteDBTasks.getAllNotesByIdType(getActivity(), drill.getId(), NoteContract.NOTE_TYPE_DRILL, new DanceConsumer<List<DanceNote>>() {
            @Override
            public void consume(List<DanceNote> danceNotes) {
                mAdapter = new DanceNoteAdapter(getContext(), danceNotes);
                listView.setAdapter(mAdapter);
            }

            @Override
            public void handleError() {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fdv_add_note_btn:
                Intent intent = new Intent(getActivity(), CreateNoteActivity.class);
                intent.putExtra("id", drill.getId());
                intent.putExtra("type", "drill");
                startActivityForResult(intent, DrillViewActivity.RESULT_CREATE_NOTE);
                break;
        }
    }

    public void addNote(DanceNote note) {
        mAdapter.add(note);
    }
}
