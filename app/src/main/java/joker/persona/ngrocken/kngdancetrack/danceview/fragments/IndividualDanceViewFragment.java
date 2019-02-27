package joker.persona.ngrocken.kngdancetrack.danceview.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.adapters.DanceConceptAdapter;
import joker.persona.ngrocken.kngdancetrack.danceview.CreateDrillActivity;
import joker.persona.ngrocken.kngdancetrack.danceview.CreateMoveActivity;
import joker.persona.ngrocken.kngdancetrack.danceview.DrillViewActivity;
import joker.persona.ngrocken.kngdancetrack.danceview.IndividualDanceActivity;
import joker.persona.ngrocken.kngdancetrack.danceview.MoveActivity;
import joker.persona.ngrocken.kngdancetrack.database.DanceObjectDBTasks;
import joker.persona.ngrocken.kngdancetrack.model.Dance;
import joker.persona.ngrocken.kngdancetrack.model.DanceConcept;
import joker.persona.ngrocken.kngdancetrack.model.Drill;
import joker.persona.ngrocken.kngdancetrack.model.Move;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

public class IndividualDanceViewFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    public final static int CREATE_DANCE_MOVE_RESULT = 1;
    public final static int CREATE_DRILL_RESULT = 2;

    private Button createDanceMoveBtn;
    private Button createDrillBtn;
    private Button createTaskBtn;
    private ListView danceListView;

    private Dance dance;
    private DanceConceptAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individ_dance_view, null);

        createDanceMoveBtn = view.findViewById(R.id.fidv_createDanceMove);
        createDrillBtn = view.findViewById(R.id.fidv_createDrill);
        createTaskBtn = view.findViewById(R.id.fidv_createTask);
        setButtonsEnabled(false);

        danceListView = view.findViewById(R.id.fidv_danceListView);
        mAdapter = new DanceConceptAdapter(getContext(),  new LinkedList<DanceConcept>());
        danceListView.setAdapter(mAdapter);
        danceListView.setOnItemClickListener(this);

        createDanceMoveBtn.setOnClickListener(this);
        createDrillBtn.setOnClickListener(this);
        return view;
    }

    public void setButtonsEnabled(boolean enabled) {
        createDanceMoveBtn.setEnabled(enabled);
        createDrillBtn.setEnabled(enabled);
        createTaskBtn.setEnabled(enabled);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.fidv_createDanceMove:
                if(dance != null) {
                    Intent intent = new Intent(getContext(), CreateMoveActivity.class);
                    intent.putExtra("danceId", dance.getId());
                    intent.putExtra("danceName", dance.getName());
                    getActivity().startActivityForResult(intent, CREATE_DANCE_MOVE_RESULT);
                }
                break;
            case R.id.fidv_createDrill:
                if(dance != null) {
                    Intent intent = new Intent(getContext(), CreateDrillActivity.class);
                    intent.putExtra("danceId", dance.getId());
                    intent.putExtra("danceName", dance.getName());
                    getActivity().startActivityForResult(intent, CREATE_DRILL_RESULT);
                }
                break;

        }
    }

    public void addMove(Move move) {
        mAdapter.add(move);
    }

    public void addDrill(Drill drill) {mAdapter.add(drill); }

    public void setDance(Dance dance) {
        this.dance = dance;
        setButtonsEnabled(true);

        DanceObjectDBTasks.getAllDanceConceptsForDance(getContext(), dance.getId(),
                new DanceConsumer<List<DanceConcept>>() {
                    @Override
                    public void consume(List<DanceConcept> concepts) {
                        mAdapter.addAll(concepts);
                    }

                    @Override
                    public void handleError() {

                    }
                });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        DanceConcept concept = mAdapter.getItem(i);
        switch(concept.getConceptType()) {
            case DRILL:
                Drill drill = (Drill) concept;
                Intent intent = new Intent(getActivity(), DrillViewActivity.class);
                intent.putExtra("id", drill.getId());
                startActivity(intent);
                break;
        }
    }
}
