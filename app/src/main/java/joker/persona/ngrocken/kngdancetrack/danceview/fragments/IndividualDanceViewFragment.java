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
import android.widget.Toast;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.danceview.CreateMoveActivity;
import joker.persona.ngrocken.kngdancetrack.danceview.IndividualDanceActivity;
import joker.persona.ngrocken.kngdancetrack.danceview.MoveActivity;
import joker.persona.ngrocken.kngdancetrack.model.Dance;

public class IndividualDanceViewFragment extends Fragment implements View.OnClickListener {

    private Button createDanceMoveBtn;
    private Button createDrillBtn;
    private Button createTaskBtn;
    private ListView danceListView;

    private Dance dance;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_individ_dance_view, null);

        createDanceMoveBtn = view.findViewById(R.id.fidv_createDanceMove);
        createDrillBtn = view.findViewById(R.id.fidv_createDrill);
        createTaskBtn = view.findViewById(R.id.fidv_createTask);
        setButtonsEnabled(false);

        danceListView = view.findViewById(R.id.fidv_danceListView);

        createDanceMoveBtn.setOnClickListener(this);

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
                    Toast.makeText(getContext(), "ID " + dance.getId(), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                break;
        }
    }

    public void setDance(Dance dance) {
        this.dance = dance;
        setButtonsEnabled(true);
    }
}
