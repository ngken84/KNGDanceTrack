package joker.persona.ngrocken.kngdancetrack.danceview.fragments;

import android.app.Activity;
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
import android.widget.RatingBar;

import joker.persona.ngrocken.kngdancetrack.R;
import joker.persona.ngrocken.kngdancetrack.database.DanceObjectDBTasks;
import joker.persona.ngrocken.kngdancetrack.model.Dance;
import joker.persona.ngrocken.kngdancetrack.model.Drill;
import joker.persona.ngrocken.kngdancetrack.util.DanceConsumer;

public class CreateDrillFragment extends Fragment implements View.OnClickListener {

    private EditText nameEdit;
    private EditText descEdit;
    private EditText durationEdit;
    private RatingBar ratingBar;;
    private Button createDrillBtn;

    private Dance dance = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_drill, null);

        nameEdit = view.findViewById(R.id.fcdl_drillname_edit);
        descEdit = view.findViewById(R.id.fcdl_drilldesc_edit);
        ratingBar = view.findViewById(R.id.fcdl_importance);
        durationEdit = view.findViewById(R.id.fcdl_drill_duration);

        createDrillBtn = view.findViewById(R.id.fcdl_createdrill_btn);
        createDrillBtn.setOnClickListener(this);

        return view;
    }

    public void setDance(Dance dance) {
        this.dance = dance;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.fcdl_createdrill_btn:
                createDrill();
                break;
        }
    }

    private void createDrill() {
        if(dance == null) {
            getActivity().setResult(Activity.RESULT_CANCELED);
            getActivity().finish();
            return;
        }
        boolean hasError = false;

        String name = nameEdit.getText().toString().trim();

        if(name.length() == 0) {
            nameEdit.setError("Please enter a name.");
            hasError = true;
        }

        String description = descEdit.getText().toString().trim();

        int importance = ratingBar.getNumStars();

        String durationStr = durationEdit.getText().toString().trim();
        int duration = 0;
        if(durationStr.length() == 0) {
            durationEdit.setError("Please enter how long the drill will generally take.");
            hasError = true;
        } else {
            duration = Integer.parseInt(durationStr);
            if(duration < 0) {
                durationEdit.setError("Duration must be at least one minute");
                hasError = true;
            }
        }


        if(hasError) {
            return;
        }

        Drill drill = new Drill(name, dance.getId(), dance.getName(), description, importance, duration);

        DanceObjectDBTasks.insertDrill(getActivity(), drill, new DanceConsumer<Long>() {
            @Override
            public void consume(Long aLong) {
                if(aLong != 0) {
                    Intent data = new Intent();
                    data.putExtra("id", aLong);
                    getActivity().setResult(Activity.RESULT_OK, data);
                    getActivity().finish();
                    return;
                }
            }

            @Override
            public void handleError() {

            }
        });





    }
}
