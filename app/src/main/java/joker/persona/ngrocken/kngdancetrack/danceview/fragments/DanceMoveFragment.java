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
import joker.persona.ngrocken.kngdancetrack.danceview.MoveActivity;
import joker.persona.ngrocken.kngdancetrack.database.DanceNoteDBTasks;
import joker.persona.ngrocken.kngdancetrack.database.contracts.NoteContract;
import joker.persona.ngrocken.kngdancetrack.model.DanceNote;
import joker.persona.ngrocken.kngdancetrack.model.DanceObject;
import joker.persona.ngrocken.kngdancetrack.model.Move;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

public class DanceMoveFragment extends Fragment implements View.OnClickListener {

    private TextView titleText;
    private TextView moveText;
    private ListView listView;
    private Button addNoteBtn;

    private Move move;
    private DanceNoteAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dance_move, null);

        titleText = view.findViewById(R.id.fdm_title);
        moveText = view.findViewById(R.id.fdm_move_text);

        listView = view.findViewById(R.id.fdm_listview);
        
        addNoteBtn = view.findViewById(R.id.fdm_addNote);

        return view;
    }

    public void setMove(Move move) {
        this.move = move;
        addNoteBtn.setOnClickListener(this);

        titleText.setText(move.getName());
        moveText.setText(move.getDescription());

        DanceNoteDBTasks.getAllNotesByIdType(getActivity(), move.getId(), NoteContract.NOTE_TYPE_MOVE, new DanceConsumer<List<DanceNote>>() {
            @Override
            public void consume(List<DanceNote> danceNotes) {
                mAdapter = new DanceNoteAdapter(getActivity(), danceNotes);
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
            case R.id.fdm_addNote:
                Intent intent = new Intent(getContext(), CreateNoteActivity.class);
                intent.putExtra("id", move.getId());
                intent.putExtra("type", NoteContract.NOTE_TYPE_MOVE);
                getActivity().startActivityForResult(intent, MoveActivity.RESULT_CREATE_NOTE);
                break;
        }
    }

    public void addNote(DanceNote note) {
        mAdapter.add(note);
        mAdapter.sort(new DanceObject.DanceDateCreatedComparator());
        mAdapter.notifyDataSetChanged();
    }
}
